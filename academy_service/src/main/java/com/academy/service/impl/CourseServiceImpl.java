package com.academy.service.impl;

import com.academy.domain.dto.CourseAddDTO;
import com.academy.domain.po.Course;
import com.academy.domain.po.CourseDescription;
import com.academy.domain.vo.CoursePublishVO;
import com.academy.domain.vo.CourseVO;
import com.academy.mapper.CourseDescriptionMapper;
import com.academy.mapper.CourseMapper;
import com.academy.service.ICourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程表 服务实现类
 * </p>
 *
 * @author chan
 * @since 2025-02-21
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {

    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private CourseDescriptionMapper courseDescriptionMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Long saveCourse(CourseAddDTO courseAddDTO) {
        // 1. 创建course对象 并将courseAddDTO对象中的数据设置到course对象
        Course course = new Course();
        // 2. 保存course对象
        BeanUtils.copyProperties(courseAddDTO, course);
        if (course.getId()!=null){
            courseMapper.updateById(course);
        }else {
            courseMapper.insert(course);
        }
        // 3. 创建CourseDescription 对象
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setId(course.getId());
        courseDescription.setDescription(courseAddDTO.getDescription());
        if (course.getId()!=null){
            courseDescriptionMapper.updateById(courseDescription);
        }else {
            courseDescriptionMapper.insert(courseDescription);
        }

        // 将图片地址保存到redis小集合
        String filename = course.getCover().substring(course.getCover().indexOf(".com") + 5, course.getCover().indexOf("?"));
        redisTemplate.opsForSet().add("redis_db_upload_image", filename);
        // 4. 返回结果
        return course.getId();
    }

    @Override
    public CourseAddDTO getCourseById(Long id) {
        // 1. 查询课程表的数据
        Course course = courseMapper.selectById(id);
        // 2. 查询课程详情的数据
        CourseDescription courseDescription = courseDescriptionMapper.selectById(id);
        // 3. 构建CourseAddDTO对象数据
        CourseAddDTO courseAddDTO = new CourseAddDTO();
        BeanUtils.copyProperties(course, courseAddDTO);
        courseAddDTO.setDescription(courseDescription.getDescription());
        return courseAddDTO;
    }

    @Override
    public CoursePublishVO getCoursePublishById(String courseId) {
        return courseMapper.getCoursePublishById(courseId);
    }

    @Override
    public int postCoursePublishById(String courseId) {
        return courseMapper.postCoursePublishById(courseId);
    }

    @Override
    public List<CourseVO> getHotCourse() {
        List<CourseVO> data = courseMapper.getHotCourse();
        return data;
    }
}
