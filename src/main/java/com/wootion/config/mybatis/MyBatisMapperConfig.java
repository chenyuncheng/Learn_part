package com.wootion.config.mybatis;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatisMapperConfig
 * Mapper的映射配置信息
 * @author Admin
 * @Date 00:00:00 2018-05-03
 */
@Configuration
@AutoConfigureAfter(MyBatisConfig.class)
public class MyBatisMapperConfig {
    @Bean
    public static MapperScannerConfigurer mapperScannerConfigurer() {

        MapperScannerConfigurer conf= new MapperScannerConfigurer();
        conf.setBasePackage("com.wootion.cast.**.repository");
        conf.setSqlSessionFactoryBeanName("SqlSessionFactory");
        conf.setAnnotationClass(BootRepository.class);

        return conf;
    }
}