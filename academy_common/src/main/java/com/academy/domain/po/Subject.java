package com.academy.domain.po;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 课程科目表
 */
@Data
@TableName("wnxt_subject")
@Schema(title = "Subject对象", description = "课程科目表")
public class Subject implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(title = "课程类别ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(title = "类别名称")
    private String title;

    @Schema(title = "父ID")
    private Long parentId;

    @Schema(title = "排序字段")
    private Integer sort;

    @Schema(title = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @Schema(title = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
