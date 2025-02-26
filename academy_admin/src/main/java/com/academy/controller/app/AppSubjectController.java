package com.academy.controller.app;

import com.academy.domain.po.Subject;
import com.academy.entity.Result;
import com.academy.service.ISubjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户端课程分类控制器
 */
@RestController
@RequestMapping("/app/subject")
@Slf4j
public class AppSubjectController {
    @Autowired
    private ISubjectService subjectService;

    @GetMapping("/index/two")
    public Result<List<Subject>> listIndexOne(){
        List<Subject> subjectList = subjectService.listIndexTwo();
        return Result.ok(subjectList);
    }

}
