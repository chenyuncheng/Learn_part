package com.wootion.config.database;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.sql.SQLException;

/**
 * DataSource Configuration
 * @author zhangxiaoxiang
 * @date 2018-05-03
 */
@Configuration
@PropertySource("classpath:dataSource.properties")
public class DruidConfig {

    /**
     * DataSource Properties
     * @return datasource properties
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.druid")
    public DruidProperties druidProperties() {

        return new DruidProperties();
    }

    /**
     * 在同样的DataSource中，首先使用被标注的DataSource
     * @return DruidDataSource
     */
    @Bean(value = "druidDataSource",initMethod = "init",destroyMethod = "close")
    public DruidDataSource dataSource() {

        DruidDataSource dataSource = new DruidDataSource();
        try {
            dataSource.setUrl(druidProperties().getDbUrl());
            dataSource.setUsername(druidProperties().getUsername());
            dataSource.setPassword(druidProperties().getPassword());
            dataSource.setDriverClassName(druidProperties().getDriverClassName());

            System.out.println("[druidDB Start.....]");
            System.out.println("[数据库连接地址:"+druidProperties().getDbUrl()+"]");
            System.out.println("[数据库连接用户:"+druidProperties().getUsername()+"]");
            System.out.println("[数据库连接密码:"+druidProperties().getPassword()+"]");
            System.out.println();

            //configuration
            dataSource.setInitialSize(druidProperties().getInitialSize());
            dataSource.setMaxActive(druidProperties().getMaxActive());
            dataSource.setMaxWait(druidProperties().getMaxWait());
            dataSource.setTimeBetweenEvictionRunsMillis(druidProperties().getTimeBetweenEvictionRunsMillis());
            dataSource.setMinEvictableIdleTimeMillis(druidProperties().getMinEvictableIdleTimeMillis());
            dataSource.setValidationQuery(druidProperties().getValidationQuery());
            dataSource.setTestWhileIdle(druidProperties().isTestWhileIdle());
            dataSource.setPoolPreparedStatements(druidProperties().isPoolPreparedStatements());
            dataSource.setMaxPoolPreparedStatementPerConnectionSize(druidProperties().getMaxPoolPreparedStatementPerConnectionSize());
            dataSource.setFilters(druidProperties().getFilters());
        } catch (SQLException e) {
            System.out.println("Database connection exception !");
        }
            return dataSource;
    }
}