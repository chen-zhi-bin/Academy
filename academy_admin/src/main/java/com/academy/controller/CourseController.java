package com.academy.controller;


import com.academy.domain.dto.CourseAddDTO;
import com.academy.domain.vo.CoursePublishVO;
import com.academy.entity.Result;
import com.academy.service.ICourseService;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @PutMapping
    public Result updateCourse(@RequestBody CourseAddDTO courseAddDTO){
        Long id = courseService.saveCourse(courseAddDTO);
        return Result.ok(id);
    }

    @GetMapping("/{id}")
    public Result<CourseAddDTO> getCourseById(@PathVariable Long id){
        CourseAddDTO courseAddDTO = courseService.getCourseById(id);
        return Result.ok(courseAddDTO);
    }

    @Schema(title ="查询发布课程数据")
    @GetMapping("/publish/{courseId}")
    public Result<CoursePublishVO> getCoursePublishById(@PathVariable String courseId){
        CoursePublishVO coursePublishVo = courseService.getCoursePublishById(courseId);
        return Result.ok(coursePublishVo);
    }

    @Schema(title ="发布课程")
    @PostMapping("/publish/{courseId}")
    public Result postCoursePublishById(@PathVariable String courseId){
        int row = courseService.postCoursePublishById(courseId);
        return row > 0 ? Result.ok("发布成功"):Result.error("发布失败");
    }
}
