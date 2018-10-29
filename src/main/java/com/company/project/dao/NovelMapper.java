package com.company.project.dao;

import java.util.List;
import java.util.Map;

import com.company.project.core.Mapper;
import com.company.project.model.Novel;

public interface NovelMapper extends Mapper<Novel> {
	
	List<Map<String,Object>> findChaptersByNovelId(Integer novelId);
}