package com.academy.controller;


import com.academy.constant.Constant;
import com.academy.domain.dto.TrainerSearchDTO;
import com.academy.domain.vo.TrainerExportVo;
import com.academy.domain.vo.TrainerListVO;
import com.academy.entity.PageResult;
import com.academy.entity.Result;
import com.academy.domain.po.Trainer;
import com.academy.service.ITrainerService;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 培训师表 前端控制器
 * </p>
 *
 * @author chan
 * @since 2025-02-13
 */
@RestController
@RequestMapping("/trainer")
@Tag(name = "培训师控制类")
public class TrainerController {

    @Autowired
    private ITrainerService trainerService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Operation(summary = "搜索+分页")
    @PostMapping("/list/search")
    public Result<PageResult<TrainerListVO>> listTrainer(
            @RequestBody TrainerSearchDTO trainerDto){
        System.out.println(trainerDto);
        PageResult<TrainerListVO> result = trainerService.listTrainer(trainerDto.getCurrentPage(), trainerDto.getPageSize(), trainerDto);
        return Result.ok(result);
    }

    @Operation(summary = "添加")
    @PostMapping("/save")
    public Result saveTrainer(@Valid @RequestBody Trainer trainer){
        boolean flag = trainerService.save(trainer);
        if (flag){
            // 将图片存到redis的集合中redis_db_upload_image
            String filename = trainer.getAvatar().substring(trainer.getAvatar().lastIndexOf("/") + 1, trainer.getAvatar().indexOf("?"));
            redisTemplate.opsForSet().add(Constant.REDIS_DB_UPLOAD_IMAGE, filename);
        }
        return Result.commonByFlag(flag);
    }

    @Operation(summary = "批量删除")
    @DeleteMapping
    public Result deleteBatch(@RequestParam List<Long> ids){
        boolean flag = trainerService.removeByIds(ids);
        return Result.commonByFlag(flag);
    }

    @Operation(summary = "根据id查找")
    @GetMapping("/{id}")
    public Result<Trainer> getById(@PathVariable Long id){
        Trainer trainer = trainerService.getById(id);
        return Result.ok(trainer);
    }

    @Operation(summary = "根据id修改")
    @PutMapping
    public Result updateById(@RequestBody Trainer trainer){
        boolean flag = trainerService.updateById(trainer);
        if (flag){
            // 将图片存到redis的集合中redis_db_upload_image
            String filename = trainer.getAvatar().substring(trainer.getAvatar().lastIndexOf("/") + 1, trainer.getAvatar().indexOf("?"));
            redisTemplate.opsForSet().add(Constant.REDIS_DB_UPLOAD_IMAGE, filename);
        }
        return Result.commonByFlag(flag);
    }

    @GetMapping("/list/all")
    public Result<List<Trainer>> listAll(){
        List<Trainer> trainerList = trainerService.listAll();
        return Result.ok(trainerList);
    }

    @PostMapping("/download")
    public void downloadTrainer(HttpServletResponse response) throws IOException {
        // 获取所有培训师信息
        List<TrainerExportVo> trainerList = trainerService.listAllTrainerExportVo();

        // 设置响应头
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("trainer_list", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-Disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

        // 使用 EasyExcel 写入数据
        try (ExcelWriter builder = EasyExcel.write(response.getOutputStream(), TrainerExportVo.class).build()) {
            WriteSheet sheet = EasyExcel.writerSheet("培训师列表").build();
            builder.write(trainerList, sheet);
            builder.finish();
            response.getOutputStream().flush();
        }
    }

    @GetMapping("/search/key-world")
    public Result<List<Trainer>> searchTrainerByKeyWorld(@RequestParam("key-world")String keyWorld){
        List<Trainer> data = trainerService.searchTrainerByKeyWorld(keyWorld);
        return Result.ok(data);
    }

}
