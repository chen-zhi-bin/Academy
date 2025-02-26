package com.academy.controller.app;

import com.academy.domain.po.Course;
import com.academy.domain.vo.CourseVO;
import com.academy.entity.Result;
import com.academy.service.ICourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/app/course")
@Slf4j
public class AppCourseController {

    @Autowired
    private ICourseService courseService;

    @GetMapping("/course/hot")
    public Result<List<CourseVO>> getHotCourse(){
        List<CourseVO> data = courseService.getHotCourse();
        return Result.ok(data);
    }
}
