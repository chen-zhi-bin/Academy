package com.academy.mapper;

import com.academy.domain.po.Chapter;
import com.academy.domain.vo.ChapterVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 课程章节表 Mapper 接口
 * </p>
 *
 * @author chan
 * @since 2025-02-21
 */
public interface ChapterMapper extends BaseMapper<Chapter> {


    @Select("select id, title from wnxt_chapter where course_id = #{courseId}")
    List<ChapterVO> listByCourseId( @Param("courseId") Long courseId);

    @Delete("delete from wnxt_chapter where id = #{id}")
    void deleteByChapterId(Long id);
}
