package com.srtp.taxi.service;


import com.srtp.taxi.entity.Car;
import com.srtp.taxi.entity.Driver;

/**
 * Created by david.w on 2020/4/14.
 */

public interface DriverService {
    /**
     * 司机注册
     * @param driver
     */
    Driver register(Driver driver);

    /**
     * 司机登录
     * @param driver
     * @return
     */
    Driver login(Driver driver);
}
