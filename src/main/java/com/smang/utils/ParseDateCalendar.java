package com.smang.utils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author SMA
 * @Date 13/07/2017
 */
public class ParseDateCalendar {

    public static String getDDFromDateObject(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        DecimalFormat df = new DecimalFormat("00");
        return df.format(cal.get(Calendar.DAY_OF_MONTH));
    }

    public static String getMMFromDateObject(Date date){
        Calendar cal = Calendar.getInstance();
        DecimalFormat df = new DecimalFormat("00");
        cal.setTime(date);
        return df.format(cal.get(Calendar.MONTH) +1); //In calendar utility month starts by 0 (0=>jan)
    }

    public static String getYYYYFromDateObject(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        DecimalFormat df = new DecimalFormat("00");
        return df.format(cal.get(Calendar.YEAR));
    }

    public static void main(String[] args) throws ParseException {

        String ts = "2017-07-12 14:55:43";
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(ts);

        System.out.println(getDDFromDateObject(date));
        System.out.println(getMMFromDateObject(date));
        System.out.println(getYYYYFromDateObject(date));
    }
}
