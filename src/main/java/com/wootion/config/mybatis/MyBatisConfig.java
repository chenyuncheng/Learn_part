package com.wootion.config.mybatis;

import java.io.IOException;
import java.util.Properties;


import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.github.pagehelper.PageInterceptor;
import com.wootion.config.mybatis.pagehelper.PageHelperProperties;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * mybatis配置
 * @author admin
 */
@Component
@Configuration
@EnableTransactionManagement
@PropertySource("classpath:dataSource.properties")
public class MyBatisConfig {

    private DruidDataSource druidDataSource;

    public MyBatisConfig(DruidDataSource druidDataSource){

        this.druidDataSource=druidDataSource;
    }

    @Bean
    @ConfigurationProperties(prefix ="jdbc.mybatis")
    public MybatisProperties myBatisProperties() {

        return new MybatisProperties();
    }

    /**
     * 配置factory
     * @param myBatisProperties mybatis配置属性信息
     * @return SqlSessionFactory
     * @throws Exception 抛出异常信息
     */
    @Bean(name = "SqlSessionFactory")
    @Autowired
    public SqlSessionFactory sqlSessionFactory(MybatisProperties myBatisProperties) throws Exception{

        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(druidDataSource);
        // 包扫描域对象
        bean.setTypeAliasesPackage(myBatisProperties.getTypeAliasesPackage());
        //包扫描处理器
        bean.setTypeHandlersPackage(myBatisProperties.getTypeHandlersPackage());
        //MP 全局配置注入
        bean.setGlobalConfig(globalConfig());
        // 添加插件
        bean.setPlugins(new Interceptor[] { pageInterceptor() });

        try {
            //添加XML目录
            bean.setMapperLocations(resolver.getResources(myBatisProperties.getMapperLocations()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bean.getObject();
    }

    @Bean(name = "SqlSession")
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {

        return new SqlSessionTemplate(sqlSessionFactory);
    }

    /**
     * 开启事务
     * @return dataSourceTransactionManager
     */
    @Bean(name = "Transaction")
    public DataSourceTransactionManager dataSourceTransactionManager() {
        return new DataSourceTransactionManager(druidDataSource);
    }

    /**
     * 分页插件
     * @return PageInterceptor
     */
    private PageInterceptor pageInterceptor(){
        // 分页插件
        PageInterceptor pageHelper = new PageInterceptor();
        Properties properties = new Properties();

        PageHelperProperties page = new PageHelperProperties();
        if (StringUtils.isNotBlank(page.getDialect())) {
            properties.setProperty("dialect", page.getDialect());
        }
        // <!-- 该参数默认为false -->
        // <!-- 设置为true时，会将RowBounds第一个参数offset当成pageNum页码使用 -->
        // <!-- 和startPage中的pageNum效果一样-->
        if (StringUtils.isNotBlank(page.getOffsetAsPageNum())) {
            properties.setProperty("offsetAsPageNum", page.getOffsetAsPageNum());
        }

        // <!-- 该参数默认为false -->
        // <!-- 设置为true时，使用RowBounds分页会进行count查询 -->
        if (StringUtils.isNotBlank(page.getRowBoundsWithCount())) {
            properties.setProperty("rowBoundsWithCount", page.getRowBoundsWithCount());
        }

        // <!-- 设置为true时，如果pageSize=0或者RowBounds.limit = 0就会查询出全部的结果 -->
        // <!-- （相当于没有执行分页查询，但是返回结果仍然是Page类型）-->
        if (StringUtils.isNotBlank(page.getPageSizeZero())) {
            properties.setProperty("pageSizeZero", page.getPageSizeZero());
        }

        // <!-- 3.3.0版本可用 - 分页参数合理化，默认false禁用 -->
        // <!-- 启用合理化时，如果pageNum<1会查询第一页，如果pageNum>pages会查询最后一页 -->
        // <!-- 禁用合理化时，如果pageNum<1或pageNum>pages会返回空数据 -->
        if (StringUtils.isNotBlank(page.getReasonable())) {
            properties.setProperty("reasonable", page.getReasonable());
        }

        // <!-- 3.5.0版本可用 - 为了支持startPage(Object params)方法 -->
        // <!-- 增加了一个`params`参数来配置参数映射，用于从Map或ServletRequest中取值 -->
        // <!-- 可以配置pageNum,pageSize,count,pageSizeZero,reasonable,orderBy,不配置映射的用默认值 -->
        // <!-- 不理解该含义的前提下，不要随便复制该配置 -->
        // <!-- properties.setProperty("params", "pageNum=start;pageSize=limit;") -->
        // <!--支持通过Mapper接口参数来传递分页参数-->
        if (StringUtils.isNotBlank(page.getSupportMethodsArguments())) {
            properties.setProperty("supportMethodsArguments", page.getSupportMethodsArguments());
        }

        // <!-- always总是返回PageInfo类型,check检查返回类型是否为PageInfo,none返回Page -->
        if (StringUtils.isNotBlank(page.getReturnPageInfo())) {
            properties.setProperty("returnPageInfo", page.getReturnPageInfo());
        }

        pageHelper.setProperties(properties);
        return pageHelper;
    }

    /**
     * 定义MP全局策略
     * @return GlobalConfig
     */
    private GlobalConfig globalConfig(){
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setBanner(false);
        globalConfig.setDbConfig(dbConfig());
        return globalConfig;
    }

    /**
     * 数据源配置
     * @return DbConfig
     */
    private GlobalConfig.DbConfig dbConfig(){
        GlobalConfig.DbConfig dbConfig = new GlobalConfig.DbConfig();
        dbConfig.setDbType(DbType.MYSQL);
        //全局的主键策略
        dbConfig.setIdType(IdType.AUTO);
        return dbConfig;
    }
}