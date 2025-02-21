package com.academy.mapper;

import com.academy.domain.po.Subject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface SubjectMapper extends BaseMapper<Subject> {

    @Select("select * from wnxt_subject where title = #{title} and parent_id = #{parentId}")
    Subject getByTitleAndParent(@Param("title") String title, @Param("parentId") Long parentId);

}
