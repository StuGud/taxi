package com.srtp.taxi.controller;

import com.srtp.taxi.entity.Driver;
import com.srtp.taxi.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/driver")
public class DriverController {

    @Autowired
    DriverService driverService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String loginDriver(String username,String password){
        Driver loginUser = driverService.login(new Driver(null, username, password, null, null));
        if (loginUser==null){
            System.out.println("登录失败");
            return null;
        }else{
            return "/pages/driver/loginDriver_succeed";
        }
    }

    @PostMapping("/register")
    public String registerDriver(Driver driver){
        System.out.println("保存的司机信息"+driver);
        driverService.registerDriver(driver);
        return "/pages/driver/registerDriver_succeed";
    }
}
