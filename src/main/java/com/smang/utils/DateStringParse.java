package com.smang.utils;

import org.apache.commons.lang.time.DateUtils;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by smang on 22/02/2017.
 */
public class DateStringParse {

    public static String dateSubtractGetPart(String dateStr, int nbDays, String format){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        DecimalFormat df = new DecimalFormat("00");
        String val = null;
        try {
            Date date= DateUtils.addDays(sdf.parse(dateStr),-nbDays);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            String year = String.valueOf(cal.get(Calendar.YEAR));
            String month = df.format(cal.get(Calendar.MONTH)+1); //In calendar utility month starts by 0 (0=>jan)
            String day = df.format(cal.get(Calendar.DAY_OF_MONTH));

            switch(format){
                case "dd":
                    val=day;
                    break;
                case "mm":
                    val=month;
                    break;
                case "yyyy":
                    val=year;
                    break;
                default:
                    new ParseException("Invalid value for format"+format+"Valid values: dd, mm, yyyy",0);
                    System.exit(1);
                    break;
            }


        } catch (ParseException e) {
            e.printStackTrace();
        }
        return val;

    }



    public static void main(String[] args) {


        String dateFormat="yyyy-MM-dd HH:mm:ss";
        DecimalFormat df = new DecimalFormat("00");
        DateTimeFormatter formatter = DateTimeFormat.forPattern(dateFormat);
               // .withLocale(Locale.UK);

        LocalDate date = formatter.parseLocalDate("2016-09-01 13:29:25");

        System.out.println("Day: "+ df.format(date.getDayOfMonth()));
        System.out.println("Month: "+df.format(date.getMonthOfYear()));
        System.out.println("Year: "+date.getYear());


        System.out.println(dateSubtractGetPart("2016-09-01 13:29:25",1,"mm"));
        System.out.println(dateSubtractGetPart("2016-09-01 13:29:25",1,"dd"));
        System.out.println(dateSubtractGetPart("2016-09-01 13:29:25",1,"yyyy"));
    }
}
