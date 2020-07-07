package com.example.demo.java.example;

/**
 * @Author: fzh
 * @Date: 2020/6/30 23:33
 * @Content: why while 在这个时候为什么不会停止?
 */
public class Example9 {

    private static boolean flag = true;
    public static void main(String[] args) {

        new Thread(() -> {
            while (flag) {

            }
        }).start();

        flag = false;
    }




}
