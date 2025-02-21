package com.academy.domain.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainerExportVo {
    @ExcelProperty("ID")
    private Long id;
    @ExcelProperty("名称")
    private String name;
    @ExcelProperty("培训师简介")
    private String intro;
    @ExcelProperty("培训师资历")
    private String career;
    @ExcelProperty("头衔 1高级培训师 2首席培训师")
    private String level;
    @ExcelProperty("头像")
    private String avatar;
    @ExcelProperty("入驻时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ColumnWidth(value = 20)
    private Date createTime;
}
