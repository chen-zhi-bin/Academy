package com.academy.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 课程分类数据导入业务对象
 */
@Data
@Schema(title = "课程分类数据导入业务对象")
public class SubjectImportDTO {
    @Schema(title ="一级分类名称")
    private String oneTitle;
    @Schema(title ="二级分类名称")
    private String towTitle;
}
