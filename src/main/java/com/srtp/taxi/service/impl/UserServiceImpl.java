package com.srtp.taxi.service.impl;


import com.srtp.taxi.entity.User;
import com.srtp.taxi.mapper.UserMapper;
import com.srtp.taxi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by david.w on 2020/4/7.
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public void registerUser(User user) {
        userMapper.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userMapper.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {
        if(userMapper.queryUserByUsername(username)==null){
            return false;
        }
        return true;
    }
}
