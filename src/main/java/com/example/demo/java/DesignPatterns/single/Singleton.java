package com.example.demo.java.DesignPatterns.single;

/**
 * 饿汉式单例类不能实现延迟加载，不管将来用不用始终占据内存；
 * 懒汉式单例类线程安全控制烦琐，而且性能受影响。
 * 有种更好的单例模式叫做Initialization Demand Holder (IoDH)的技术。
 */
public class Singleton {

    private Singleton(){
        System.out.println(Singleton.class);
    }
    
    private static class HolderClass{
        private final static Singleton s = new Singleton();
    }
    
    public static Singleton getInstance(){
        return HolderClass.s;
    }
    
    
    public static void main(String[] args) {
        Singleton s = Singleton.getInstance();
    }
    
}