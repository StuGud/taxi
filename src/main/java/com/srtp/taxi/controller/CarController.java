package com.srtp.taxi.controller;

import com.srtp.taxi.entity.Car;
import com.srtp.taxi.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/car")
public class CarController {

    @Autowired
    CarService carService;

    @PostMapping("/register")
    public String registerCar(Car car){
        //检查验证码是否正确
        //未实现
        if (carService.existsPlateNumber(car.getPlateNumber())){
            System.out.println("车牌号"+car.getPlateNumber()+"不可用");
            return null;
        }else {
            carService.registerCar(car);
            return "/pages/car/registerCar_succeed";
        }
    }
}
