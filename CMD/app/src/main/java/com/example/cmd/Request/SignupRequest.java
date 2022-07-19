package com.example.cmd.Request;

public class SignupRequest {
    String userId;
    String password;

    public SignupRequest(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }
}
