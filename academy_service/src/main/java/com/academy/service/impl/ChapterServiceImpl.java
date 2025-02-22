package com.academy.service.impl;

import com.academy.domain.po.Chapter;
import com.academy.mapper.ChapterMapper;
import com.academy.service.IChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程章节表 服务实现类
 * </p>
 *
 * @author chan
 * @since 2025-02-21
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements IChapterService {

}
