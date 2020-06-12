package com.example.demo.mysql;


import org.apache.ibatis.executor.keygen.SelectKeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Properties;

/*@Intercepts(
        {@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
                @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
        }
)
@Configuration*/
public class DataSourcePlugin implements Interceptor {


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //判断当前Sql是否存在事务
        boolean active = TransactionSynchronizationManager.isActualTransactionActive();
        //routeKey默认是MASTER（主库）
        String routeKey = DynamicDataSourceHolder.DB_MASTER;
        Object[] objects = invocation.getArgs();//第一个参数类型为MappedStatement对象, 第二个传入是参数
        MappedStatement statement = (MappedStatement) objects[0];

        if (!active) {//当前不是事务操作
            //判断读方法
            if (statement.getSqlCommandType().equals(SqlCommandType.SELECT)) {
                // 如果在sql中使用了 select last_insert_id 函数 那么就是 MASTER
                if (statement.getId().contains(SelectKeyGenerator.SELECT_KEY_SUFFIX)) {
                    routeKey = DynamicDataSourceHolder.DB_MASTER;
                } else {
                    routeKey = DynamicDataSourceHolder.DB_SLAVE;
                }
            } else {
                routeKey = DynamicDataSourceHolder.DB_MASTER;
            }
        } else {//当前是事务操作
            routeKey = DynamicDataSourceHolder.DB_MASTER;
        }
        //设置具体的routKey
        DynamicDataSourceHolder.setRouteKey(routeKey);
        System.out.println("当前的数据源是 ：" + routeKey);
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

}
