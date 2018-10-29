package com.wootion.cast.user.service;

import com.wootion.cast.user.domain.User;
import com.wootion.cast.user.repository.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ZhangXiaoXiang
 * @date 2018/10/8 22:27
 **/
@Service
public class UserService {

    private UserMapper userMapper;
    public UserService(UserMapper userMapper){

        this.userMapper = userMapper;
    }

    public List<User> listAll(User user){

        return userMapper.listAll(user);
    }
}
