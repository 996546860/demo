package com.example.demo.service;

import com.example.demo.dao.NumberDao;
import com.example.demo.demo.number;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service(value="numberServicel")
public class numberService {

    @Resource
    private NumberDao numberDao;

    public int getNumber(){
        return numberDao.findAll().get(0).getNum();
    }

    @Transactional
    public int delNumber(number num){
        try{
            numberDao.save(num);
        }catch (Exception e){
            System.out.println(e);
            throw e;
        }

        return 1;
    }

}
