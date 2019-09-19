package com.example.demo.dao.pojo;

public class User {
    private Integer uid; //编号
    private String username; //登录名
    private String realname; //姓名
    private String password; //密码
    private char jurisdiction; //权限

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public char getJurisdiction() {
        return jurisdiction;
    }

    public void setJurisdiction(char jurisdiction) {
        this.jurisdiction = jurisdiction;
    }

}
