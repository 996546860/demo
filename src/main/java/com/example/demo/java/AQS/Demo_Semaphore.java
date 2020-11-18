package com.example.demo.java.AQS;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Author: fzh
 * @Date: 2020/6/23 15:11
 * @Content: 每次 放开两条线程执行,执行看效果
 */
public class Demo_Semaphore {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(20);
        Semaphore semaphore = new Semaphore(2);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() +"准备中");
                semaphore.release();
            }).start();
        }

        System.out.println("全部开始执行");
    }
}
