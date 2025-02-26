package com.academy.mapper;

import com.academy.domain.po.Course;
import com.academy.domain.vo.CoursePublishVO;
import com.academy.domain.vo.CourseVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 课程表 Mapper 接口
 * </p>
 *
 * @author chan
 * @since 2025-02-21
 */
public interface CourseMapper extends BaseMapper<Course> {

    CoursePublishVO getCoursePublishById(String courseId);

    int postCoursePublishById(String courseId);

    List<CourseVO> getHotCourse();
}
