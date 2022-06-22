package com.example.test.service;

import com.example.test.request.RequestLogin;
import com.example.test.request.RequestRegister;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface APIInterface {

    @POST("/api/auth/login")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Call<ResponseBody> doLogin(@Body RequestLogin data);

    @POST("/api/auth/signup")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Call<ResponseBody> doSignUp(@Body RequestRegister data);


    @GET("/api/influencerIg/")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Call<ResponseBody> influencerIgList();

    @GET
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Call<ResponseBody> influencerIgData(@Url String url);

    @GET("/api/influencerTiktok/")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Call<ResponseBody> influencerTiktokList();

    @GET
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Call<ResponseBody> influencerTiktokData(@Url String url);
}
