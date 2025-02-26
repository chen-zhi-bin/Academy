package com.academy.domain.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 课程
 */
@Data
@Schema(title = "视频列表展示业务对象")
public class CourseVO {
    @Schema(title ="ID")
    private Long id;

    @Schema(title = "课程培训师ID")
    private Long trainerId;

    @Schema(title = "课程培训师Name")
    private String trainerName;

    @Schema(title = "课程专业ID")
    private Long subjectId;

    @Schema(title = "课程专业二级分类")
    private String subjectTwoName;

    @Schema(title = "课程专业父级ID")
    private Long subjectParentId;


    @Schema(title = "课程专业父级分类")
    private String subjectOneName;

    @Schema(title = "课程标题")
    private String title;

    @Schema(title = "课程销售价格，设置为0则可免费观看")
    private BigDecimal price;

    @Schema(title = "总课时")
    private Integer lessonNum;

    @Schema(title = "课程封面图片路径")
    private String cover;

    @Schema(title = "销售数量")
    private Long buyCount;

    @Schema(title = "浏览数量")
    private Long viewCount;

    @Schema(title = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Schema(title = "更新时间")
    private Date updateTime;
}
