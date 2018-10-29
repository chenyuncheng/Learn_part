package com.wootion.cast.user.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ZhangXiaoXiang
 * @date 2018/10/8 22:22
 **/
@Data
public class User implements Serializable {
    /**
     */
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
}
