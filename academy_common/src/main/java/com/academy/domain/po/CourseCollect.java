package com.academy.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 课程收藏表
 * </p>
 *
 * @author chan
 * @since 2025-02-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("wnxt_course_collect")
@Schema(title ="CourseCollect对象", description="课程收藏表")
public class CourseCollect implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(title = "收藏ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(title = "课程培训师ID")
    private Long courseId;

    @Schema(title = "课程专业ID")
    private Long userId;

    @Schema(title = "逻辑删除 1（true）已删除， 0（false）未删除")
    private Integer isDeleted;

    @Schema(title = "创建时间")
    private LocalDateTime createTime;

    @Schema(title = "更新时间")
    private LocalDateTime updateTime;


}
