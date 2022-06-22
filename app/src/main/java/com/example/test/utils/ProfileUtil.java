package com.example.test.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class ProfileUtil {

    public void save(Context context, String user, String category, String email){
        SharedPreferences settings = context.getSharedPreferences("profileUser", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("user", user);
        editor.putString("category", category);
        editor.putString("email", email);
        editor.commit();
    }


    public String getUser(Context context){
        SharedPreferences settings = context.getSharedPreferences("profileUser", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        String user = settings.getString("user", null);
        return user;
    }

    public String getEmail(Context context){
        SharedPreferences settings = context.getSharedPreferences("profileUser", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        String user = settings.getString("email", null);
        return user;
    }

    public String getcategory(Context context){
        SharedPreferences settings = context.getSharedPreferences("profileUser", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        String category = settings.getString("category", null);
        return category;
    }

    public void resetProfile(Context context){
        SharedPreferences settings = context.getSharedPreferences("profileUser", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.clear();
        editor.commit();
    }

    public boolean isLogin(Context context){
        boolean isLogin = false;
        SharedPreferences settings = context.getSharedPreferences("profileUser", context.MODE_PRIVATE);
        String user = settings.getString("user", null);

        if (user != null){
            if (user.equals("")){
                return false;
            }else{
                return true;
            }
        }
        return isLogin;
    }
}
