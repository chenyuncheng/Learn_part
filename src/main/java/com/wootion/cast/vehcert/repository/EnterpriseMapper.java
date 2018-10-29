package com.wootion.cast.vehcert.repository;

import com.wootion.cast.vehcert.domain.Enterprise;
import com.wootion.config.mybatis.BootRepository;

import java.util.List;

@BootRepository
public interface EnterpriseMapper {


    List<Enterprise> findEnterprise(Enterprise enterprise);

}
