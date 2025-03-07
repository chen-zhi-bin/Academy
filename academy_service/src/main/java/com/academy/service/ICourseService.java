package com.academy.service;

import com.academy.domain.dto.CourseAddDTO;
import com.academy.domain.po.Course;
import com.academy.domain.vo.CoursePublishVO;
import com.academy.domain.vo.CourseVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程表 服务类
 * </p>
 *
 * @author chan
 * @since 2025-02-21
 */
public interface ICourseService extends IService<Course> {

    Long saveCourse(CourseAddDTO courseAddDTO);

    CourseAddDTO getCourseById(Long id);

    CoursePublishVO getCoursePublishById(String courseId);

    int postCoursePublishById(String courseId);

    List<CourseVO> getHotCourse();
}
