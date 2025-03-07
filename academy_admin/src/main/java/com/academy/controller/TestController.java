package com.academy.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "测试类")
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @Operation(summary = "demo测试")
    @GetMapping("/demo")
    public String demo(){
        log.debug("进入了TestController===========");
        return "hello world";
    }
}