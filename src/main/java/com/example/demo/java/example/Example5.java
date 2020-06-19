package com.example.demo.java.example;

import org.openjdk.jol.info.ClassLayout;

import static java.lang.System.out;

/**
 * @Author: huahua
 * @Date: 2020/6/19
 * @Content:
 *  偏向锁和无锁
 *  1. jvm 启动加载【4(秒)已过】的条件， 会把加锁的对象标记为可偏向状态 也就是对象头第一行 00000101 00000000 00000000 00000000
 *  2. 当前成进行加锁的时候，会根据是否可偏向 [表头 中 (00000‘1(偏向锁状态)’01  +  00000000 00000000 00000000(hashCode为空))] 当满足当前条件的时候 ，当前锁会变成【可偏向锁】
 *  3. 当锁转换为【偏向锁】的之后，表头为(00000101 11110000 01100100 00100000) (543485957) ,当前状态下， 101 才是真正的标识了当前锁是【偏向锁】
 *  4. 偏向锁 是无法保存hashCode的，对象头包括了  (锁信息)thread:23 | (阀值[目前认知批量重偏向和批量重撤销])epoch:2 | (年龄代)age:4 | (是否偏向)biased_lock:1 | lock:2 Biased
 *  5. 加入你调用了 对象.hashCode,那么对象头就会变为  identity_hashcode:25 | age:4 | biased_lock:1 | lock:2 |       Normal
 *                                             00000001 01101110 10100011 11000110 00011011 00000000 (对象头的信息 和 上面的描述是相反的) ，从对象头可以看出来，已经更新为无锁状态
 *
 *                                             epoch值 每进行一次 撤销 就+1
 */

public class Example5 {
    static A a;

    public static void main(String[] args) throws InterruptedException {
        /** TODO jvm 运行开始前因为有许多的同步方法要执行，所以延迟了偏向锁的开启 */
        Thread.sleep(5000);
        a = new A();
        out.println("before lock");
        out.println(ClassLayout.parseInstance(a).toPrintable());

        Thread thread = new Thread(() -> {
            synchronized (a) {
                out.println("lock ing");
                out.println(ClassLayout.parseInstance(a).toPrintable());
            }
        });
        thread.start();
        thread.join();

        out.println("lock end");
        out.println(ClassLayout.parseInstance(a).toPrintable());

        a.hashCode();
        out.println("do hashCode");
        out.println(ClassLayout.parseInstance(a).toPrintable());
    }
}
