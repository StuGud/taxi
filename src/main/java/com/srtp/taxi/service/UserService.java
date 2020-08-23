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
    public void registerUser(User user);

    /**
     * 登录
     * @param user
     * @return
     */
    public User login(User user);

    /**
     * 是否已存在用户名
     * @param username
     * @return 返回true表示用户名已存在
     */
    public boolean existsUsername(String username);
}
