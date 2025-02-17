package com.academy.mapper;

import com.academy.domain.po.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;


public interface MenuMapper extends BaseMapper<Menu> {
    List<Menu> listTreeMenusAll(String username);
    List<Menu> listTreeMenusByUsername(String username);
}
