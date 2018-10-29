package com.wootion.cast.vehcert.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Enterprise implements Serializable {
    private Long id;
    private String enterpId;
    private String enterpCode;
    private String enterpName;
    private String tcpCode;
    private String dress;
    private String enterpOther;
    private String factory;
}
