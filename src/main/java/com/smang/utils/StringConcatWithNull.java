package com.smang.utils;

/**
 * @author SMA
 * @Date 20/10/2017
 */
public class StringConcatWithNull {


    public static void main(String[] args) {
        String a = "A";
        String b = "B";
        String c = null;
        String d = "D";
        String e = "";
        String x = a +b +c +d;


        System.out.println(x);
    }
}
