package com.academy.service;

import com.academy.domain.dto.UserLoginDTO;
import com.academy.domain.po.User;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IUserService extends IService<User> {

    User login(UserLoginDTO userLoginDTO);

    User selectByUsername(String token);
}
