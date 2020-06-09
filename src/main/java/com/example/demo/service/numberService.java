package com.example.demo.service;

import com.example.demo.demo.number;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service(value = "numberServicel")
public class numberService {


    public int getNumber() {
        return 1;
    }

    @Transactional
    public int delNumber(number num) {
        return 1;
    }

}
