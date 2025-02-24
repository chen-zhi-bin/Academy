package com.academy.controller;


import com.academy.domain.po.Chapter;
import com.academy.domain.vo.ChapterVO;
import com.academy.domain.vo.CoursePublishVO;
import com.academy.entity.Result;
import com.academy.service.IChapterService;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程章节表 前端控制器
 * </p>
 *
 * @author chan
 * @since 2025-02-21
 */
@RestController
@RequestMapping("/chapter")
@Slf4j
@Tag(name = "章节管理")
public class ChapterController {
    @Autowired
    private IChapterService chapterService;

    @Schema(title = "根据课程ID查询章节及视频")
    @GetMapping("/relation/video/{courseId}")
    public Result<List<ChapterVO>> listByCourseIdRelationVideos(@PathVariable("courseId") Long courseId){
        List<ChapterVO> chapterVOList = chapterService.listByCourseIdRelationVideos(courseId);
        return Result.ok(chapterVOList);
    }

    @Schema(title = "保存")
    @PostMapping
    public Result save(@Valid @RequestBody Chapter chapter){
        boolean flag = chapterService.save(chapter);
        return Result.commonByFlag(flag);
    }

    @Schema(title = "删除")
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Long id){
        chapterService.removeByIdRelation(id);
        return Result.ok();
    }

    @Schema(title = "根据ID查询")
    @GetMapping("/{id}")
    public Result<Chapter> getById(@PathVariable Long id){
        Chapter chapter = chapterService.getById(id);
        return Result.ok(chapter);
    }

    @Schema(title = "根据ID编辑")
    @PutMapping
    public Result updateById(@Valid @RequestBody Chapter chapter){
        boolean flag = chapterService.updateById(chapter);
        return Result.commonByFlag(flag);
    }

}
