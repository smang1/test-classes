package com.smang.utils;

import java.text.SimpleDateFormat;

/**
 * Created by smang on 21/02/2017.
 */
public class StringformatMatch {

    public static void main(String[] args) {
        System.out.println(String.format("%s %s %s %s %s %s", "hello", "2", "3", "4", "5", "6"));
        System.out.println(String.format("%1$s %2$s %3$s %4$s %5$s %1$s", "hello", "2", "3", "4", "5", "6"));
        System.out.println(String.format("(%1$s.part_yyyy > %2$s or (%1$s.part_yyyy=%2$s and (%1$s.part_mm > %3$s or (%1$s.part_mm=%3$s and %1$s.part_dd >= %4$s))))", "ocr", "2016",  "02", "2"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String prevTs="2016-12-05 14:32:05";
        String id="1IF1612000000001";

        String label="PP";
        String type=id.substring(1,3);
        System.out.println(id + " type "+ type );

        if(type=="IF"){
            System.out.println("string matched");
        } else
        {
            System.out.println("string  not matched");
        }


        System.out.println("label:"+label.substring(0,2));

        if(id.matches("^1IF.*$")){
            System.out.println("string matched: "+id);
        } else
        {
            System.out.println("string  not matched: "+id);
        }

        /*System.out.println(prevTs.substring(0,4));
        System.out.println(prevTs.substring(5,7));
        System.out.println(prevTs.substring(8,10));*/

    }
}
