package com.smang.utils;

import org.apache.commons.lang.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by smang on 29/11/2016.
 */
public class DateCalc {

    /**
     * This method subtracts the number of days (Int) from a date String ("yyyy-MM-dd hh:mm:ss")
     * and returns dd, mm or yyyy based on the format value requested
     * @param dateStr: A date String ("yyyy-MM-dd hh:mm:ss")
     * @param nbDays :Number of days to be subtacted (integer)
     * @param format : Format of the result required dd, mm or yyyy
     * @return
     */
    public static int dateSubtractGetPart(String dateStr, int nbDays, String format){
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        int val=0;
        try {
            Date date=DateUtils.addDays(sdf.parse(dateStr),-nbDays);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH)+1; //In calendar utility month starts by 0 (0=>jan)
            int day = cal.get(Calendar.DAY_OF_MONTH);

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
            }


        } catch (ParseException e) {
            e.printStackTrace();
        }
        return val;

    }


    public static void main(String[] args) {

/*        Date date=DateUtils.addDays(new Date(), -29); // your date
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        //Date dateBefore30Days = DateUtils.addDays(new Date(), -29);

        System.out.println(DateUtils.addDays(new Date(), -29));
        System.out.println(day);
        System.out.println(month+1);
        System.out.println(year);*/

        System.out.println(dateSubtractGetPart("2016-11-28 00:00:00",28,"dd"));
        System.out.println(dateSubtractGetPart("2016-11-28 00:00:00",28,"mm"));
        System.out.println(dateSubtractGetPart("2016-11-28 00:00:00",28,"yyyy"));
    }


}
