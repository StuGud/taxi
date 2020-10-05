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

    final
    UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User register(User user) {
        if(userMapper.saveUser(user)){
            return user;
        }
        return null;
    }

    @Override
    public User login(User user) {
        return userMapper.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {
        if (userMapper.queryUserByUsername(username)==null){
            return false;
        }
        return true;
    }

    @Override
    public boolean existsPhone(String phone) {
        if (userMapper.queryUserByPhone(phone)==null){
            return false;
        }
        return true;
    }

    @Override
    public User modify(User user) {
        if(userMapper.updateUser(user)){
            return user;
        }
        return null;
    }
}
