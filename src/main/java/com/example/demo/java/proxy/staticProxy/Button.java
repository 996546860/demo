package com.example.demo.java.proxy.staticProxy;

import com.example.demo.java.proxy.staticProxy.IFunction;

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
