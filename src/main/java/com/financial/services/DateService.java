package com.financial.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateService {

    public static Date dateToDate(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return  format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String dateToString(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }
}
