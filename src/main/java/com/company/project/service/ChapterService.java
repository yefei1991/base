package com.company.project.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.project.core.AbstractService;
import com.company.project.dao.ChapterMapper;
import com.company.project.model.Chapter;


/**
 * Created by CodeGenerator on 2018/10/17.
 */
@Service
@Transactional
public class ChapterService extends AbstractService<Chapter> {
    @Resource
    private ChapterMapper chapterMapper;

}
