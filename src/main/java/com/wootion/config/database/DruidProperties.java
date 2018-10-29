package com.wootion.config.database;
import lombok.Data;

import java.io.Serializable;


/**
 * @author XZhang
 * @date 2018-05-03
 */
@Data
public class DruidProperties implements Serializable{

    /**
     */
    private static final long serialVersionUID = 1L;
    /**
     * DataSource address
     */
    private String dbUrl;
    /**
     * DataBase username
     */
    private String username;
    /**
     * DataBase password
     */
    private String password;
    /**
     * DataBase driver class
     */
    private String driverClassName;
    /**
     * DataBase init size
     */
    private int initialSize;
    /**
     * Database max active
     */
    private int maxActive;
    /**
     * Get DataBase max wait time
     */
    private int maxWait;
    /**
     *The Destroy thread detects the interval between connections.
     */
    private int timeBetweenEvictionRunsMillis;
    /**
     *
     */
    private int minEvictableIdleTimeMillis;
    /**
     * SQL used to check whether the connection is valid or not.
     */
    private String validationQuery;
    /**
     *申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。
     */
    private boolean testWhileIdle;
    /**
     * 是否缓存preparedStatement，也就是PSCache。PSCache对支持游标的数据库性能提升巨大，比如说oracle,在mysql下建议关闭
     */
    private boolean poolPreparedStatements;
    /**
     * 要启用PSCache，必须配置大于0，当大于0时，poolPreparedStatements自动触发修改为true,在Druid中,不会存在Oracle下PSCache占用内存过多的问题，可以把这个数值配置大一些，比如说100
     */
    private int maxPoolPreparedStatementPerConnectionSize;
    /**
     * 属性类型是字符串，通过别名的方式配置扩展插件，常用的插件有：
     * 监控统计用的filter:stat日志用的filter:log4j防御sql注入的filter:wall
     */
    private String filters;
    /**
     * 物理连接初始化的时候执行的sql
     */
    private String connectionProperties;
}