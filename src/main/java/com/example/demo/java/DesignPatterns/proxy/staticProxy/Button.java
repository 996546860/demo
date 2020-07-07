package com.example.demo.java.DesignPatterns.proxy.staticProxy;

public class Button implements IFunction {

    private String btName;

    public Button(String btName) {
        this.btName = btName;
    }

    @Override
    public void click() {
        System.out.println(this.btName + "按钮点击了...");
    }


}
