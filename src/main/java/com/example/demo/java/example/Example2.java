package com.example.demo.java.example;

import org.openjdk.jol.info.ClassLayout;

import static java.lang.System.out;
/**
 * @Author: fzh
 * @Date: 2020/6/18 20:24
 * @Content:
 */
public class Example2 {

    public static void main(String[] args) throws InterruptedException {


        Thread.sleep(5000);
        A a = new A();
        // TODO 00000001 00000000 00000000 00000000 当前状态是不可以偏向 无所
        out.println(ClassLayout.parseInstance(a).toPrintable()); // 无锁

        synchronized (a) {
            out.println(" lock ing");
            // TODO 11101000 11110101 00001010 00000011 当前状态 因为不可以偏向 锁升级为 轻量级
            out.println(ClassLayout.parseInstance(a).toPrintable());
        }

        out.println("after lock");
        out.println(ClassLayout.parseInstance(a).toPrintable());
    }
}
