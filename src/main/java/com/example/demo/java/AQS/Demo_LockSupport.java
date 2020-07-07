package com.example.demo.java.AQS;

import java.util.concurrent.locks.LockSupport;

/**
 * @Author: fzh
 * @Date: 2020/6/24 12:00
 * @Content:
 * park()--让线程睡眠
 * 为了线程调度禁用当前线程，除非许可可用。
 * 如果许可可用，则消耗和调用立即返回; 否则当前线程用于线程调度目的，禁用并一直处于休眠状态的三种情况之一发生：
 * 其他某些线程调用unpark与当前线程作为目标; 要么
 * 其他某些线程中断当前线程; 要么
 * 该调用不合逻辑地（即毫无理由地）返回。
 * 这种方法不报告是哪个线程导致返回方法。 调用者应该重新检查造成线程公园摆在首位的条件。 调用者还可以确定，例如，该线程的中断状态返回时
 *
 * unpark(thread) -- 唤醒指定的线程
 * 使现有的许可证给定线程的，如果它不是已经可用。 如果线程被阻塞在park那么就会解除。 否则，它的下一个呼叫park是保证不被阻塞。 此操作不保证带任何效果如果给定线程尚未启动。
 */
public class Demo_LockSupport {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()-> {
            System.out.println("线程即将被关闭执行");
            LockSupport.park();
            System.out.println("线程重新开始工作");
        });
        thread.start();
        Thread.sleep(4000);
        System.out.println("线程唤醒执行");
        LockSupport.unpark(thread);
    }
}
