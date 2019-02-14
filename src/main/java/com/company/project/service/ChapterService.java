package com.company.project.service;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.company.project.core.AbstractService;
import com.company.project.dao.ChapterMapper;
import com.company.project.model.Chapter;
import tk.mybatis.mapper.entity.Condition;


/**
 * Created by CodeGenerator on 2018/10/17.
 */
@Service
@Transactional
public class ChapterService extends AbstractService<Chapter> {
  @Resource
  private ChapterMapper chapterMapper;

  public void setPrevAndNext(Chapter c) {
    Chapter next = findByNovelIdAndSort(c.getNovelid(), c.getSort() + 1);
    c.setNext(next == null ? null : next.getId() + "");
    c.setPrev(c.getSort() == 1 ? null
        : findByNovelIdAndSort(c.getNovelid(), c.getSort() - 1).getId() + "");
  }

  public Chapter findByNovelIdAndSort(int novelId, int sort) {
    Condition c = new Condition(Chapter.class);
    c.and().andEqualTo("sort", sort).andEqualTo("novelid", novelId);
    List<Chapter> chapters = this.findByCondition(c);
    return chapters.size() == 0 ? null : chapters.get(0);
  }

}
