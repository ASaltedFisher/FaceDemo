package com.example.demo.service.impl;

import com.example.demo.dao.mapper.UserMapper;
import com.example.demo.dao.pojo.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryUser(String username, String password) throws SQLException {
        return userMapper.queryUser(username, password);
    }

    @Override
    public List<User> queryAll() throws SQLException {
        return userMapper.queryAll();
    }

    @Override
    public void changePassword(String password, Integer uid) throws SQLException {
        userMapper.changePassword(password, uid);
    }

}
