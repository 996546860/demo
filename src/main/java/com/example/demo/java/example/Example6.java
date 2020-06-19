package com.example.demo.java.example;

import org.openjdk.jol.info.ClassLayout;

import static java.lang.System.out;

/**
 * @Author: fzh
 * @Date: 2020/6/19 21:57
 * @Content:
 */
public class Example6 {
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(5000);
        A a = new A();

        Thread t1 = new Thread(() -> {
            synchronized (a) {
                out.println("a 0");
                out.println(ClassLayout.parseInstance(a).toPrintable());
            }
        });
        t1.start();
        t1.join();

        Thread thread = new Thread(() -> {
            synchronized (a) {
                out.println("a 1");
                out.println("a 1");
                out.println("a 1");
                out.println("a 1");
                out.println("a 1");
                out.println("a 1");
                out.println(ClassLayout.parseInstance(a).toPrintable());
            }
        });
        thread.start();
    }
}
