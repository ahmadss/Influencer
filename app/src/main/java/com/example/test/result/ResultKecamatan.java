package com.example.test.result;

import com.example.test.model.Kecamatan;
import com.example.test.model.Kota;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResultKecamatan {
    @SerializedName("status_code")
    private String status;

    @SerializedName("status_message_ind")
    private String message;

    @SerializedName("value")
    private ArrayList<Kecamatan> data;

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

    public ArrayList<Kecamatan> getData() {
        return data;
    }

    public void setData(ArrayList<Kecamatan> data) {
        this.data = data;
    }
}
