package com.example.test.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static String getDateTime() {
        //SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date) + " " + getCurrentTime();
    }

    public static String getCurrentTime(){
        Date dt = new Date();
        String sJam, sMenit, sDetik;
        sJam  = Integer.toString(dt.getHours());
        sMenit  = Integer.toString(dt.getMinutes());
        sDetik  = Integer.toString(dt.getSeconds());

        if (sJam.length() < 2) sJam = "0" + sJam;
        if (sMenit.length() < 2) sMenit = "0" + sMenit;
        if (sDetik.length() < 2) sDetik = "0" + sDetik;
        //return dt.getHours() + ":" + dt.getMinutes() + ":" + dt.getSeconds();
        return sJam + ":" + sMenit + ":" + sDetik;
    }
}
