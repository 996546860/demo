package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.UserDao;
import com.example.demo.demo.User;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao,User> implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public User getUserById(int userId) {
        return userDao.selectByPrimaryKey(userId);
    }

    @Override
    public boolean addUser(User record) {
        boolean result = false;
        try {
            userDao.insert(record);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}