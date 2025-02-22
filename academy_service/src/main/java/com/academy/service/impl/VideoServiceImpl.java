package com.academy.service.impl;

import com.academy.domain.po.Video;
import com.academy.mapper.VideoMapper;
import com.academy.service.IVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频表 服务实现类
 * </p>
 *
 * @author chan
 * @since 2025-02-21
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements IVideoService {

}
