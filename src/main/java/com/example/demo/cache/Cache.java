package com.example.demo.cache;

/**
 * @Author: f
 * @Date: 2020/9/26 20:01
 * @Content:
 */
public class Cache implements ICacheService,ICacheConfig {

    @Override
    public void reBuild(String config) {
        System.out.println("重新定义了 config =" + config);
    }

    @Override
    public Object get(String key) {
        System.out.println("获取一个 key = " + key);
        return null;
    }

    @Override
    public void put(String key, String value) {
        System.out.println("存放一个key ="+key+" ,value = " +value);
    }

    @Override
    public void delete(String key) {
        System.out.println("删除一个key = "+ key);
    }
}
