package com.example.demo.entity;

import java.io.Serializable;

public class MyUser implements Serializable{
    private String userName;    // Field to store "username" in the users table in H2DB

    private String password;    // Field to store "password" in the users table in H2DB

    private String name;        // Field to store "name" in the users table in H2DB

    private String roleName;    // Field to store "roleName" in the users table in H2DB

    /**
     * getter, setter
     */
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
