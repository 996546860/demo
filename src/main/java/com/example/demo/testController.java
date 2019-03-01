package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.example.demo.auto.User;
import com.example.demo.auto.UserFactory;
import com.example.demo.demo.number;
import com.example.demo.service.numberService;
import com.example.demo.zookeeper.DistributedLock;
import com.example.demo.zookeeper.zookeeperClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.sun.javafx.collections.MappingChange;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class testController {

    @Autowired
    private numberService numberServicel;

    @RequestMapping("sendEmail")
    public int sendEmail() throws Exception {
        DistributedLock lock = new DistributedLock("120.79.76.167:2181", "test1");

        try{
            lock.lock();
            int num = numberServicel.getNumber();
            if(num > 0){
                number nu = new number();
                nu.setId(1);
                nu.setNum(num-1);
                numberServicel.delNumber(nu);
                System.err.println(Thread.currentThread().getName()+"獲取到了資格..");
            }
        }catch (Exception e){
            throw e;
        }finally {
            lock.unlock();
        }

        return 1;
        /*zookeeperClient client = new zookeeperClient() {
            @Override
            public void process() throws Exception {
                System.out.println("正在打印..");
            }
        };
        client.run();*/
        /*User user = new User();
        System.out.println(user.getAge());
        return user;*/


    }


}