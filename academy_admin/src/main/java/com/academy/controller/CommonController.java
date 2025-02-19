package com.academy.controller;

import com.academy.entity.Result;
import com.academy.utils.OssUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController {

    @Autowired
    private OssUtils ossUtils;

    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws IOException {
        // 1. 需要修改文件的名称（保证唯一性）
        String uuid = UUID.randomUUID().toString();
        String endFile = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        String filename = uuid + "." + endFile;
        // 2. 对文件的格式进行判断(类型 png 会通过后缀)
        if(!("png".equals(endFile) || "jpg".equals(endFile) || "jpeg".equals(endFile))){
            return Result.error("图片格式不正确");
        }
        // 3. 对文件的实际类型进行判断（文件头） TODO
        // 4. 调用oss的工具类上传图片,接受图片的地址
        String fileUrl = ossUtils.uploadFile(filename, file.getInputStream());
        log.info("图片上传到阿里云oss的访问地址是：" + fileUrl);
        // 5. 将图片地址发送给前端
        return Result.ok(20000, "图片上传成功", fileUrl);
    }

}
