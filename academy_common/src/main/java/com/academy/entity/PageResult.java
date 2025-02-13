package com.academy.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Tag(name = "分页数据响应对象")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> {
    @Schema(description = "总记录数")
    private Long total;
    @Schema(description = "分页数据")
    private List<T> data;
}