package com.example.demo.java.example.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: fzh
 * @Date: 2020/6/22 17:20
 * @Content: 自动 抢任务去执行 根据内核数量创建的线程池
 */
public class Demo2_WorkStealingPool {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService;
        executorService = Executors.newWorkStealingPool();

        executorService.execute(() -> {
            toDO(1000);
        });
        executorService.execute(() -> {
            toDO(1000);
        });executorService.execute(() -> {
            toDO(1000);
        });executorService.execute(() -> {
            toDO(1000);
        });executorService.execute(() -> {
            toDO(1000);
        });executorService.execute(() -> {
            toDO(1000);
        });executorService.execute(() -> {
            toDO(1000);
        });executorService.execute(() -> {
            toDO(1000);
        });executorService.execute(() -> {
            toDO(1000);
        });
        Thread.sleep(5000);
    }

    private static void toDO(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName());
        }
    }
}
