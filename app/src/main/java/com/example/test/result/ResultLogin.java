package com.example.test.result;

import com.example.test.model.User;
import com.google.gson.annotations.SerializedName;

public class ResultLogin {
    @SerializedName("status_code")
    private String status;

    @SerializedName("status_message_ind")
    private String message;

    @SerializedName("value")
    private User data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }
}
