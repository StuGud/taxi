package com.srtp.taxi.mapper;


import com.srtp.taxi.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
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
}
