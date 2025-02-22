package com.academy.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 保存课程基础信息的业务实体
 */
@Data
public class CourseAddDTO {
    @Schema(title = "课程标题")
    private String title;
    @Schema(title = "一级分类ID")
    private Long subjectParentId;
    @Schema(title = "二级分类ID")
    private Long subjectId;
    @Schema(title = "培训师ID")
    private Long trainerId;
    @Schema(title = "总课时")
    private Integer lessonNum;
    @Schema(title = "课程描述")
    private String description;
    @Schema(title = "课程封面")
    private String cover;
    @Schema(title = "课程价格")
    private BigDecimal price;
}
