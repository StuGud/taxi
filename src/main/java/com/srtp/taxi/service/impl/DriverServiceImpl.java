package com.srtp.taxi.service.impl;


import com.srtp.taxi.entity.Driver;
import com.srtp.taxi.entity.OnlineDriver;
import com.srtp.taxi.mapper.DriverMapper;
import com.srtp.taxi.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by david.w on 2020/4/14.
 */

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    DriverMapper driverMapper;

    @Override
    public Driver register(Driver driver) {
       if(driverMapper.saveDriver(driver)){
           return driver;
       }
       return null;
    }

    @Override
    public Driver login(Driver driver) {
        return driverMapper.queryDriverByUsernameAndPassword(driver.getUsername(),driver.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {
        if (driverMapper.queryDriverByUsername(username)==null){
            return false;
        }
        return true;
    }

    @Override
    public boolean existsPhone(String phone) {
        if (driverMapper.queryDriverByPhone(phone)==null){
            return false;
        }
        return true;
    }

    @Override
    public Driver modify(Driver driver) {
        if(driverMapper.updateDriver(driver)){
            return driver;
        }
        return null;
    }

    @Override
    public List<Driver> listAll() {
        return driverMapper.queryAllDriver();
    }

    @Override
    public Driver findDriverById(long id) {
        return driverMapper.queryDriverById(id);
    }

    @Override
    public List<OnlineDriver> listAllOnline() {
        return driverMapper.queryAllOnlineDriver();
    }

    @Override
    public OnlineDriver findOnlineDriverById(long id) {
        return driverMapper.queryOnlineDriverById(id);
    }
}
