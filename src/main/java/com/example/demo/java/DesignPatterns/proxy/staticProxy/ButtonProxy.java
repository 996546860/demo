package com.example.demo.java.DesignPatterns.proxy.staticProxy;

public class ButtonProxy implements IFunction {

    // 持有对象代理的引用
    private IFunction iFunction;

    //定义角色名称
    private String roleName;

    public ButtonProxy(IFunction function, String roleName) {
        this.iFunction = function;
        this.roleName = roleName;
    }

    @Override
    public void click() {
        if ("管理者".equals(roleName)) {
            iFunction.click();
        } else {
            System.out.println("没有权限点击按钮...");
        }
    }


}
