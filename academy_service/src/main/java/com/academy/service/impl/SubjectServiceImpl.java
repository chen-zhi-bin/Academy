package com.academy.service.impl;

import com.academy.domain.dto.SubjectImportDTO;
import com.academy.domain.po.Subject;
import com.academy.domain.po.Trainer;
import com.academy.mapper.SubjectMapper;
import com.academy.mapper.TrainerMapper;
import com.academy.service.ISubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 会员表 服务实现类
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements ISubjectService {

    @Autowired
    private SubjectMapper subjectMapper;

    @Override
    public void importData(List<SubjectImportDTO> importDTOList) {
//        if (importDTOList.size() == 0) {
//            throw new RuntimeException("没有可导入数据");
//        }
        // 2. 遍历集合，获取对象
        for(SubjectImportDTO subjectDTO : importDTOList){
            // 判断一级分类是否存在，不存在则添加，存在则不用考虑
            Subject subjectOne = subjectMapper.getByTitleAndParent(subjectDTO.getOneTitle(), 0L);
            if(subjectOne == null){
                subjectOne = new Subject();
                subjectOne.setTitle(subjectDTO.getOneTitle());
                subjectMapper.insert(subjectOne);
            }
            // 3. 判断二级分类是否存在，不存在则添加，存在则不用考虑
            Subject subjectTwo = subjectMapper.getByTitleAndParent(subjectDTO.getTowTitle(), subjectOne.getId());
            if(subjectTwo == null){
                subjectTwo = new Subject();
                subjectTwo.setTitle(subjectDTO.getTowTitle());
                subjectTwo.setParentId(subjectOne.getId());
                subjectMapper.insert(subjectTwo);
            }
        }
    }
}
