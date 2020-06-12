package com.example.demo.java.stream;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamMap {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> collect = new ArrayList<>();
        collect.addAll(list.stream().filter(s -> s > 0).map(n -> n + 2).collect(Collectors.toList()));
        System.out.println(JSON.toJSON(collect));


        //Users users0 = new Users(0, "tom");
        //Users users1 = new Users(1, "jerry");
        //Users users3 = new Users(1, "jerry");
        //Users users2 = new Users(2, "superman");
        //List<Users> list = new ArrayList<>();
        //list.add(users0);

        //System.out.println(JSON.toJSONString(list));

        //Users users = list.get(0);
        //users.setId(99);


        //System.out.println(JSON.toJSONString(list));
        //list.add(users1);
        //list.add(users2);
        //list.add(users3);
        //将list转成map key:users.id value:users.age
        // Map<Integer, String> collect = list.stream().collect(Collectors.toMap(Users::getId, Users::getName));
        //System.out.println(JSON.toJSONString(collect));
        //将list转成map key:users.id value:users对象
        //第一种 直接返回users本身
        //Map<Integer, Users> collect1 = list.stream().collect(Collectors.toMap(Users::getId, users -> users));
        //System.out.println(JSON.toJSONString(collect1));
        //将list转成map key:users.id value:users对象
        //第二种 Function中有一个static方法identity 返回本身
        //Map<Integer, Users> collect2 = list.stream().collect(Collectors.toMap(Users::getId, Function.identity()));
        //System.out.println(JSON.toJSONString(collect2));

        //将list转成map key:users.age value:users对象
        //如果list中的age存在相同的时候,转化map的时候就会出错Duplicate key
        //Map<String, Users> collect3 = list.stream().collect(Collectors.toMap(Users::getName, Function.identity()));
        //System.out.println(JSON.toJSONString(collect3));
       //toMap的重载,定义key2覆盖key1的值
        //Map<Integer, Users> collect4 = list.stream().collect(Collectors.toMap(Users::getId, Function.identity(), (key1, key2) -> key2));
        //System.out.println(JSON.toJSONString(collect4));
    }
}
