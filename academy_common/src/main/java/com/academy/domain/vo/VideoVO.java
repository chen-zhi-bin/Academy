package com.academy.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 视频列表展示业务对象
 */
@Data
@Schema(title = "视频列表展示业务对象")
public class VideoVO {
    @Schema(title ="ID")
    private Long id;
    @Schema(title ="视频名称")
    private String title;
}
