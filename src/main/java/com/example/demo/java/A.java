package com.example.demo.java;

import org.openjdk.jol.info.ClassLayout;

public class A {
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(5000);
        B b = new B();
        System.out.println(ClassLayout.parseInstance(b).toPrintable());

        Thread t = new Thread(b,"牛逼的接口");
        t.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        b.stop();
        t.join();
    }
}
class B implements Runnable {
    private  boolean flag = true;
    @Override
    public void run() {

        System.out.println("开始");
        flag = true;
        while(flag)
        {
        }
        System.out.println("结束");
    }
    public void stop() {
        this.flag  = !this.flag;
    }
}