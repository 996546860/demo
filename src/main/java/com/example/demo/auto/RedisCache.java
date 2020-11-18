package com.example.demo.auto;

import java.lang.annotation.*;

/**
 * @Author: fzh
 * @Date: 2020/8/6 20:56
 * @Content:
 */
@Target({ElementType.PARAMETER, ElementType.METHOD,ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisCache {

    String key();
}
