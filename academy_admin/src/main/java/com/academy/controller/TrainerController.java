package com.academy.controller;


import com.academy.domain.dto.TrainerSearchDTO;
import com.academy.domain.vo.TrainerListVO;
import com.academy.entity.PageResult;
import com.academy.entity.Result;
import com.academy.domain.po.Trainer;
import com.academy.service.ITrainerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Operation(summary = "搜索+分页")
    @PostMapping("/list/{currentPage}/{pageSize}")
    public Result<PageResult<TrainerListVO>> listTrainer(
            @PathVariable("currentPage")int currentPage,
            @PathVariable("pageSize")int pageSize,
            @RequestBody TrainerSearchDTO trainerDto){
        PageResult<TrainerListVO> result = trainerService.listTrainer(currentPage, pageSize, trainerDto);
        return Result.ok(result);
    }

    @Operation(summary = "添加")
    @PostMapping("/save")
    public Result saveTrainer(@Valid @RequestBody Trainer trainer){
        boolean flag = trainerService.save(trainer);
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
        return Result.commonByFlag(flag);
    }

}
