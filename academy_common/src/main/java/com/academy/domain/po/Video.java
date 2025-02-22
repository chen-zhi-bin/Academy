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
 * 课程视频表
 * </p>
 *
 * @author chan
 * @since 2025-02-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("wnxt_video")
@Schema(title ="Video对象", description="课程视频表")
public class Video implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(title = "视频ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(title = "课程ID")
    private Long courseId;

    @Schema(title = "章节ID")
    private Long chapterId;

    @Schema(title = "节点名称")
    private String title;

    @Schema(title = "云端视频资源")
    private Long videoSourceId;

    @Schema(title = "原始文件名称")
    private String videoOriginalName;

    @Schema(title = "排序字段")
    private Integer sort;

    @Schema(title = "播放次数")
    private Long playCount;

    @Schema(title = "是否可以试听：0收费 1免费")
    private Byte isFree;

    @Schema(title = "视频时长（秒）")
    private Double duration;

    @Schema(title = "Empty未上传 Transcoding转码中  Normal正常")
    private String status;

    @Schema(title = "视频源文件大小（字节）")
    private Long size;

    @Schema(title = "乐观锁")
    private Long version;

    @Schema(title = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Schema(title = "更新时间")
    private Date updateTime;
}
