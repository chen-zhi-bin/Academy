package com.academy.mapper;

import com.academy.domain.po.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper extends BaseMapper<User> {

    @Select("select * from wnxt_user where username = #{username} and is_deleted = 0")
    User selectByUsername(@Param("username") String username);
}
