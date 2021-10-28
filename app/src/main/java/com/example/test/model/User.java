package com.example.test.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {

    @SerializedName("user_name")
    private String username;

    @SerializedName("customer_id")
    private String customer_id;

    @SerializedName("customer_type_id")
    private String customer_type_id;

    @SerializedName("customer_name")
    private String customer_name;

    @SerializedName("phone")
    private String phone;

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    @SerializedName("blocked")
    private String blocked;

    @SerializedName("is_active")
    private String is_active;

    public String getUsername() {
        if(username==null){
            username = "";
        }
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCustomer_id() {
        if(customer_id==null){
            customer_id = "";
        }
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_type_id() {
        if (customer_type_id==null){
            customer_type_id = "";
        }
        return customer_type_id;
    }

    public void setCustomer_type_id(String customer_type_id) {
        this.customer_type_id = customer_type_id;
    }

    public String getCustomer_name() {
        if(customer_name==null){
            customer_name = "";
        }
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getPhone() {
        if(phone==null){
            phone = "";
        }
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        if(email==null){
            email = "";
        }
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        if(password==null){
            password = "";
        }
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBlocked() {
        if(blocked==null){
            blocked = "true";
        }
        return blocked;
    }

    public void setBlocked(String blocked) {
        this.blocked = blocked;
    }

    public String getIs_active() {
        if(is_active==null){
            is_active = "false";
        }
        return is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }
}
