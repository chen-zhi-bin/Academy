package com.academy.domain.po;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 培训师表
 * </p>
 *
 * @author chan
 * @since 2025-02-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("wnxt_trainer")
@Tag(name="Trainer对象", description="培训师表")
public class Trainer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "培训师ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @NotEmpty(message = "请输入培训师名称")
    @Schema(description = "培训师姓名")
    private String name;

    @Schema(description = "培训师简介")
    private String intro;

    @Schema(description = "培训师资历,一句话说明培训师")
    private String career;

    @NotNull(message = "请选择培训师头衔")
    @Schema(description = "头衔 1高级培训师 2首席培训师")
    private Integer level;

    @Schema(description = "培训师头像")
    private String avatar;

    @Schema(description= "排序")
    private Integer sort;

    @Schema(description = "逻辑删除 1（true）已删除， 0（false）未删除")
    @TableLogic
    private Boolean isDeleted;

    @Schema(description = "创建时间")
    @TableField(fill = FieldFill.INSERT) //插入时填充字段
    private Date createTime;

    @Schema(description = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)//插入和修改时填充字段
    private Date updateTime;


}
