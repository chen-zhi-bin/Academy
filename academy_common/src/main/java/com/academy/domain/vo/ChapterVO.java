package com.academy.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 章节列表展示业务对象
 */
@Data
@Schema(title = "章节列表展示业务对象")
public class ChapterVO {
    @Schema(title ="ID")
    private Long id;
    @Schema(title ="章节名称")
    private String title;
    @Schema(title ="章节关联的视频列表")
    private List<VideoVO> videoVOList = new ArrayList<>();
}
