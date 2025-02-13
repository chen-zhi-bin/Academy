package com.academy.service;

import com.academy.domain.dto.TrainerSearchDTO;
import com.academy.domain.po.Trainer;
import com.academy.domain.vo.TrainerListVO;
import com.academy.entity.PageResult;
import com.academy.entity.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 培训师表 服务类
 * </p>
 *
 * @author chan
 * @since 2025-02-13
 */
public interface ITrainerService extends IService<Trainer> {

    Result<PageResult<TrainerListVO>> listTrainer(int currentPage, int pageSize, TrainerSearchDTO trainerDto);
}
