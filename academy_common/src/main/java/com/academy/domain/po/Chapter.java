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
 * 课程章节表
 * </p>
 *
 * @author chan
 * @since 2025-02-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("wnxt_chapter")
public class Chapter implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(title = "章节ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(title = "课程ID")
    private Long courseId;

    @Schema(title = "章节名称")
    private String title;

    @Schema(title = "显示排序")
    private Integer sort;

    @Schema(title = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Schema(title = "更新时间")
    private Date updateTime;
}
