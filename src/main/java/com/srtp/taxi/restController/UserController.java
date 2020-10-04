package com.srtp.taxi.restController;

import com.srtp.taxi.entity.User;
import com.srtp.taxi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    final
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public User loginUser(User user){
        User loginUser = userService.login(user);
        if (loginUser==null){
            System.out.println("登录失败");
            return null;
        }else{
            System.out.println("登录成功");
            //loginUser.setPassword("******");
            return loginUser;
        }
    }

    @PostMapping("/register")
    public User registerDriver(User user){
        System.out.println("保存的用户信息"+user);
        return userService.register(user);
    }
}