package com.example.test.result;

import com.example.test.model.Kecamatan;
import com.example.test.model.Kelurahan;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResultKelurahan {
    @SerializedName("status_code")
    private String status;

    @SerializedName("status_message_ind")
    private String message;

    @SerializedName("value")
    private ArrayList<Kelurahan> data;

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

    public ArrayList<Kelurahan> getData() {
        return data;
    }

    public void setData(ArrayList<Kelurahan> data) {
        this.data = data;
    }
}
