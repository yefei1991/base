package com.company.project.web;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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

  @Autowired
  private MongoTemplate mongoTemplate;

  @GetMapping("/bookList")
  public JsonResult bookList() {
    List<Novel> novels = mongoTemplate.findAll(Novel.class);
    // return JsonResult.success(novelService.findAll());
    return JsonResult.success(Utils.toMap(novels));
  }

  @GetMapping("/chapterList")
  public JsonResult chapterList(String novelId) {
    Objects.requireNonNull(novelId, "novelId is null");
    String novelHexId = new BigInteger(novelId, 10).toString(16);
    Query query = new Query(Criteria.where("id").is(novelHexId));
    Novel novel = mongoTemplate.findOne(query, Novel.class);
    Query query1 = new Query(Criteria.where("novelid").is(novelId));
    List<Chapter> chapters = mongoTemplate.find(query1, Chapter.class);
    Map<String, Object> result = new HashMap<>();
    // Novel novel = novelService.findById(novelId);
    // Map<String, Object> result = new HashMap<>();
    // result.put("novel", novel.getName());
    // result.put("chapters", novelService.findChaptersByNovelId(novelId));
    result.put("novel", novel.getName());
    result.put("chapters", Utils.toMap(chapters));
    return JsonResult.success(result);
  }

  @GetMapping("/chapterDetail")
  public JsonResult chapterDetail(String chapterId) {
    String chapterHexId = new BigInteger(chapterId, 10).toString(16);
    Objects.requireNonNull(chapterId, "chapterId is null");
    // Chapter c = chapterService.findById(chapterId);
    Query query = new Query(Criteria.where("id").is(chapterHexId));
    Chapter c = mongoTemplate.findOne(query, Chapter.class);
    // chapterService.setPrevAndNext(c);
    this.setMongoPrevAndNext(c);
    return JsonResult.success(c);
  }

  public void setMongoPrevAndNext(Chapter c) {
    Query query = new Query(Criteria.where("novelid").is(c.getNovelid()))
        .addCriteria(Criteria.where("sort").in(c.getSort() - 1, c.getSort() + 1));
    List<Chapter> chapters = mongoTemplate.find(query, Chapter.class);
    for (Chapter chapter : chapters) {
      if (chapter.getSort() > c.getSort()) {
        c.setNext(chapter.getId().toString());
      } else {
        c.setPrev(chapter.getId().toString());
      }
    }
  }

}
