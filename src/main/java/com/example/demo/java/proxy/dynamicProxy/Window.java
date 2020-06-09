package com.example.demo.java.proxy.dynamicProxy;

public class Window implements IFunction {

    private String name;

    public Window(String name) {
        this.name = name;
    }

    @Override
    public void click() {
        System.out.println(this.name + "被单击了");
    }

    @Override
    public void dbClick() {
        System.out.println(this.name + "被双击了");
    }

    @Override
    public String getName() {
        return name;
    }
}
