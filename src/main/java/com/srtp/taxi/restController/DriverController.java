package com.srtp.taxi.restController;

import com.srtp.taxi.entity.Car;
import com.srtp.taxi.entity.Driver;
import com.srtp.taxi.service.CarService;
import com.srtp.taxi.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/driver")
public class DriverController {
    final
    DriverService driverService;

    @Autowired
    CarService carService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @PostMapping("/login")
    public Driver login(Driver driver){
        Driver loginDriver = driverService.login(driver);
        if (loginDriver==null){
            System.out.println("登录失败");
            return null;
        }else{
            System.out.println("登录成功");
            return loginDriver;
        }
    }

    @PostMapping("/register")
    public Driver register(Driver driver){
        System.out.println("保存的司机信息"+driver);
        return driverService.register(driver);
    }

    @PostMapping("/{driverId}/registerCar")
    public Car registerCar(@PathVariable long driverId, Car car){
        Car registerCar = carService.registerCar(driverId, car);
        return registerCar;
    }
}
