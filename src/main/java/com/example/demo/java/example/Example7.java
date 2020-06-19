package com.example.demo.java.example;
import org.openjdk.jol.info.ClassLayout;

import static java.lang.System.out;
/**
 * @Author: fzh
 * @Date: 2020/6/19 23:37
 * @Content: 这个例子用来表用 偏向锁 膨胀为 轻量级锁 是 交替执行的时候
 */
public class Example7 {
    static A a ;

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(5000);
        a = new A();
        for (int i =0;i < 20; i ++) {
            int c  =i;
            Thread t1 = new Thread(() -> {
                synchronized (a) {
                    out.println("lock2 ing +" + c);
                    String printable = ClassLayout.parseInstance(a).toPrintable();
                    if(!printable.contains("00000101")){
                        out.println(printable);
                    }
                }
            });

            t1.start();
            t1.join();
            Thread t2 = new Thread(() -> {
                synchronized (a) {
                    out.println("lock3 ing +" + c);
                    String printable = ClassLayout.parseInstance(a).toPrintable();
                    if(!printable.contains("00000101")){
                        out.println(printable);
                    }
                }
            });
            t2.start();
            t2.join();
        }
    }
}
