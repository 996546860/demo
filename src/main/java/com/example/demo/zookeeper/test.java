package com.example.demo.zookeeper;


public class test {
    static int n = 500;

    public static void secskill() {
        try {
            java.lang.Thread.currentThread().sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.err.println(--n);
    }

    public static void main(String[] args) {
        /*DistributedLock lock = new DistributedLock("120.79.76.167:2181", "test1");
        if(null!=lock){

        }*/

        new Thread(() ->{
            System.out.println(111);
        }).start();
        /*Runnable runnable = new Runnable() {
            public void run() {
                try {
                    lock.lock();
                    secskill();
                    //System.out.println(Thread.currentThread().getName() + "正在运行");
                } finally {
                    if (lock != null) {
                        lock.unlock();
                    }
                }
            }
        };

        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(runnable);
            t.start();
        }*/
    }

}
