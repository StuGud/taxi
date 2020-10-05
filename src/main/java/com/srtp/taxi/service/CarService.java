package com.srtp.taxi.service;


import com.srtp.taxi.entity.Car;

/**
 * Created by david.w on 2020/4/14.
 */

public interface CarService {
    /**
     * 车辆注册
     * @param car
     */
    public Car register(long driverId, Car car);

    /**
     * 车辆中是否已存在车牌号
     * @param plateNumber
     * @return 返回true表示用车牌号已存在
     */
    public boolean existsPlateNumber(String plateNumber);
}
