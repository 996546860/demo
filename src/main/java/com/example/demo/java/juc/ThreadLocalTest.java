package com.example.demo.java.juc;

/**
 * @Author: fzh
 * @Date: 2020/6/15 21:17
 * @Content:
 */
public class ThreadLocalTest {
    private static   ThreadLocal<String> threadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                Thread.sleep(100);
                threadLocal.set("fzh");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(300);
                System.out.println(threadLocal.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
