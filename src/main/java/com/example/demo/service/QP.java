package com.example.demo.service;

import java.util.List;

public class QP {
    public static List maopao(List<Integer> list){
        Integer[] integers = new Integer[list.size()];
        list.toArray(integers);
        for (int i = 0; i < integers.length-1; i++) {
            for (int j = 0; j < integers.length-1-i; j++) {
                if(integers[j]>integers[j+1]){
                    int temp = integers[j];
                    integers[j] = integers[j+1];
                    integers[j+1] = temp;
                }
            }
        }
        return java.util.Arrays.asList(integers);
    }
    public static List replaceA(List<Integer> list){
        Object[] objects = new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            Integer six =  list.get(i);
            if(six==1){
                objects[i] = "A";
            }else if(six == 11){
                objects[i] = "J";
            }else if(six == 12){
                objects[i] = "Q";
            }else if(six == 13){
                objects[i] = "K";
            }else if(six == 88){
                objects[i] = "小王";
            }else if(six == 99){
                objects[i] = "大王";
            }else{
                objects[i] = six;
            }
        }
        return java.util.Arrays.asList(objects);
    }
}
