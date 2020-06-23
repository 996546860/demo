package com.example.demo.java.juc;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Author: fzh
 * @Date: 2020/6/15 22:40
 * @Content: 阻塞列 put 加 take 拿
 *                  add 加 超过 直接报错
 *                  offer 加入，不过可以判断 是否加入成功 以及 设置加入时间
 */
public class BlockingQueueTest {
    /***TODO 无解队列 Integer.MAXVALUE**/
    static BlockingQueue<String> queue = new LinkedBlockingQueue<>();
    /**
     * TODO new ArrayBlockingQueue<>();  有界队列
     * TODO new DelayQueue<>;  延迟队列 需要百度学习
     * TODO new LinkedTransferQueue<>();  百度学习一下
     * TODO new ArrayBlockingQueue<>();  有界队列
     * TODO new DelayQueue<>;  延迟队列 需要百度学习
     * TODO new LinkedTransferQueue<>(); 百度学习一下
     * TODO new SynchronousQueue<>(); 容量始终为0 不能用add;
     *
     * */
    public static void main(String[] args) {
        long time1 = System.currentTimeMillis();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    queue.put("fzh"+i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(100);
                    System.out.println(queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        long time2 = System.currentTimeMillis();

    }
}
