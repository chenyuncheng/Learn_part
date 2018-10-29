package com.wootion.cast.user.rest;

import com.wootion.cast.user.domain.User;
import com.wootion.cast.user.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ZhangXiaoXiang
 * @date 2018/10/8 22:30
 **/
@RestController
public class UserRestController {

    private UserService userService;
    public UserRestController(UserService userService){

        this.userService = userService;
    }

    @GetMapping(value = "/")
    public List<User> listAll(User user){

        return userService.listAll(user);
    }
}
