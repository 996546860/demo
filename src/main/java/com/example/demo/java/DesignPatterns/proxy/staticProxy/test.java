package com.example.demo.java.DesignPatterns.proxy.staticProxy;

/**
 * 静态代理
 * 1 . 定义抽象行为的类[抽象类,接口]
 * 2 . 定义具体类实现抽象行为
 * 3 . 定义代理类实现和具体类相同接口
 * 4 . 代理类中必须持有实现类对应的引用
 * <p>
 * 缺点
 * 只能代理一类对象
 * 扩展维护比较难
 */
public class test {
    public static void main(String[] args) {
        //无代理调用
        IFunction function = new Button("提交");
        function.click();

        System.out.println("----------------------");

        IFunction proxy = new ButtonProxy(function, "管理者");
        proxy.click();


    }
}
