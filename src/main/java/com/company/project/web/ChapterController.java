package com.company.project.web;
import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.project.service.ChapterService;

/**
* Created by CodeGenerator on 2018/10/17.
*/
@RestController
@RequestMapping("/chapter")
public class ChapterController {
    @Resource
    private ChapterService chapterService;

}
