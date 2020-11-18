package com.example.demo;

import com.example.demo.demo.User;
import com.sun.corba.se.impl.resolver.SplitLocalResolverImpl;

import java.util.function.Supplier;

public class Finalization {
   /* public static Finalization finalization;

    @Override
    protected void finalize() {
        System.out.println("Finalized");
        finalization = this;
    }*/

    public static void main(String[] args) {


        /*Finalization f = new Finalization();
        System.out.println("First print: " + f);
        f = null;
        System.gc();//异步
        try {// 休息一段时间，让上面的垃圾回收线程执行完成
            System.out.println("正在休息");
            Thread.currentThread().sleep(1000);
            System.out.println("休息结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Second print: " + f);
        System.out.println(f.finalization);
        System.out.println(f);*/
        //上海 get certified

            System.out.println("hi itstack-demo-agent-01");

        //january
        //Shanghai baiqiu e-commerce Co., Ltd
    }


    Object getString (Supplier<Object> supplier) {

        return supplier.get();
    }
}
