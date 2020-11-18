package com.example.demo.cache;

/**
 * @Author: f
 * @Date: 2020/9/26 20:35
 * @Content: 不对外开放
 */
public interface ICacheConfig {

    /**
     * 重新设置cache配置
     * @param config
     */
    void reBuild(String config);
}
