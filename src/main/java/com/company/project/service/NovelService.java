package com.company.project.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.project.core.AbstractService;
import com.company.project.core.JsonResult;
import com.company.project.dao.NovelMapper;
import com.company.project.downloader.DingDianDownloader;
import com.company.project.model.Chapter;
import com.company.project.model.Novel;
import com.company.project.util.Downloader;

import tk.mybatis.mapper.entity.Condition;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;


/**
 * Created by CodeGenerator on 2018/10/17.
 */
@Service
@Transactional
public class NovelService extends AbstractService<Novel> {
    @Resource
    private NovelMapper novelMapper;
    
    @Autowired
    private ChapterService chapterService;

    public JsonResult saveChaptersByUrl(Novel novel) {
    	List<Chapter> chapters=Downloader.downloadChapters(novel.getUrl());
    	if(chapters.size()==0) {
    		return JsonResult.failure("下载失败");
    	}
    	chapters.forEach(s->{s.setNovelid(novel.getId());});
    	chapterService.save(chapters);
    	return JsonResult.success();
    }
    
    public JsonResult updateNovelDetail(Novel novel) {
    	Date startTime=new Date();
    	List<Chapter> chapters=this.findNeedDownload(novel);
    	ExecutorService service=Executors.newFixedThreadPool(16);
    	for(Chapter chapter:chapters) {
    		service.submit(()->{
    			if(Downloader.downloadDetail(chapter)) {
    				chapterService.update(chapter);
    			}
    		});
    	}
    	service.shutdown();
    	while(true) {
    		if(service.isTerminated()) {
    			Date endTime=new Date();
    			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    			System.out.println("开始时间是"+sdf.format(startTime));
    			System.out.println("结束时间是"+sdf.format(endTime));
    			return JsonResult.success();
    		}
    		try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    }
    
    public List<Chapter> findNeedDownload(Novel novel){
    	Condition con=new Condition(Chapter.class);
    	con.and().andIsNull("content").andEqualTo("novelid", novel.getId());
    	List<Chapter> chapters=chapterService.findByCondition(con);
    	return chapters;
    }
    
    public List<Map<String,Object>> findChaptersByNovelId(Integer novelId){
    	return novelMapper.findChaptersByNovelId(novelId);
    }
    
    public void download() {
    	DingDianDownloader dddownloader=new DingDianDownloader();
		HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
    	SimpleProxyProvider proxyProvider = SimpleProxyProvider.from(new Proxy("proxysz.aac.com", 80));
    	httpClientDownloader.setProxyProvider(proxyProvider);
    	List<Request> requests=new ArrayList<>();
    	Spider spider=Spider.create(dddownloader).thread(5).setDownloader(httpClientDownloader).addPipeline(new MysqlPipeline());
    	for(int i=1;i<=1;i++) {
    		Request request=new Request("https://www.x23us.com/modules/article/search.php?searchtype=keywords&searchkey=%C8%FD%B9%FA&page="+i);
    		request.putExtra("type", "novels");
    		requests.add(request);
    		spider.addRequest(request);
    	}
    	spider.run();
    }
    
    class MysqlPipeline implements Pipeline{

		@Override
		public void process(ResultItems resultItems, Task task) {
			if("novels".equals(resultItems.getRequest().getExtra("type"))) {
				List<Novel> novels=resultItems.get("novels");
				save(novels);
			}
			
		}
    	
    }
}
