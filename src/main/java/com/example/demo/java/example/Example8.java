package com.example.demo.java.example;

import org.openjdk.jol.info.ClassLayout;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

/**
 * @Author: fzh
 * @Date: 2020/6/18 22:48
 * @Content:
 * 1 批量重偏向 -- t1实例化多个对象(同一个类),并且对这些对象sync(obj)，T2也sync(obj)
 *  因为要升级，锁要多次撤销偏向锁,jvm会认为接下来的对象是需要批量重偏向，那么接下来的锁都会重偏向 （按照JVM的数据来说 达到批量重偏向的阀值为20）
 * 补充 ：
 *    就如 1线程批量加锁20次后，2线程又批量加锁20次，那么2线程第十次加锁的时候 ，这个锁可能也会是偏向锁，因为 从头到尾 可能就还是同一条线程
 *  在1 和 2 线程之间加一个 2.1 线程执行，才能让 1 2 线程彻底分离开来
 * 2 批量撤销 -- t1实例化对象100个进行sync(obj),此时这些锁都是偏向锁(p-t1),然后同步等待t1完成操作之后，t2对t1实例化的100个对象进行再次的sync(obj),
 * 此时如果达到'批量重偏向'的条件之后，那么20之后的锁就会按早 （1） 当中的操作, 此时假设 t2操作100个对象中,只操作了40个,线程执行结束,
 * t3开始执行，=重点=,如果t3对剩余的这些对象进行sync(obj),达到一定的阀值之后，就会把后面的所有对象锁升级为轻量级锁
 */
public class Example8 {

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(4100);
        List<A> lists = new ArrayList<>();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                A a1 = new A();
                synchronized (a1) {
                    lists.add(a1);
                }
            }
        });
        t1.start();
        t1.join();



        Thread tt1 = new Thread(() -> {
        });
        tt1.start();
        tt1.join();

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < lists.size(); i++) {
                A a = lists.get(i);
                synchronized (a) {
                    if(i == 21) {
                        out.println(21);
                        out.println(ClassLayout.parseInstance(a).toPrintable());
                        break;
                    }
                }
            }
        });
        t2.start();
        t2.join();
        out.println(ClassLayout.parseInstance(lists.get(10)).toPrintable());

        /*Thread tt2 = new Thread(() -> {
        });
        tt2.start();
        tt2.join();


        System.gc();

        Thread.sleep(5000);
        Thread t3 = new Thread(() -> {
            for (int i = 0; i < lists.size(); i++) {
                A a = lists.get(i);
                synchronized (a) {
                    if(i == 21) {
                        out.println("t3"+21);
                        out.println(ClassLayout.parseInstance(a).toPrintable());
                    }

                    if(i == 40) {
                        out.println("t3"+40);
                        out.println(ClassLayout.parseInstance(a).toPrintable());
                    }

                    if(i == 50) {
                        out.println("t3"+50);
                        out.println(ClassLayout.parseInstance(a).toPrintable());
                    }

                    if(i == 90) {
                        out.println("t3"+90);
                        out.println(ClassLayout.parseInstance(a).toPrintable());
                    }
                }
            }
        });
        t3.start();
*/

    }
}
