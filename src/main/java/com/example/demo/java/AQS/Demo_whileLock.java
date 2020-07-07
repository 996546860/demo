package com.example.demo.java.AQS;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static java.util.concurrent.locks.ReentrantReadWriteLock.*;

/**
 * @Author: fzh
 * @Date: 2020/6/24 11:35
 * @Content:
 */
public class Demo_whileLock {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock(true);
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

        /*WriteLock writeLock = new writeLock();
        writeLock.lock();
        writeLock.unlock();*/

        /*ReadLock readLock = new ReadLock();

        readLock.lock();
        readLock.unlock();*/

        reentrantReadWriteLock.writeLock().lock();
        reentrantReadWriteLock.writeLock().unlock();

        reentrantReadWriteLock.readLock().lock();
        reentrantReadWriteLock.readLock().unlock();


        Thread thread = new Thread(()->{
            try {
                lock.lock();
                try {
                    Thread.sleep(5000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(111);
            }finally {
                lock.unlock();
            }
        }," t1");

        thread.start();

        Thread thread2 = new Thread(()->{
            try {
                lock.lock();
                try {
                    Thread.sleep(5000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(111);
            }finally {
                lock.unlock();
            }
        }," t2");

        thread2.start();


        Thread thread3 = new Thread(()->{
            try {
                lock.lock();

                try {
                    Thread.sleep(5000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(111);
            }finally {
                lock.unlock();
            }
        }," t3");

        thread3.start();


    }

}
