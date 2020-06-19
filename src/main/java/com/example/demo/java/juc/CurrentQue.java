package com.example.demo.java.juc;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @Author: fzh
 * @Date: 2020/6/15 21:43
 * @Content:
 */
public class CurrentQue {
    private static Queue<Integer> queue = new ConcurrentLinkedQueue<>();


    static {
        for (int i = 0; i < 100; i++) {
            queue.add(i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    Integer poll = queue.poll();
                    if (poll == null) {
                        break;
                    }
                    System.out.println(poll);
                }

            }).start();
        }
    }
}
