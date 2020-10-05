package com.srtp.taxi.restController;

import com.srtp.taxi.entity.User;
import com.srtp.taxi.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public User login(User user){
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
    public Object register(User user){
        if(userService.existsUsername(user.getUsername())){
            return "用户名已存在";
        }else if(userService.existsPhone(user.getPhone())){
            return "该号码已被注册过";
        }else{
            System.out.println("保存的用户信息"+user);
            return userService.register(user);
        }
    }

    @PostMapping("/modify")
    public Object modify(User user,String newPassword,String newPhone){
        User loginUser = userService.login(user);
        if (loginUser==null){
            System.out.println("修改失败");
            return "修改失败:用户名或密码错误";
        }else{
            if(newPassword!=null){
                loginUser.setPassword(newPassword);
            }
            if(newPhone!=null){
                if(userService.existsPhone(newPhone)){
                    return "该号码已被注册过";
                }
                loginUser.setPhone(newPhone);
            }
            User modifiedUser= userService.modify(loginUser);
            if(modifiedUser==null){
                return "修改失败";
            }else{
                return modifiedUser;
            }
            //return loginUser;
        }
    }
}