package com.example.demo.java.AQS;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: fzh
 * @Date: 2020/6/23 15:17
 * @Content:
 */
public class Demo_CyclicBarrier {

    static class TaskThread extends Thread {

        CyclicBarrier barrier;

        public TaskThread(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                System.out.println(getName() + " 到达栅栏 A");
                barrier.await();
                System.out.println(getName() + " 冲破栅栏 A");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int threadNum = 5;
        CyclicBarrier barrier = new CyclicBarrier(threadNum, () -> {
            System.out.println("所有线程已经就绪");
        });
        for (int j = 0; j < 3; j++) {
            for(int i = 0; i < 5; i++) {
                new TaskThread(barrier).start();
            }
            System.out.println("-------------------------------"+j);
            Thread.sleep(3000);
        }

    }
}
