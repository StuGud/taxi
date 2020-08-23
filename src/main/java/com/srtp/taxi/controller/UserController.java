package com.srtp.taxi.controller;

import com.srtp.taxi.entity.Driver;
import com.srtp.taxi.entity.User;
import com.srtp.taxi.service.UserService;
import com.srtp.taxi.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    //PostMapping
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String loginUser(String username,String password){
        User loginUser = userService.login(new User(null, username, password, null, null));
        if (loginUser==null){
            System.out.println("登录失败");
            return null;
        }else{
            System.out.println("登录成功");
            return "pages/user/login_succeed";
        }
    }

    @PostMapping("/register")
    public String registerDriver(User user){
        System.out.println("保存的用户信息"+user);
        userService.registerUser(user);
        return "/pages/user/registerUser_succeed";
    }
}
