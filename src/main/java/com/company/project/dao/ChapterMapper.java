package com.company.project.dao;

import java.util.List;
import com.company.project.core.Mapper;
import com.company.project.model.Chapter;

public interface ChapterMapper extends Mapper<Chapter> {
  List<Chapter> findTop5000UnDownloadChapters();
}
