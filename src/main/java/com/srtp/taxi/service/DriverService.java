package com.srtp.taxi.service;


import com.srtp.taxi.entity.Driver;

/**
 * Created by david.w on 2020/4/14.
 */

public interface DriverService {
    /**
     * 司机注册
     * @param driver
     */
    public void registerDriver(Driver driver);

    /**
     * 司机登录
     * @param driver
     * @return
     */
    public Driver login(Driver driver);

    /**
     * 司机中是否已存在用户名
     * @param username
     * @return 返回true表示用户名已存在
     */
    public boolean existsUsername(String username);
}
