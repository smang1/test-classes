package com.smang.utils;

import org.joda.time.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class FindTheLastAvailableExchangeRate {
    static Map<Date,String> rates;

    private static String getLastAvailableRate(Date yyyy_MM_dd){
        String rate=null;
        for(int i=0; i<=7; i++){
            if(rates.containsKey(new DateTime(yyyy_MM_dd).minusDays(i).toDate())){
                rate=rates.get(new DateTime(yyyy_MM_dd).minusDays(i).toDate());
                break;
            }
        }
        return rate;

    }

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        rates = new HashMap<>();
        rates.put(sdf.parse("2018-05-29"),"29");
        rates.put(sdf.parse("2018-05-28"),"28");
        rates.put(sdf.parse("2018-05-27"),"27");
        rates.put(sdf.parse("2018-05-26"),"26");
        rates.put(sdf.parse("2018-05-25"),"25");
        rates.put(sdf.parse("2018-05-24"),"24");
        rates.put(sdf.parse("2018-05-23"),"23");
        rates.put(sdf.parse("2018-05-10"),"10");
        rates.put(sdf.parse("2018-05-03"),"03");
        rates.put(sdf.parse("2018-04-15"),"15");

        System.out.println(getLastAvailableRate(sdf.parse("2018-05-14")));






    }
}
