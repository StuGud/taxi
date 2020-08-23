package com.srtp.taxi.mapper;


import com.srtp.taxi.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Created by david.w on 2020/4/7.
 */
@Mapper
public interface UserMapper {
    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return  返回null,无此用户
     */
    @Select("select * from t_user where username=#{username}")
    public User queryUserByUsername(String username);

    /**
     * 根据用户名密码查询用户信息
     * @param username
     * @param password
     * @return  返回null,无此用户
     */
    @Select("select * from t_user where username=#{username} and password=#{password}")
    public User queryUserByUsernameAndPassword(String username,String password);

    /**
     * 保存用户信息
     * @param user
     * @return 返回-1,操作失败
     */
    @Insert("insert into t_user (username,password,phone,email) values (#{username},#{password},#{phone},#{email}")
    public int saveUser(User user);


}
