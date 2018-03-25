package com.smang.utils;

/**
 * Created by smang on 02/12/2016.
 */
public class SubstringTest {

    public static void main(String[] args) {

        String a = "emp1";

        if(a.trim().equalsIgnoreCase("emp")){
            System.out.println("TRUE");
            System.exit(1);
        }


        String k = "1XY_ABCD-MYID132-01_QWERF1";
        System.out.println(k.substring(1,3));



    }
}
