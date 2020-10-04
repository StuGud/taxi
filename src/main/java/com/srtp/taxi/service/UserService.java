package com.srtp.taxi.service;


import com.srtp.taxi.entity.User;

/**
 * Created by david.w on 2020/4/7.
 */

public interface UserService {

    /**
     * 注册用户
     * @param user
     */
    public User register(User user);

    /**
     * 登录
     * @param user
     * @return
     */
    public User login(User user);
}
