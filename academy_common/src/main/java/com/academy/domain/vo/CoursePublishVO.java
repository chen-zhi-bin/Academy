package com.academy.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 课程提交审核页面业务对象
 *
 * @author tarnett
 */
@Data
@Schema(title = "课程提交审核页面业务对象")
public class CoursePublishVO {
    @Schema(title = "ID")
    private Long id;
    @Schema(title = "标题")
    private String title;
    @Schema(title = "封面")
    private String cover;
    @Schema(title = "课时")
    private Integer lessonNum;
    @Schema(title = "一级分类名称")
    private String subjectOneName;
    @Schema(title = "二级分类名称")
    private String subjectTwoName;
    @Schema(title = "培训师名称")
    private String trainerName;
    @Schema(title = "价格")
    private String price;
}
