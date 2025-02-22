package com.academy.controller;


import com.academy.domain.dto.CourseAddDTO;
import com.academy.entity.Result;
import com.academy.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程表 前端控制器
 * </p>
 *
 * @author chan
 * @since 2025-02-21
 */
@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private ICourseService courseService;

    @PostMapping
    public Result addCourse(@RequestBody CourseAddDTO courseAddDTO){
        Long id = courseService.saveCourse(courseAddDTO);
        return Result.ok(id);
    }

    @GetMapping("/{id}")
    public Result<CourseAddDTO> getCourseById(@PathVariable Long id){
        CourseAddDTO courseAddDTO = courseService.getCourseById(id);
        return Result.ok(courseAddDTO);
    }
}
