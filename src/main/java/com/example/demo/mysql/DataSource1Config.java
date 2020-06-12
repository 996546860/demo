package com.example.demo.mysql;

/*@Configuration
@MapperScan(basePackages = "com.example.demo.mapper", sqlSessionTemplateRef = "masterSqlSessionTemplate")*/
public class DataSource1Config {

/*    @Bean(name = "master")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    @Primary
    public DataSource testDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "masterSqlSessionFactory")
    @Primary
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("master") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:*.xml"));
        return bean.getObject();
    }

    @Bean(name = "masterTransactionManager")
    @Primary
    public DataSourceTransactionManager testTransactionManager(@Qualifier("master") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "masterSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("masterSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }*/

}