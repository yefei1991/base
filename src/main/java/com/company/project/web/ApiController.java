package com.company.project.web;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.project.core.JsonResult;
import com.company.project.model.Chapter;
import com.company.project.service.ChapterService;
import com.company.project.service.NovelService;

@RestController
@RequestMapping("/api")
public class ApiController {

	@Autowired
	private NovelService novelService;
	
	@Autowired
	private ChapterService chapterService;
	
	@GetMapping("/bookList")
	public JsonResult bookList() {
		return JsonResult.success(novelService.findAll());
	}
	
	@GetMapping("/chapterList")
	public JsonResult chapterList(Integer novelId) {
		Objects.requireNonNull(novelId, "novelId is null");
		return JsonResult.success(novelService.findChaptersByNovelId(novelId));
	}
	
	@GetMapping("/chapterDetail")
	public JsonResult chapterDetail(Integer chapterId) {
		Objects.requireNonNull(chapterId, "chapterId is null");
		Chapter c=chapterService.findById(chapterId);
		return JsonResult.success(c.getContent());
	}
}
