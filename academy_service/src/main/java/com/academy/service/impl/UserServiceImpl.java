package com.academy.service.impl;

import com.academy.domain.dto.UserLoginDTO;
import com.academy.domain.po.User;
import com.academy.mapper.UserMapper;
import com.academy.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private final UserMapper userMapper;

    @Override
    public User login(UserLoginDTO userLoginDTO) {
        // 根据用户名查询
        User user = userMapper.selectByUsername(userLoginDTO.getUsername());
        if(user == null){
            return null;
        }
        // 比较密码
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        boolean flag = bCryptPasswordEncoder.matches(userLoginDTO.getPassword(), user.getPassword());
        if(!flag){
            return null;
        }
        return user;
    }

    @Override
    public User selectByUsername(String username) {
        return userMapper.selectByUsername(username);
    }
}
