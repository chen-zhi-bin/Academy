package com.academy.domain.po;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 课程表
 * </p>
 *
 * @author chan
 * @since 2025-02-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("wnxt_course")
@Schema(title = "Course对象", description = "课程表")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(title = "课程ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(title = "课程培训师ID")
    private Long trainerId;

    @Schema(title = "课程专业ID")
    private Long subjectId;

    @Schema(title = "课程专业父级ID")
    private Long subjectParentId;

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

    @Schema(title = "乐观锁")
    private Long version;

    @Schema(title = "课程状态 Draft未发布  Normal已发布")
    private String status;

    @Schema(title = "逻辑删除 1（true）已删除， 0（false）未删除")
    @TableLogic
    private Byte isDeleted;

    @Schema(title = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Schema(title = "更新时间")
    private Date updateTime;
}
