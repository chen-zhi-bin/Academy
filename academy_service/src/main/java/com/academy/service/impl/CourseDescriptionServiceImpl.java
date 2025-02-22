package com.academy.service.impl;

import com.academy.domain.po.CourseDescription;
import com.academy.mapper.CourseDescriptionMapper;
import com.academy.service.ICourseDescriptionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程简介表 服务实现类
 * </p>
 *
 * @author chan
 * @since 2025-02-21
 */
@Service
public class CourseDescriptionServiceImpl extends ServiceImpl<CourseDescriptionMapper, CourseDescription> implements ICourseDescriptionService {

}
