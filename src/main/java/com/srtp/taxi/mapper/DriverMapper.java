package com.srtp.taxi.mapper;


import com.srtp.taxi.entity.Driver;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Created by david.w on 2020/4/14.
 */
@Mapper
public interface DriverMapper {
    /**
     * 根据用户名查询司机
     * @param username 用户名
     * @return  返回null,无此用户
     */
    @Select("select * from t_driver where username=#{username}")
    public Driver queryDriverByUsername(String username);

    /**
     * 根据用户名密码查询司机信息
     * @param username
     * @param password
     * @return  返回null,无此司机
     */
    @Select("select * from t_driver where username=#{username} and password=#{password}")
    public Driver queryDriverByUsernameAndPassword(String username,String password);

    /**
     * 保存司机信息
     * @param driver
     * @return 返回-1,操作失败
     */
    @Insert("insert into t_driver(username,password,phone,plateNumber) values(#{username},#{password},#{phone},#{plateNumber})")
    public int saveDriver(Driver driver);
}
