package com.example.demo.service;

import com.example.demo.dao.pojo.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    /**
     * 用户信息查询
     * @return
     * @throws SQLException
     */
    User queryUser(String username, String password) throws SQLException;

    List<User> queryAll() throws SQLException;

    void changePassword(String password, Integer uid) throws SQLException;
}
