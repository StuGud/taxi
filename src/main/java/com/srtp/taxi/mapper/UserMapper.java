package com.srtp.taxi.mapper;


import com.srtp.taxi.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserMapper {
    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return  返回null,无此用户
     */
    @Select("select * from t_user where username=#{username}")
    User queryUserByUsername(String username);

    @Select("select * from t_user where phone=#{phone}")
    User queryUserByPhone(String phone);

    /**
     * 根据用户名密码查询用户信息
     * @param username
     * @param password
     * @return  返回null,无此用户
     */
    @Select("select * from t_user where username=#{username} and password=#{password}")
    User queryUserByUsernameAndPassword(String username, String password);

    /**
     * 保存用户信息
     * @param user
     * @return 返回-1,操作失败
     */
    @Insert("insert into t_user (username,password,phone) values (#{username},#{password},#{phone})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    boolean saveUser(User user);

    @Update("update t_user set password=#{password},phone=#{phone} where id=#{id}")
    boolean updateUser(User user);
}
