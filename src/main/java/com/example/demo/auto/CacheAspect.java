package com.example.demo.auto;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author: fzh
 * @Date: 2019/8/15 13:01
 * @Content: 缓存处理类
 */
@Aspect
@Slf4j
@Component
public class CacheAspect {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Pointcut("@annotation(com.shandiancheng.common.annotation.RedisCache)")
    public void poinCut() {
        System.out.println("切点");
    }

    @Around(value = "@annotation(redisCache)")
    public Object around(ProceedingJoinPoint pjp, RedisCache redisCache) throws Throwable {
        System.out.println("进入切面缓存");
        Object proceed = pjp.proceed();
        System.out.println("执行结束");
        return proceed;
    }
}
