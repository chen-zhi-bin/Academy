package com.academy.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.academy.domain.dto.TrainerSearchDTO;
import com.academy.domain.po.Trainer;
import com.academy.domain.vo.TrainerExportVo;
import com.academy.domain.vo.TrainerListVO;
import com.academy.entity.PageResult;
import com.academy.entity.Result;
import com.academy.mapper.TrainerMapper;
import com.academy.service.ITrainerService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.BeanUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.xiaoymin.knife4j.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 培训师表 服务实现类
 * </p>
 *
 * @author chan
 * @since 2025-02-13
 */
@Service
public class TrainerServiceImpl extends ServiceImpl<TrainerMapper, Trainer> implements ITrainerService {

    @Autowired
    private TrainerMapper trainerMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public PageResult<TrainerListVO> listTrainer(int currentPage, int pageSize, TrainerSearchDTO trainerDto) {
        QueryWrapper<Trainer> wrapper = new QueryWrapper<Trainer>()
                .like(trainerDto!=null&&StrUtil.isNotBlank(trainerDto.getName()),"name",trainerDto.getName())
                .eq(trainerDto!=null&&trainerDto.getLevel()!=null,"level",trainerDto.getLevel())
                .ge(trainerDto!=null&&trainerDto.getBeginTime()!=null,"create_time",trainerDto.getBeginTime())
                .le(trainerDto!=null&&trainerDto.getEndTime()!=null,"create_time",trainerDto.getEndTime());
        Page<Trainer> trainerPage = trainerMapper.selectPage(new Page<>(currentPage, pageSize), wrapper);
        PageResult<TrainerListVO> result = new PageResult<>();
        result.setTotal(trainerPage.getTotal());
        List<TrainerListVO> trainerListVOS = BeanUtil.copyToList(trainerPage.getRecords(), TrainerListVO.class);
        result.setData(trainerListVOS);
        return result;
    }

    @Override
    public List<Trainer> listAll() {
        // 1. 从redis中数据，如果redis中存在数据，直接返回就可以，不需要查询数据库
        List trainerList = redisTemplate.opsForList().range("trainerListAll", 0, -1);
        if(trainerList != null && trainerList.size() > 0){
            return trainerList;
        }
        // 2. 如果redis中没有数据，查询数据
        List<Trainer> trainerList1 = trainerMapper.selectList(null);
        // 3. 将数据库中的查询出来的数据存储到redis中
        redisTemplate.opsForList().leftPushAll("trainerListAll", trainerList1);
        // 4. 再返回数据
        return trainerList1;
    }

    @Override
    public List<TrainerExportVo> listAllTrainerExportVo() {
        List<TrainerExportVo> data = trainerMapper.listAllTrainerExportVo();
        return data;
    }
}
