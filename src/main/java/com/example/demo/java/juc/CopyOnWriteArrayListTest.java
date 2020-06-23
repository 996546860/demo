package com.example.demo.java.juc;

/**
 * @Author: fzh
 * @Date: 2020/6/15 22:00
 * @Content: 多线程 情况下
 */
public class CopyOnWriteArrayListTest {

    // 读多写少用这个
    //List<String> list =  new CopyOnWriteArrayList<>();

    // 读少写多用这个
    //Vector<String> list = new Vector<>();

    // 线程安全有问题
    //List<String> list =  new ArrayList<>();
}
