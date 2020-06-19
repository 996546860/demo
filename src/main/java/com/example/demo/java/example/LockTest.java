package com.example.demo.java.example;

/**
 * @Author: fzh
 * @Date: 2020/6/14 15:50
 * @Content:
 */
public class LockTest {
     static Boolean a = true;

    public static void main(String[] args) throws InterruptedException {
        LockTest t = new LockTest();
        Thread ta = new Thread(() -> {
            doa();

        });
        ta.start();

        ta.interrupt();
    }

    public static void doa() {
        while (a) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println("出现了异常");
                e.printStackTrace();
            }
            System.out.println(1111);
        }
    }

}
