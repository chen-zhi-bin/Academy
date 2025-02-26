package com.academy.service;

import com.academy.domain.dto.SubjectImportDTO;
import com.academy.domain.po.Subject;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ISubjectService extends IService<Subject> {
    void importData(List<SubjectImportDTO> importDTOList);

    List<Subject> listIndexTwo();
}
