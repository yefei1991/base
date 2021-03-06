package com.company.project.downloader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import com.company.project.model.Chapter;
import com.company.project.model.Novel;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;
import us.codecraft.webmagic.selector.Selectable;

public class DingDianDownloader implements PageProcessor {

  public static void main(String[] args) {
    DingDianDownloader dddownloader = new DingDianDownloader();
    HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
    SimpleProxyProvider proxyProvider = SimpleProxyProvider.from(new Proxy("proxysz.aac.com", 80));
    httpClientDownloader.setProxyProvider(proxyProvider);
    List<Request> requests = new ArrayList<>();
    Spider spider = Spider.create(dddownloader).thread(32).setDownloader(httpClientDownloader);
    for (int i = 1; i <= 1; i++) {
      Request request = new Request(
          "https://www.x23us.com/modules/article/search.php?searchtype=keywords&searchkey=%C8%FD%B9%FA&page="
              + i);
      request.putExtra("type", "novels");
      requests.add(request);
      spider.addRequest(request);
    }
    spider.run();
  }

  @Override
  public void process(Page page) {
    if ("novels".equals(page.getRequest().getExtra("type"))) {
      // String url=page.getHtml().$(".read").links().get();
      // System.out.println(page.getHtml().$("#content tr").nodes().size());
      List<Selectable> trs = page.getHtml().$("#content tr").nodes();
      List<Novel> novels = new ArrayList<>();
      for (int i = 1; i < trs.size(); i++) {
        Novel novel = new Novel();
        List<Selectable> tds = trs.get(i).css("td").nodes();
        String url = trs.get(i).css("td").links().all().get(1);
        String name = tds.get(0).$("a").get().replaceAll("<.*?>", "");
        String author = tds.get(2).toString().replaceAll("<.*?>", "");
        String type = tds.get(5).toString().replaceAll("<.*?>", "");
        novel.setAuthor(author);
        novel.setName(name);
        novel.setType(type);
        novel.setUrl(url);
        novels.add(novel);
        Request request = new Request(url);
        request.putExtra("type", "chapters");
        request.putExtra("novelUrl", url);
        page.addTargetRequest(request);
      }
      page.putField("novels", novels);
      // Request request=new Request(url);
      // request.putExtra("type", "chapter");
      // page.setSkip(true);
      // page.addTargetRequest(request);
    } else if ("chapters".equals(page.getRequest().getExtra("type"))) {
      List<String> urls = page.getHtml().$("table").links().all();
      List<String> titles = page.getHtml().$("table a", "text").all();
      List<Chapter> chapters = new ArrayList<>();
      for (int i = 0; i < urls.size(); i++) {
        Chapter c = new Chapter();
        c.setSort(i + 1);
        c.setTitle(titles.get(i));
        c.setUrl(urls.get(i));
        chapters.add(c);
        Request request = new Request(urls.get(i));
        request.putExtra("type", "chapterDetail");
        page.addTargetRequest(request);
      }
      page.putField("chapters", chapters);
    } else if ("chapterDetail".equals(page.getRequest().getExtra("type"))) {
      String content = page.getHtml().css("#contents", "text").get();
      page.putField("content", content);
    }
  }

  @Override
  public Site getSite() {
    return Site.me().setSleepTime(100).setRetryTimes(5).setRetrySleepTime(100);
  }

  class novelPipeLine implements Pipeline {

    @Override
    public void process(ResultItems resultItems, Task task) {
      System.out.println(resultItems.getRequest().getUrl());
      System.out.println("enter");
      String title = resultItems.get("title");
      String chapter = resultItems.get("chapter");
      String content = resultItems.get("content");
      try {
        Path path = Paths.get("d:\\ddxs\\" + title + "\\" + chapter + ".txt");
        if (!Files.exists(path.getParent())) {
          Files.createDirectories(path.getParent());
        }
        Files.write(path, content.getBytes(), StandardOpenOption.CREATE);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

  }
}
