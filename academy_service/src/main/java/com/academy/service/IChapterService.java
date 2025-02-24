package com.academy.service;

import com.academy.domain.po.Chapter;
import com.academy.domain.vo.ChapterVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程章节表 服务类
 * </p>
 *
 * @author chan
 * @since 2025-02-21
 */
public interface IChapterService extends IService<Chapter> {

    List<ChapterVO> listByCourseIdRelationVideos(Long courseId);

    void removeByIdRelation(Long id);
}
