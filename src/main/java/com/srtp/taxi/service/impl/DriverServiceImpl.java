package com.srtp.taxi.service.impl;


import com.srtp.taxi.entity.Driver;
import com.srtp.taxi.mapper.DriverMapper;
import com.srtp.taxi.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by david.w on 2020/4/14.
 */

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    DriverMapper driverMapper;

    @Override
    public void registerDriver(Driver driver) {
        driverMapper.saveDriver(driver);
    }

    @Override
    public Driver login(Driver driver) {
        return driverMapper.queryDriverByUsernameAndPassword(driver.getUsername(),driver.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {
        if(driverMapper.queryDriverByUsername(username)==null){
            return false;
        }
        return true;
    }
}
