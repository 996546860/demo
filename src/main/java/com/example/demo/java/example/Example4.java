package com.example.demo.java.example;

import org.openjdk.jol.info.ClassLayout;

import java.util.ArrayList;
import java.util.List;
import static java.lang.System.out;
import static java.lang.System.err;
/**
 * @Author: fzh
 * @Date: 2020/6/18 22:48
 * @Content:
 * 1 批量重偏向 -- t1实例化多个对象,(同一个类)，并且同步了这些对象，T2也同步
 * 了这些对象，因为要升级，锁要多次撤销偏向锁,jvm会认为接下来的对象需要批量重偏向，那么接下来
 * 都是偏向锁
 */
public class Example4 {

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(5000);
        List<A> lists = new ArrayList<>();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                A a1 = new A();
                synchronized (a1) {
                    lists.add(a1);
                }
            }
        });
        t1.start();
        t1.join();
        out.println(ClassLayout.parseInstance(lists.get(10)).toPrintable());
        err.println("结束打印1去");
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < lists.size(); i++) {
                A a = lists.get(i);
                synchronized (a) {

                    if(lists.size() == 15) {
                        out.println(ClassLayout.parseInstance(a).toPrintable());
                    }

                    if(lists.size() == 25) {
                        out.println(ClassLayout.parseInstance(a).toPrintable());
                    }
                }
            }
        });
        t2.start();
        t2.join();
    }
}
