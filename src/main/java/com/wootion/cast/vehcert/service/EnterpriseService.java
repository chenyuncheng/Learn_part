package com.wootion.cast.vehcert.service;

import com.wootion.cast.vehcert.domain.Enterprise;
import com.wootion.cast.vehcert.repository.EnterpriseMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnterpriseService {

    private EnterpriseMapper enterpriseMapper;
    public EnterpriseService(EnterpriseMapper enterpriseMapper){

        this.enterpriseMapper = enterpriseMapper;
    }

    public List<Enterprise> getEnterprise(Enterprise enterprise){

        return enterpriseMapper.getEnterprise(enterprise);
    }
}
