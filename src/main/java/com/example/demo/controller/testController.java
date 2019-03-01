package com.example.demo.controller;

import com.example.demo.demo.number;
import com.example.demo.service.numberService;
import com.example.demo.zookeeper.DistributedLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class testController {

    @Autowired
    private numberService numberServicel;

    @RequestMapping("sendEmail")
    public String sendEmail() throws Exception {
        DistributedLock lock = new DistributedLock("120.79.76.167:2181", "test1");

        try{
            lock.lock();
            int num = numberServicel.getNumber();
            if(num > 5){
                number nu = new number();
                nu.setId(1);
                nu.setNum(num-1);
                numberServicel.delNumber(nu);
                System.err.println(Thread.currentThread().getName()+"获取到了");
            }
        }catch (Exception e){
            throw e;
        }finally {
            lock.unlock();
        }

        return "成功";
        /*zookeeperClient client = new zookeeperClient() {
            @Override
            public void process() throws Exception {
                System.out.println("正在打印..");
            }
        };
        client.run();*//*
        *//*User user = new User();
        System.out.println(user.getAge());
        return user;*/
    }


}