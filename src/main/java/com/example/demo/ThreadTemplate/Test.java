package com.example.demo.ThreadTemplate;

/**
 * @Author: fzh
 * @Date: 2020/9/25 16:37
 * @Content:
 */
public class Test {
    public static void main(String[] args) {
        for (int i = 0; i < 200; i++) {
            ExecuteService.execute(() -> {
                System.out.println(1);
            });

            /*CustomizeThread customizeThread = new CustomizeThread().setRunnable(() -> {
                System.out.println(1);
            }).setThreadName("傻逼");*/
        }

    }
}
