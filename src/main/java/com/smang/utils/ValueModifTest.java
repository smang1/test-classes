package com.smang.utils;

/**
 * @author SMA
 * @Date 14/09/2017
 */
public class ValueModifTest {

    public static void modifyString(String a){
        a = "POPO";
        System.out.println("Inside method: "+a);

    }
    public static void main(String[] args) {
        String a ="TOTO";
        System.out.println("Before calling the method: "+a);

        modifyString(a);
        System.out.println("After the method: "+a);

    }
}
