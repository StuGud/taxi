package com.srtp.taxi.service;


import com.srtp.taxi.entity.User;

import java.util.List;

/**
 * Created by david.w on 2020/4/7.
 */

public interface UserService {

    /**
     * 注册用户
     * @param user
     */
    User register(User user);

    /**
     * 登录
     * @param user
     * @return
     */
    User login(User user);

    boolean existsUsername(String username);
    boolean existsPhone(String phone);

    User modify(User user);

    List<User> listAll();
}
