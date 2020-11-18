package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.demo.User;
import com.example.demo.demo.number;
import com.example.demo.service.inteface.IFzh;
import com.example.demo.zookeeper.DistributedLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class testController {

    @Autowired
    IFzh iFzh;

    @RequestMapping("/showUser")
    @ResponseBody
    public User toIndex() {
        iFzh.isRedis();
        return new User();
    }

    @RequestMapping("sendEmail")
    public String sendEmail() throws Exception {
        DistributedLock lock = new DistributedLock("120.79.76.167:2181", "test1");
        try {
            lock.lock();
            int num = 5;
            if (num > 5) {
                number nu = new number();
                nu.setId(1);
                nu.setNum(num - 1);
                System.out.println(JSON.toJSON(nu));
                System.err.println(Thread.currentThread().getName() + "获取到了");
            }
        } catch (Exception e) {
            throw e;
        } finally {
            lock.unlock();
        }
        return "成功";
    }

    public static void main(String[] args) {
        String s = "{\n" +
                "\t\"code\":200,\n" +
                "\t\"message\":\"\",\n" +
                "\t\"body\":{\n" +
                "\t\t\"flagDefault\":false,\n" +
                "\t\t\"id\":1,\n" +
                "\t\t\"receiveArea\":\"string\",\n" +
                "\t\t\"receiveAreaId\":0,\n" +
                "\t\t\"receiveCity\":\"string\",\n" +
                "\t\t\"receiveCityId\":0,\n" +
                "\t\t\"receiveDetail\":\"string\",\n" +
                "\t\t\"receivePeople\":\"string\",\n" +
                "\t\t\"receiveProvince\":\"string\",\n" +
                "\t\t\"receiveProvinceId\":0,\n" +
                "\t\t\"receiveZipCode\":\"string\",\n" +
                "\t\t\"telPhone\":\"17621688293\",\n" +
                "\t\t\"userId\":0\n" +
                "\t}\n" +
                "}";
        s.replaceAll("\t","");
        Object o = JSON.toJSON(s);
        Map<String, Object> map = (Map<String, Object>) JSONObject.toJSON(o);
    }
}