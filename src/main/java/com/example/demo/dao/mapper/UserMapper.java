package com.example.demo.dao.mapper;

import com.example.demo.dao.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

public interface UserMapper {

    /**
     * 核实用户是否存在及账户密码是否对应正确
     * @return
     * @throws SQLException
     */
    User queryUser(@Param("username") String username, @Param("password") String password) throws SQLException;

    /**
     * 查询所有需要填写工时日报的员工
     * @return
     * @throws SQLException
     */
    List<User> queryAll() throws SQLException;

    /**
     * 修改密码
     * @param password
     * @param uid
     * @throws SQLException
     */
    void changePassword(@Param("password") String password, @Param("uid") Integer uid) throws SQLException;
}
