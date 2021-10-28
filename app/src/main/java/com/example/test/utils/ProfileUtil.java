package com.example.test.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.test.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class ProfileUtil {
    public void save(Context context, User user){
        SharedPreferences settings = context.getSharedPreferences("profileShipper", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        Gson gson = new Gson();

        String jsonUser = gson.toJson(user);
        editor.putString("jsonUser", jsonUser);
        editor.commit();
    }


    public void setSessionId(Context context, String sessionId){
        SharedPreferences settings = context.getSharedPreferences("profileShipper", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        editor.putString("requestSessionId", sessionId);
        editor.commit();
    }

    public String getSessionId(Context context){
        SharedPreferences settings = context.getSharedPreferences("profileShipper", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        String sessionId = settings.getString("requestSessionId", null);
        return sessionId;
    }

    public User getUser(Context context){
        SharedPreferences settings = context.getSharedPreferences("profileShipper", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        Gson gson = new Gson();
        String jsonUser = settings.getString("jsonUser", null);
        Type type = new TypeToken<User>() {}.getType();
        return gson.fromJson(jsonUser, type);
    }

    public void resetProfile(Context context){
        SharedPreferences settings = context.getSharedPreferences("profileShipper", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.clear();
        editor.commit();
    }

    public boolean isLogin(Context context){
        boolean isLogin = false;
        SharedPreferences settings = context.getSharedPreferences("profileShipper", context.MODE_PRIVATE);
        Gson gson = new Gson();
        String jsonUser = settings.getString("jsonUser", null);
        Type type = new TypeToken<User>() {}.getType();
        User user = gson.fromJson(jsonUser, type);

        if (user != null){
            if (user.getUsername().equals("")){
                return false;
            }else{
                return true;
            }
        }
        return isLogin;
    }
}
