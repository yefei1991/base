package com.company.project.web;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.company.project.core.JsonResult;
import com.company.project.model.Chapter;
import com.company.project.model.Novel;
import com.company.project.service.ChapterService;
import com.company.project.service.NovelService;
import com.company.project.util.Utils;

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
    Novel novel = novelService.findById(novelId);
    Map<String, Object> result = new HashMap<>();
    result.put("novel", novel.getName());
    result.put("chapters", novelService.findChaptersByNovelId(novelId));
    return JsonResult.success(result);
  }

  @GetMapping("/chapterDetail")
  public JsonResult chapterDetail(Integer chapterId) {
    Utils.printCurrentTime();
    Objects.requireNonNull(chapterId, "chapterId is null");
    Chapter c = chapterService.findById(chapterId);
    chapterService.setPrevAndNext(c);
    Utils.printCurrentTime();
    return JsonResult.success(c);
  }
}
