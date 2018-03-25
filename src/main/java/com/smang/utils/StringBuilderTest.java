package com.smang.utils;

/**
 * Created by frup66315 on 01/03/2017.
 */
public class StringBuilderTest {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append("abcd");
        System.out.println(sb);
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);
    }

}
