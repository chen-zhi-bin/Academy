package com.academy.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

import java.util.Date;

/**
 * 培训师后端列表展示业务对象
 */
@Data
@Tag(name = "培训师后端列表展示业务对象")
public class TrainerListVO {
    @Schema(description ="ID")
    private Long id;
    @Schema(description ="名称")
    private String name;
    @Schema(description ="头衔：1初级 2中级 3高级 4首席")
    private Integer level;
    @Schema(description ="头像")
    private String avatar;
    @Schema(description ="入驻时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;
}