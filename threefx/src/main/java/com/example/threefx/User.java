package com.example.threefx;

import java.util.List;

public class User {
    private int user_id;
    private String username;
    private String email;
    private String type;

    public User() {
    }

    public User( String username, String email, String type) {

        this.username = username;
        this.email = email;
        this.type = type;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
