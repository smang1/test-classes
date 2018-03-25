package com.smang.utils;

/**
 * @author SMA
 * @Date 12/07/2017
 */
public class LexicographicComparison {

    public static int lexicoCompare(String str1, String str2){
        return str1.compareTo(str2);
    }

    public static void main(String[] args) {
        System.out.println(lexicoCompare("5", "35"));

    }
}

