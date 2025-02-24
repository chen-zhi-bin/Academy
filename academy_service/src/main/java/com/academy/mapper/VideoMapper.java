package com.academy.mapper;

import com.academy.domain.po.Video;
import com.academy.domain.vo.VideoVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 课程视频表 Mapper 接口
 * </p>
 *
 * @author chan
 * @since 2025-02-21
 */
public interface VideoMapper extends BaseMapper<Video> {

    @Select("select id, title from wnxt_video where chapter_id = #{chapterId}")
    List<VideoVO> listByChapterId(Long chapterId);

    @Delete("delete from wnxt_video where chapter_id = #{id}")
    void deleteByChapterId(Long id);
}
