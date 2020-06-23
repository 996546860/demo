package com.example.demo.java.AQS;

import org.checkerframework.checker.units.qual.C;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: fzh
 * @Date: 2020/6/23 14:11
 * @Content: 具体API 看方法的解释
 */
public class Demo_Latch {
    public static void main(String[] args) throws InterruptedException {
       ExecutorService service = Executors.newFixedThreadPool(20);
        CountDownLatch latch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() +"准备中");
                latch.countDown();
            }).start();
        }

        latch.await();

        System.out.println("全部开始执行");
    }
}
