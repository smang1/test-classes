package com.smang.utils;

/**
 * @author SMA
 * @Date 29/08/2017
 */
public class isInteger {

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isInteger("1"));
    }

}
