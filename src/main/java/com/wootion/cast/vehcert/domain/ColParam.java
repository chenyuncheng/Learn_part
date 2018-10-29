package com.wootion.cast.vehcert.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class ColParam implements Serializable {
    private Long id;
    private String cateCode;
    private String colorCode;
    private String colorName;
    private String picName;
    private String picUrl;
    private String factory;

}
