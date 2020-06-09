package com.example.demo.java.proxy.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理 基于 反射机制
 * 动态代理只能代理实现了接口的类 原因是由于JDK动态代理
 */
public class ObjectProxy {
    //持有目标代理类
    private Object target;

    public ObjectProxy() {
    }

    public ObjectProxy(Object target) {
        this.target = target;
    }

    /**
     * 定义生成目标对象代理对象的方法
     */
    public Object createProxy() {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new Handler());
    }

    /**
     * 定义回调处理内部类
     */
    private class Handler implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.err.println("method " + method);
            System.err.println("args " + args);
            validateLogin();
            validateRole();
            Object obj = method.invoke(target, args);
            requestDate();
            return obj;
        }
    }

    private void validateLogin() {
        System.out.println("验证登录...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("登录验证成功...");
    }

    private void validateRole() {
        System.out.println("验证角色...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("角色验证成功...");
    }

    private void requestDate() {
        System.out.println("开始请求数据...");
    }


}
