package com.academy.controller;


import com.academy.domain.dto.SubjectImportDTO;
import com.academy.domain.po.Subject;
import com.academy.entity.Result;
import com.academy.service.ISubjectService;
import com.academy.utils.EasyExcelUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private ISubjectService subjectService;

    @GetMapping("/list/all")
    public Result<List<Subject>> listAll(){
        List<Subject> subjectList = subjectService.list();
        return Result.ok(subjectList);
    }

    /**
     * 导出数据
     * @param file
     * @return
     * @throws IOException
     */
    @Schema(title = "导入数据")
    @PostMapping("/import")
    public Result importData(MultipartFile file) throws IOException {
        // 1. 通过EasyExcel读取数据
        List<SubjectImportDTO> importDTOList = EasyExcelUtils.readExcelOneSheet(file.getInputStream(), SubjectImportDTO.class);
        // 2. 将数据交给service进行保存
        subjectService.importData(importDTOList);
        // 3. 返回数据
        return Result.ok();
    }

    @PostMapping("/downloadTemplate")
    public void downloadTemplate(HttpServletResponse response) throws IOException {
        File file = ResourceUtils.getFile("classpath:subject_template.xlsx");
        downloadTemplate("课程分类模板", file, response);
    }

    /**
     * 普通下载模板的方法
     * @param fileName
     * @param file
     * @param response
     * @throws Exception
     */
    public static void downloadTemplate(String fileName, File file, HttpServletResponse response) throws IOException {
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf8");
        response.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" + fileName + ".xlsx");

        int len = 0;
        byte[] bs = new byte[1024];
        InputStream in = new FileInputStream(file);
        while((len = in.read(bs)) != -1){
            response.getOutputStream().write(bs,0, len);
        }
        in.close();
        response.getOutputStream().flush();
    }
}
