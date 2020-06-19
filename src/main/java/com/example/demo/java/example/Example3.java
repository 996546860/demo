package com.example.demo.java.example;
import org.openjdk.jol.info.ClassLayout;

import static java.lang.System.out;
import static java.lang.System.err;
/**
 * @Author: fzh
 * @Date: 2020/6/18 21:57
 * @Content: 可以看出来 虽然线程 交替执行 锁并没有升级 (少数情况下)
 *   这个不是重偏向吧
 *   java thread == linux os thread 有可能Os线程结束的时候， 101 并没有被使用，而且 101 所持有的线程已经结束了 ，那么t1 线程可能就会 直接拿到这个 linux os分配的线程
 *   感觉 os 提供的线程方法 JVM 会复用 或者 。。。
 *   请看 demo 7
 * com.example.demo.java.example.A object internals:
 *  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
 *       0     4        (object header)                           05 e0 d7 1f (00000101 11100000 11010111 00011111) (534241285)
 *       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
 *       8     4        (object header)                           43 c1 00 f8 (01000011 11000001 00000000 11111000) (-134168253)
 *      12     4        (loss due to the next object alignment)
 * Instance size: 16 bytes
 * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
 *
 * com.example.demo.java.example.A object internals:
 *  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
 *       0     4        (object header)                           05 e0 d7 1f (00000101 11100000 11010111 00011111) (534241285)
 *       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
 *       8     4        (object header)                           43 c1 00 f8 (01000011 11000001 00000000 11111000) (-134168253)
 *      12     4        (loss due to the next object alignment)
 * Instance size: 16 bytes
 * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
 *
 * com.example.demo.java.example.A object internals:
 *  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
 *       0     4        (object header)                           05 e0 d7 1f (00000101 11100000 11010111 00011111) (534241285)
 *       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
 *       8     4        (object header)                           43 c1 00 f8 (01000011 11000001 00000000 11111000) (-134168253)
 *      12     4        (loss due to the next object alignment)
 * Instance size: 16 bytes
 * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
 */
public class Example3 {
    static A a ;
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(5000);
        a = new A();
        Thread t1 = new Thread(() -> {
            synchronized (a) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                out.println("t1 ing");
                out.println(ClassLayout.parseInstance(a).toPrintable());
            }
        });
        t1.start();
        t1.join();
        /**
         * TODO 重点
         */
        Thread thread = new Thread(() -> {
            out.println("空线程");
        });
        thread.start();

        Thread t2 = new Thread(() -> {
            synchronized (a) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                out.println("t2 ing");
                out.println(ClassLayout.parseInstance(a).toPrintable());

            }
        });
        t2.start();
        t2.join();
        Thread t3 = new Thread(() -> {
            synchronized (a) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                out.println("t3 ing");
                out.println(ClassLayout.parseInstance(a).toPrintable());
            }
        });
        t3.start();
        t3.join();
    }
}
