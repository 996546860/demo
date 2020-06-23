package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.demo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao extends BaseMapper<User> {
    @Select("select *From user where id = 1")
    User selectByPrimaryKey(Integer id);
}