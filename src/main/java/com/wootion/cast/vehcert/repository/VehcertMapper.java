package com.wootion.cast.vehcert.repository;

import com.wootion.cast.vehcert.domain.Vehcert;
import com.wootion.config.mybatis.BootRepository;

import java.util.List;

@BootRepository
public interface VehcertMapper {

    List<Vehcert> findVehcert(Vehcert vehcert);

}
