package com.example.demo.java.stream;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamMap {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> collect = new ArrayList<>();
        collect.addAll(list.stream().filter(s -> s > 0).map(n -> n + 2).collect(Collectors.toList()));
        System.out.println(JSON.toJSON(collect));

    }
}
