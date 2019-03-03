package com.example.demo.service;

import com.example.demo.demo.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface UserService {
    public User getUserById(int userId);

    boolean addUser(User record);

}