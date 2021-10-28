package com.example.test.service;

import com.example.test.request.RequestLogin;
import com.example.test.result.ResultLogin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIInterface {

    @POST("login-customer")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Call<ResultLogin> doLogin(@Body RequestLogin data);
}
