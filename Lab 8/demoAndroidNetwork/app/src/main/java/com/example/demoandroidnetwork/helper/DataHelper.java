package com.example.demoandroidnetwork.helper;

import android.icu.text.SimpleDateFormat;
import android.net.ParseException;
import java.util.Date;

public class DataHelper {
    private static String Pattern = "dd/MM/yyyy";
    public static Date toDate(String st) throws ParseException, java.text.ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.parse(st);
    }
    public static String toString(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }
    public static String toHour(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        return sdf.format(date);
    }
    public static String toHour(String st){
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        return sdf.format(st);
    }
}
