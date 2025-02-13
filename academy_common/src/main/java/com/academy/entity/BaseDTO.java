package com.academy.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

@Tag(name = "DTO的基础类")
@Data
public class BaseDTO {
    @Schema(description = "当前页")
    private Integer currentPage;
    @Schema(description = "每页记录数")
    private Integer pageSize;
}