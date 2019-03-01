package com.example.demo;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class Child extends Parent {
    public Child(){
        System.out.println("cccc");
    }



    public static void main(String[] args) {

        //字符串连接 优化
        /*StringBuffer sblog = new StringBuffer();
        sblog.append("waterDriven|sellerId=");
        sblog.append("|result=");
        System.out.println(sblog.toString());

        String sblog= Joiner.on("|").withKeyValueSeparator("=")
                .join(ImmutableMap.of("sellerId", "result"));
        System.out.println(sblog);*/
        // 字符串连接 优化

        //时间转换优化
        /*try{

            SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");

            SimpleDateFormat sdfMins = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Date now = new Date();

            String today = sdfDay.format(now);

            String waterStart = today + " 03:00:00";

            String waterEnd = today + " 04:00:00";

            Date waterStartTime = sdfMins.parse(waterStart);

            Date waterEndTime = sdfMins.parse(waterEnd);

            System.out.println(waterStart);
            System.out.println(waterEnd);

        }catch (ParseException pe) {

            //return XX;

        }
        LocalDateTime now = LocalDateTime.now();

        LocalDateTime waterStart = LocalDateTime.of(now.getYear(), now.getMonth(),now.getDayOfMonth(),3,0);

        LocalDateTime waterEndTime =LocalDateTime.of(now.getYear(), now.getMonth(),now.getDayOfMonth(),4,0);

        System.out.println(waterEndTime);*/
        //时间转换优化

         //非空判断
         String  status = null;

         Object o = Optional.ofNullable(status).orElse("空的");

         System.out.println(o);


        Parent p = new Parent();


    }
}
