package com.example.demo.java;

import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.*;

public class Test {

/*    final static int MAX_QPS = 10;

    final static Semaphore semaphore = new Semaphore(MAX_QPS);

    public static void main(String[] args) throws Exception {


        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable() {

            @Override

            public void run() {

                semaphore.release(MAX_QPS/2);

            }

        }, 1000, 500, TimeUnit.MILLISECONDS);

        //lots of concurrent calls:100 * 1000
        ExecutorService pool = Executors.newFixedThreadPool(100);

        for (int i = 100; i > 0; i--) {

            final int x = i;

            pool.submit(() -> {

                for (int j = 1000; j > 0; j--) {

                    semaphore.acquireUninterruptibly(1);
                    remoteCall(x, j);

                }

            });

        }

        pool.shutdown();

        pool.awaitTermination(1, TimeUnit.HOURS);

        System.out.println("DONE");

    }

    private static void remoteCall(int i, int j) {
        System.out.println(String.format("%s - %s: %d %d", new Date(),
                Thread.currentThread(), i, j));
    }*/

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

        Thread t = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
        },"fzhg");
        t.start();

        for (int i = 0; i < 5; i++) {
            threadPoolExecutor.execute(t);
        }
        /*HashMap map = new HashMap();

        //map.put(null,null);

        System.out.println(map.get(null));*/
    }

}