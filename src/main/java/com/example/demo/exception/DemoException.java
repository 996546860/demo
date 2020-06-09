package com.example.demo.exception;

/**
 * 自定义异常 使用类
 */
public class DemoException extends AutoException {


    public DemoException(String message) {
        super(message);
        this.message = message;
    }

}
