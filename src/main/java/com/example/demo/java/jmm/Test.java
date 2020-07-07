package com.example.demo.java.jmm;

/**
 * @Author: fzh
 * @Date: 2020/7/1 20:48
 * @Content:
 */
public class Test {

    public static void main(String[] args) {
        // cpu1
        int a = 1;
        // cpu2
         int b = 2;
         // cpu 3
          int d  = a+b;
          //d = ?
        // 1 硬件保证缓存一致性 mesi协议
        // 2 sun JMM 公司保证 为了保证 java结果在不同的硬件上，结果是相同
    }
}
