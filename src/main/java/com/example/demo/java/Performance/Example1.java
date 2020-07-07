package com.example.demo.java.Performance;

import com.example.demo.java.example.A;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static java.lang.System.out;

/**
 * @Author: fzh
 * @Date: 2020/6/19 20:55
 * @Content: 测试多线程 的性能  100000000 次调用   开启偏向锁
 * 单线程调用  1997毫秒
 * 2 线程调用  3604
 * 4 线程调用  3729
 * 6 线程调用  3873
 * 8 线程调用  3580
 *
 * 线程池引入
 * 单线程调用  2023
 * 2 线程调用 3522
 * 3 线程调用 4044
 * 5 线程调用 4267
 * 10 线程调用 4122
 * 15 线程调用 4200
 * 20 线程调用 4281
 * 30 线程调用 4099
 * 40 线程调用 4300
 * 50 线程调用 4729
 *
 * 按照道理来说
 */
public class Example1 {
    static A a = new A();
    static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        //Thread.sleep(5000);
        int threads = 10;
        long millis1 = System.currentTimeMillis();
        CountDownLatch latch = new CountDownLatch(threads);
        AtomicInteger atomicInteger = new AtomicInteger(100000000);
        /*Thread thread = new Thread(() -> {
            while (atomicInteger.decrementAndGet() >0) {
                a.toDo();
            }

            latch.countDown();
        });
        thread.start();

        Thread thread1 = new Thread(() -> {
            while (atomicInteger.decrementAndGet() >0) {
                dos();
            }
            latch.countDown();;
        });
        thread1.start();

        Thread thread2 = new Thread(() -> {
            while (atomicInteger.decrementAndGet() >0) {
                dos();
            }
            latch.countDown();;
        });
        thread2.start();

        Thread thread3 = new Thread(() -> {
            while (atomicInteger.decrementAndGet() >0) {
                dos();
            }
            latch.countDown();;
        });
        thread3.start();


        Thread thread4 = new Thread(() -> {
            while (atomicInteger.decrementAndGet() >0) {
                dos();
            }
            latch.countDown();;
        });
        thread4.start();

        Thread thread5 = new Thread(() -> {
            while (atomicInteger.decrementAndGet() >0) {
                dos();
            }
            latch.countDown();;
        });
        thread5.start();


        Thread thread6 = new Thread(() -> {
            while (atomicInteger.decrementAndGet() >0) {
                dos();
            }
            latch.countDown();;
        });
        thread6.start();

        Thread thread7 = new Thread(() -> {
            while (atomicInteger.decrementAndGet() >0) {
                dos();
            }
            latch.countDown();;
        });
        thread7.start();*/
        ExecutorService executorService = new ThreadPoolExecutor(20,50,0, TimeUnit.SECONDS,new SynchronousQueue<>());

        for (int i = 0; i < threads; i++) {
            Thread thread = new Thread(() -> {
                while (atomicInteger.decrementAndGet() > 0) {
                    dos();
                }
                latch.countDown();
            });
            executorService.execute(thread);
        }

        latch.await();

        long millis2 = System.currentTimeMillis();
        out.println("第一次单线程测试" +( millis2 - millis1));
    }

    private  static void dos() {
        lock.lock();
        try {

        } catch (Exception e){
        } finally {
            lock.unlock();
        }

    }
}
