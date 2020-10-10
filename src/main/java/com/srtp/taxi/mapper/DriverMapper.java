package com.srtp.taxi.mapper;


import com.srtp.taxi.entity.Driver;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by david.w on 2020/4/14.
 */
@Component
@Mapper
public interface DriverMapper {
    /**
     * 根据用户名查询司机
     * @param username 用户名
     * @return  返回null,无此用户
     */
    @Select("select * from t_driver where username=#{username}")
    Driver queryDriverByUsername(String username);

    @Select("select * from t_driver where phone=#{phone}")
    Driver queryDriverByPhone(String phone);

    /**
     * 根据用户名密码查询司机信息
     * @param username
     * @param password
     * @return  返回null,无此司机
     */
    @Select("select * from t_driver where username=#{username} and password=#{password}")
    Driver queryDriverByUsernameAndPassword(String username, String password);

    @Select("select * from t_driver")
    List<Driver> queryAllDriver();

    /**
     * 保存司机信息
     * @param driver
     * @return 返回-1,操作失败
     */
    @Insert("insert into t_driver(username,password,phone) values(#{username},#{password},#{phone})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    boolean saveDriver(Driver driver);

    @Update("update t_driver set carId=#{carId} where id=#{driverId}")
    boolean bindCar(long driverId,long carId);

    @Update("update t_driver set password=#{password},phone=#{phone} where id=#{id}")
    boolean updateDriver(Driver driver);
}
