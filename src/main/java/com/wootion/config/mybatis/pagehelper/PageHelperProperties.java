package com.wootion.config.mybatis.pagehelper;

import lombok.Data;

import java.io.Serializable;

/**
 * PageHelper properties
 * @author ZhangXiaoXiang
 * @date 2018/10/8 22:19
 **/
@Data
public class PageHelperProperties implements Serializable {

    /**
     */
    private static final long serialVersionUID = 1L;

    /**
     * 设置数据库类型 Oracle,Mysql,MariaDB,SQLite,Hsqldb,PostgreSQL六种数据库
     */
    private String dialect;

    /**
     * 设置为true时，会将RowBounds第一个参数offset当成pageNum页码使用,和startPage中的pageNum效果一样
     */
    private String offsetAsPageNum;

    /**
     * 设置为true时，使用RowBounds分页会进行count查询
     */
    private String rowBoundsWithCount;

    /**
     * 设置为true时，如果pageSize=0或者RowBounds.limit = 0就会查询出全部的结果
     */
    private String pageSizeZero;

    /**
     * 启用合理化时，如果pageNum<1会查询第一页，如果pageNum>pages会查询最后一页
     */
    private String reasonable;

    /**
     *
     */
    private String supportMethodsArguments;

    /**
     * always总是返回PageInfo类型,check检查返回类型是否为PageInfo,none返回Page
     */
    private String returnPageInfo;
}