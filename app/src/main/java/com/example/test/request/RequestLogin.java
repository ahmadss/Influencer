package com.example.test.request;

public class RequestLogin {
    private String user_name;
    private String password;

    public RequestLogin(String user_name, String password){
        this.user_name = user_name;
        this.password = password;
    }
}
