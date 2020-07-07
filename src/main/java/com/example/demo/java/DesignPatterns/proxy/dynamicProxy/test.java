package com.example.demo.java.DesignPatterns.proxy.dynamicProxy;

public class test {
    public static void main(String[] args) {
        //创建目标代理对象 1- 无代理使用
        IFunction target = new Window("模态窗口");
        //target.click();
        //target.dbClick();

        //创建代理对象  代理使用
        ObjectProxy oob = new ObjectProxy(target);
        //调用创建目标代理对象的方法[只能装换为接口]
        IFunction proxy = (IFunction) oob.createProxy();
        proxy.click();
        proxy.dbClick();

    }
}
