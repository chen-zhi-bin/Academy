package com.academy.domain.dto;

import com.academy.entity.BaseDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

import java.util.Date;

@Data
@Tag(name = "培训师搜索条件业务对象")
public class TrainerSearchDTO extends BaseDTO {
    @Schema(description ="培训师姓名")
    private String name;
    @Schema(description ="培训师头衔")
    private Integer level;
    @Schema(description ="入驻的起始时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date beginTime;
    @Schema(description ="入驻的结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date endTime;
}
