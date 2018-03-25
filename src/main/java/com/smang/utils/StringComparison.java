package com.smang.utils;

/**
 * Created by SMA on 31/01/2017.
 */
public class StringComparison {

    private static  void StringCompare(){
        String testString = null;
        boolean val= (testString==null || testString=="" )? true : false   ;
        System.out.println(val);
    }


    private static  void StringBuilderCompare(){
        StringBuilder testStringB = new StringBuilder();

        String testString = testStringB.toString();
        System.out.println(testString.isEmpty());
        boolean val= (testString==null || testString=="" )? true : false   ;
        System.out.println(val);
    }
    public static void main(String[] args) {
        String s1="abcd";
        String s2="abcd";
        //System.out.println(s1.compareTo(s2));

        System.out.println("Checking if condition");
        StringBuilderCompare();



    }
}
