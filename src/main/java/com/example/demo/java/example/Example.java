package com.example.demo.java.example;
import org.openjdk.jol.info.ClassLayout;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;
/**
 * @Author: fzh
 * @Date: 2020/6/16 21:48
 * @Content: jvm  如果 对有一个对象批量的加锁 释放 等操作 20次 会有神奇的发生
 *   重偏向 不是梦
 *
 */
public class Example {
    static List<A> list = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(5000);

        Thread t1 = new Thread( () -> {
            for (int i = 0; i < 30; i++) {
                A a = new A();
                synchronized (a) {
                    list.add(a);
                }
                if(i == 1 ){
                    out.println(1);
                    out.println(ClassLayout.parseInstance(a).toPrintable());
                }
            }
        });
        t1.start();
        t1.join();
        Thread.sleep(2000);
        out.println(ClassLayout.parseInstance(list.get(20)).toPrintable());


        Thread  t2 =  new Thread(() -> {
            for (int i = 0; i < list.size(); i++) {
                synchronized (list.get(i)) {
                    if (i == 15) {
                        out.println(15);
                        out.println(ClassLayout.parseInstance(list.get(i)).toPrintable());
                    }

                    if(i == 20) {
                        out.println();
                        out.println();
                        out.println();
                        out.println();
                        out.println();
                        out.println(20);
                        out.println(ClassLayout.parseInstance(list.get(i)).toPrintable());
                    }


                }
            }
        });
        t2.start();
    }
}
