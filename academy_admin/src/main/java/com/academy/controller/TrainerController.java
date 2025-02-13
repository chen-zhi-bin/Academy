package com.academy.controller;


import com.academy.domain.dto.TrainerSearchDTO;
import com.academy.domain.vo.TrainerListVO;
import com.academy.entity.PageResult;
import com.academy.entity.Result;
import com.academy.domain.po.Trainer;
import com.academy.service.ITrainerService;
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
public class TrainerController {

    @Autowired
    private ITrainerService trainerService;

    @PostMapping("/list/{currentPage}/{pageSize}")
    public Result<PageResult<TrainerListVO>> listTrainer(
            @PathVariable("currentPage")int currentPage,
            @PathVariable("pageSize")int pageSize,
            @RequestBody TrainerSearchDTO trainerDto){
        return trainerService.listTrainer(currentPage,pageSize,trainerDto);
    }

    @PostMapping("/save")
    public Result saveTrainer(@RequestBody Trainer trainer){
        boolean flag = trainerService.save(trainer);
        return Result.commonByFlag(flag);
    }

    @DeleteMapping
    public Result deleteBatch(@RequestParam List<Long> ids){
        boolean flag = trainerService.removeByIds(ids);
        return Result.commonByFlag(flag);
    }

    @GetMapping("/{id}")
    public Result<Trainer> getById(@PathVariable Long id){
        Trainer trainer = trainerService.getById(id);
        return Result.ok(trainer);
    }

    @PutMapping
    public Result updateById(@RequestBody Trainer trainer){
        boolean flag = trainerService.updateById(trainer);
        return Result.commonByFlag(flag);
    }

}
