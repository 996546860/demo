package com.example.demo.mybatis;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @author fzh
 * @Date 2019/6/8 12:18
 */
@Configuration
@Component
@Slf4j
//指定拦截哪些方法,update包括增删改
@Intercepts(
        {
           @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
           @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
        }
        )
public class DynamicDataSourceInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0];
        log.info("当前执行的类型：+", ms.getSqlCommandType().equals(SqlCommandType.SELECT) == true ? "select": "update");
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        //增删改查的拦截，然后交由intercept处理
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {

    }
}