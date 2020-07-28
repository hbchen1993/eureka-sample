package com.example.demo.admin.entity;

/**
 * <Description> <br>
 * 用户实体
 * 
 * @author xxx<br>
 * @version 1.0<br>
 * @CreateDate 2020-7-27 <br>
 * @see com.example.demo.admin.entity <br>
 */
public class User {

    private String username;

    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
