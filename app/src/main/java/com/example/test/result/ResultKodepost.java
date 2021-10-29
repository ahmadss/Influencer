package com.example.test.result;

import com.example.test.model.Kecamatan;
import com.example.test.model.Postcode;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResultKodepost {
    @SerializedName("status_code")
    private String status;

    @SerializedName("status_message_ind")
    private String message;

    @SerializedName("value")
    private ArrayList<Postcode> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        if(message==null){
            message = "";
        }
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Postcode> getData() {
        return data;
    }

    public void setData(ArrayList<Postcode> data) {
        this.data = data;
    }
}
