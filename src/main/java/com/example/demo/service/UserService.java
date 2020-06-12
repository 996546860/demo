package com.example.demo.service;

import com.example.demo.demo.User;


public interface UserService {
    public User getUserById(int userId);

    boolean addUser(User record);

}