package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.model.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {

	User selectUserByUsernameAndPassword(@Param("username")String username, @Param("password")String password);
}
