package com.example.demo.java.AQS;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author: fzh
 * @Date: 2020/6/29 20:47
 * @Content:
 */
public class Demo_readWriteLock {
    static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public static void main(String[] args) {

        if(1!=1) {
            System.out.println(1);
        } else if(1==1) {
            System.out.println(2);
        }


        new Thread(()->{
            get();

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            put();

        },"t1").start();

        new Thread(()-> {
            get();

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            put();
        },"t2").start();

    }

    private static void put() {
        lock.writeLock().lock();
        System.out.println(Thread.currentThread().getName()+ "获取了 写锁");

        try {
            Thread.sleep(4000);
            System.out.println(Thread.currentThread().getName()+"写锁等待结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.writeLock().unlock();
    }

    private static void get() {
        lock.readLock().lock();
        System.out.println(Thread.currentThread().getName()+"获取了 读锁");
        try {
            Thread.sleep(4000);
            System.out.println(Thread.currentThread().getName()+"读锁等待结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.readLock().unlock();
    }

}
