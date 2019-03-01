package com.example.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class guitu {
    //假设谁先跑胜利 那么谁就 获得数值
    protected volatile static int vic = 1;

    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private static ExecutorService executorService =   Executors.newFixedThreadPool(2);


    public static void main(String[] args) {



        guitu gt = new guitu();

         Thread t1 = new Thread(()-> { // 乌龟 开始跑

            while (!Thread.currentThread().isInterrupted()){ //
                while (vic == 1){ // 如果没有认跑赢 就一直跑
                    System.out.println("乌龟正在加油跑步");
                    gt.JJ(Thread.currentThread().getName());
                    Thread.currentThread().interrupt();
                }
            }

        },"乌龟");


        Thread t2 = new Thread(()-> { // 兔子 开始跑

            while (!Thread.currentThread().isInterrupted()){ // 线程标记中断 代码结束
                while (vic == 1){ // 如果没有人结束 就一直跑
                    System.out.println("兔子会跑赢乌龟的");
                    gt.JJ(Thread.currentThread().getName());
                    Thread.currentThread().interrupt();
                }
            }

        },"兔子");

        t1.start();
        t2.start();

    }

    private  void JJ(String currntName){
        lock.readLock().lock();
        try{

            for (int i = 1; i < 10; i++) {
                for (int j = 1; j < 10; j++) {
                  System.out.println(currntName+"正在执行乘法表：》"+i+"*"+j+"="+i*j+"当前状态为 -- 》"+vic);
                }
            }

            if(vic == 1){

                System.out.println(Thread.currentThread().getName()+"率先跑完了本次比赛");
                vic = 0;
            }

        }catch (Exception e){
             e.printStackTrace();
        }finally {
            lock.readLock().unlock();
        }

    }


}
