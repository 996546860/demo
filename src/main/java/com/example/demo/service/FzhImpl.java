package com.example.demo.service;

import com.example.demo.auto.RedisCache;
import com.example.demo.service.inteface.IFzh;
import org.springframework.stereotype.Service;

/**
 * @Author: fzh
 * @Date: 2020/8/6 22:39
 * @Content:
 */
@Service
public class FzhImpl  implements IFzh {
    @Override
    @RedisCache(key = "111")
    public void isRedis() {
        System.out.println(11111);
    }
}
