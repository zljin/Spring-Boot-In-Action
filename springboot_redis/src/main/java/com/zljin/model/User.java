package com.zljin.model;

public class User {

    private String phone;

    //0:未登录 1：登录
    private String isLogin;

    public User() {
    }

    public User(String phone, String isLogin) {
        this.phone = phone;
        this.isLogin = isLogin;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIsLogin() {
        return isLogin;
    }

    public void setIsLogin(String isLogin) {
        this.isLogin = isLogin;
    }
}
