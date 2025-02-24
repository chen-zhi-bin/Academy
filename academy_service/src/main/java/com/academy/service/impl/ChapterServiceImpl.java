package com.academy.service.impl;

import com.academy.domain.po.Chapter;
import com.academy.domain.vo.ChapterVO;
import com.academy.domain.vo.VideoVO;
import com.academy.mapper.ChapterMapper;
import com.academy.mapper.VideoMapper;
import com.academy.service.IChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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


    @Autowired
    private ChapterMapper chapterMapper;
    @Autowired
    private VideoMapper videoMapper;

    @Override
    public List<ChapterVO> listByCourseIdRelationVideos(Long courseId) {
        // 1. 根据课程ID查询所有的章节数据
        List<ChapterVO> chapterVOList = chapterMapper.listByCourseId(courseId);
        // 2. 遍历章节列表，查询章节关联的视频数据
        if(chapterVOList != null && chapterVOList.size() > 0){
            for(ChapterVO chapterVO : chapterVOList){
                List<VideoVO> videoVOList = videoMapper.listByChapterId(chapterVO.getId());
                // 将查询出来的数据设置到chapterVO中
                chapterVO.setVideoVOList(videoVOList);
            }
        }
        // 3. 返回数据
        return chapterVOList;
    }

    @Override
    public void removeByIdRelation(Long id) {
        videoMapper.deleteByChapterId(id);
        chapterMapper.deleteByChapterId(id);
    }
}
