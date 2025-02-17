package com.academy.service;

import com.academy.domain.po.Menu;
import com.academy.domain.vo.RouterVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface IMenuService extends IService<Menu> {
    /**
     * 根据登录的用户查询该用户具备的权限的菜单
     * @param username
     * @return
     */
    List<Menu> listTreeMenusByUsername(String username);
    /**
     * 构建前端路由所需要的菜单
     * @param menus 菜单列表
     * @return 路由列表
     */
    List<RouterVo> buildMenus(List<Menu> menus);
}
