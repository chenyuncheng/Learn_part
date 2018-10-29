package com.wootion.config.mybatis;

import lombok.Data;

import java.io.Serializable;

/**
 * mybatis配置属性信息
 * @author ZhangXiaoXiang
 * @date 2018-03-10
 */
@Data
public class MybatisProperties implements Serializable {

    /**
     */
    private static final long serialVersionUID = 1L;

    /**
     * 当mybatis的xml文件和mapper接口不在相同包下时，需要用mapperLocations属性指定xml文件的路径。
     * *是个通配符，代表所有的文件，**代表所有目录下
     */
    private String mapperLocations;

    /**
     * 它一般对应我们的实体类所在的包，这个时候会自动取对应包中不包括包名的简单类名作为包括包名的别名。
     * 多个package之间可以用逗号或者分号等来进行分隔。(value的值一定要是包的全名)
     */
    private String typeAliasesPackage;

    /**
     * 扫描包
     */
    private String basePackage;

    /**
     * 用来指定TypeHandler所在的包，如果指定了该属性，SqlSessionFactoryBean会自动把该包下面的类注册为对应的TypeHandler。
     * 多个package之间可以用逗号或者分号等来进行分隔
     */
    private String typeHandlersPackage;
}