package com.company.project.web;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.project.core.JsonResult;
import com.company.project.model.Novel;
import com.company.project.service.ChapterService;
import com.company.project.service.NovelService;

/**
* Created by CodeGenerator on 2018/10/17.
*/
@RestController
@RequestMapping("/novel")
public class NovelController {
    @Resource
    private NovelService novelService;
    
    @Autowired
    private ChapterService chapterService;

    @GetMapping("/saveChapters")
    public JsonResult saveChapters() {
    	Novel novel=novelService.findById(1);
    	return novelService.saveChaptersByUrl(novel);
    }
    
    @GetMapping("/saveNovel")
    public JsonResult saveNovel() {
    	Novel novel=new Novel();
    	novel.setName("首席御医");
    	novel.setUrl("https://www.x23us.com/html/4/4371/");
    	novelService.save(novel);
    	return JsonResult.success();
    }
    
    @GetMapping("/test")
    public JsonResult test() {
    	Novel novel=new Novel();
    	novel.setId(1);
    	return novelService.updateNovelDetail(novel);
    }
}
