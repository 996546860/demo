package com.example.demo.mybatis;

import com.example.demo.auto.SqlInterceptor;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.io.StringReader;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zsj
 * @version 1.0.0
 * @description MybatisInterceptorConfig
 * @date 2020/6/22
 */
/*@Configuration
@Component
@Intercepts(
        {
                @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
                @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
        }
)*/
public class MybatisInterceptorConfig implements Interceptor {


    @Autowired
    private ApplicationContext context;
    private  volatile  AtomicInteger counter = new AtomicInteger(1);
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        SqlInterceptor sqlInterceptor = getSqlInterceptor(mappedStatement);
        if (Objects.isNull(sqlInterceptor)) {
            return invocation.proceed();
        }
//        DataPermissionSqlParameter sqlParameter = new DataPermissionSqlParameter();
  //      String paramSQL = sqlParameter.getParamSQL(sqlInterceptor.table());
        String paramSQL = "1=1";
        if (Objects.isNull(paramSQL)) {
            return invocation.proceed();
        }
        BoundSql boundSql = mappedStatement.getBoundSql(invocation.getArgs()[1]);
        String currentSql = boundSql.getSql();
        String authSql = makeSql(currentSql, sqlInterceptor, paramSQL);
        MappedStatement newStatement = newMappedStatement(mappedStatement, new BoundSqlSqlSource(boundSql));
        MetaObject msObject = MetaObject.forObject(newStatement, new DefaultObjectFactory(), new DefaultObjectWrapperFactory(), new DefaultReflectorFactory());
        msObject.setValue("sqlSource.boundSql.sql", authSql);
        invocation.getArgs()[0] = newStatement;
        return invocation.proceed();

    }
    private MappedStatement newMappedStatement(MappedStatement ms, SqlSource newSqlSource) {
        MappedStatement.Builder builder =
                new MappedStatement.Builder(ms.getConfiguration(), ms.getId(), newSqlSource, ms.getSqlCommandType());
        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        if (ms.getKeyProperties() != null && ms.getKeyProperties().length != 0) {
            StringBuilder keyProperties = new StringBuilder();
            for (String keyProperty : ms.getKeyProperties()) {
                keyProperties.append(keyProperty).append(",");
            }
            keyProperties.delete(keyProperties.length() - 1, keyProperties.length());
            builder.keyProperty(keyProperties.toString());
        }
        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());
        builder.resultMaps(ms.getResultMaps());
        builder.resultSetType(ms.getResultSetType());
        builder.cache(ms.getCache());
        builder.flushCacheRequired(ms.isFlushCacheRequired());
        builder.useCache(ms.isUseCache());

        return builder.build();
    }

    private class BoundSqlSqlSource implements SqlSource {
        private BoundSql boundSql;

        public BoundSqlSqlSource(BoundSql boundSql) {
            this.boundSql = boundSql;
        }

        @Override
        public BoundSql getBoundSql(Object parameterObject) {
            return boundSql;
        }
    }
    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }

    }

    @Override
    public void setProperties(Properties properties) {

    }
    /**
     * 通过反射获取mapper方法是否加了自定义注解
     */
    private SqlInterceptor getSqlInterceptor(MappedStatement mappedStatement) throws ClassNotFoundException {
        SqlInterceptor sqlInterceptor = null;
        String id = mappedStatement.getId();
        String className = id.substring(0, id.lastIndexOf("."));
        String methodName = id.substring(id.lastIndexOf(".") + 1);
        final Class<?> cls = Class.forName(className);
        final Method[] methods = cls.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName) && method.isAnnotationPresent(SqlInterceptor.class)) {
                sqlInterceptor = method.getAnnotation(SqlInterceptor.class);
                break;
            }
        }
        return sqlInterceptor;
    }
    private String makeSql(String sql, SqlInterceptor sqlInterceptor,String sqlParameter) throws JSQLParserException {
        CCJSqlParserManager parserManager = new CCJSqlParserManager();
        Select select = (Select) parserManager.parse(new StringReader(sql));
        PlainSelect plain = (PlainSelect) select.getSelectBody();
        Table fromItem = (Table) plain.getFromItem();
        //有别名用别名，无别名用表名，防止字段冲突报错
        String mainTableName = fromItem.getAlias() == null ? fromItem.getName() : fromItem.getAlias().getName();
        //构建子查询
        String dataAuthSql = mainTableName +sqlParameter;
        if (plain.getWhere() == null) {
            plain.setWhere(CCJSqlParserUtil.parseCondExpression(dataAuthSql));
        } else {
            plain.setWhere(new AndExpression(plain.getWhere(), CCJSqlParserUtil.parseCondExpression(dataAuthSql)));
        }
        return select.toString();
    }
}
