package com.example.demo.cache;

/**
 * @Author: f
 * @Date: 2020/9/26 20:10
 * @Content: cache 对外壳一调用的接口 对外开放
 */
public interface ICacheService {

    /**
     * 获取数据
     * @param key
     * @return
     */
    Object get(String key);

    /**
     * 放入数据
     * @param key
     * @param value
     */
    void put(String key,String value);

    /**
     * 删除数据
     * @param key
     */
    void delete(String key);

}
