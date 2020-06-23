package com.example.demo.auto;

/**
 * @Author: fzh
 * @Date: 2020/6/22 21:07
 * @Content:
 */
public @interface SqlInterceptor {
    boolean value() default false;

    String table() default "user";
}
