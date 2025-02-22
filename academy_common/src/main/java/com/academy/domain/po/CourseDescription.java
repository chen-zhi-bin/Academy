package com.academy.domain.po;

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
 * 课程简介表
 * </p>
 *
 * @author chan
 * @since 2025-02-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("wnxt_course_description")
@Schema(title = "CourseDescription对象", description = "课程简介表")
public class CourseDescription implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(title = "课程ID")
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    @Schema(title = "课程简介")
    private String description;

    @Schema(title = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Schema(title = "更新时间")
    private Date updateTime;
}
