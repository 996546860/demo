package com.example.demo.mysql;

import org.slf4j.LoggerFactory;

import java.util.logging.Logger;

public class DynamicDataSourceHolder {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(DynamicDataSourceHolder.class);
    //多线程中 保证线程安全
    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();
    //主数据
    public static final String DB_MASTER = "master";
    //从数据库
    public static final String DB_SLAVE = "slave";

    /**
     * 获取路由KEy
     *
     * @return
     */
    public static String getRouteKey() {
        String routeKey = threadLocal.get();
        if (routeKey == null) {
            routeKey = DB_MASTER;
        }
        return routeKey;
    }

    /**
     * 定义一个数据源
     *
     * @param routeKey
     */
    public static void setRouteKey(String routeKey) {
        threadLocal.set(routeKey);

        System.out.println("当前的数据源为: " + routeKey);
    }


}
