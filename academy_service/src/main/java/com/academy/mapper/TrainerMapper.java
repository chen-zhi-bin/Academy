package com.academy.mapper;

import com.academy.domain.po.Trainer;
import com.academy.domain.vo.TrainerExportVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 培训师表 Mapper 接口
 * </p>
 *
 * @author chan
 * @since 2025-02-13
 */
public interface TrainerMapper extends BaseMapper<Trainer> {

    @Select("SELECT id, name, intro, career, " +
            "CASE " +
            "   WHEN level = 1 THEN '高级培训师' " +
            "   WHEN level = 2 THEN '首席培训师' " +
            "   ELSE level  " +
            "END AS level, " +
            "avatar, create_time " +
            "FROM wnxt_trainer")
    List<TrainerExportVo> listAllTrainerExportVo();
}
