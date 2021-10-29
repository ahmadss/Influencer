package com.example.test.result;

import com.example.test.model.Kota;
import com.example.test.model.Provinsi;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResultKota {
    @SerializedName("status_code")
    private String status;

    @SerializedName("status_message_ind")
    private String message;

    @SerializedName("value")
    private ArrayList<Kota> data;

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

    public ArrayList<Kota> getData() {
        return data;
    }

    public void setData(ArrayList<Kota> data) {
        this.data = data;
    }
}
