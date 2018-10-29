package com.wootion.cast.user.repository;

import com.wootion.cast.user.domain.User;
import com.wootion.config.mybatis.BootRepository;

import java.util.List;

/**
 * @author ZhangXiaoXiang
 * @date 2018/10/8 22:26
 **/
@BootRepository
public interface UserMapper {
    List<User> listAll(User user);
}
