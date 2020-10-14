package com.srtp.taxi.service;


import com.srtp.taxi.entity.Car;
import com.srtp.taxi.entity.Driver;
import com.srtp.taxi.entity.OnlineDriver;

import java.util.List;

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

    boolean existsUsername(String username);
    boolean existsPhone(String phone);

    Driver modify(Driver driver);

    List<Driver> listAll();
    Driver findDriverById(long id);
    List<OnlineDriver> listAllOnline();
    OnlineDriver findOnlineDriverById(long id);

}
