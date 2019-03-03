package com.example.demo.mysql;


import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源配置
 */
public class DataSourceConfig extends AbstractRoutingDataSource {

    //在spring 容器中查询对应的 key 来应用数据源
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceHolder.getRouteKey();
    }
}
