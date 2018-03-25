package com.smang.utils;

import java.util.*;

/**
 * @author smang
 */
public class ArrayListDistinct {


    public static void main(String[] args) {
        String testString = "PP,AG,PM,pp,Ag ";

        System.out.println("testString Trimmed"+ testString.trim());
        //List<String> listAll = Arrays.asList("PP", "PM", "AG", "PP", "Ag");
        List<String> listAll = Arrays.asList(testString.trim().toUpperCase().split(","));
        System.out.println("listAll: "+listAll);
        Set<String> setWithUniqueValues = new HashSet<>(listAll);
        System.out.println("setWithUniqueValues: "+ setWithUniqueValues);
        List<String> listWithUniqueValues = new ArrayList<>(setWithUniqueValues);
        System.out.println("listWithUniqueValues: " + listWithUniqueValues);
       // System.out.println("String result:"+ listWithUniqueValues.toString());
       // List<String> listDistinct = listAll.stream().distinct().toArray();

        int idx=1;
        for(String type: listWithUniqueValues ){
            System.out.println(String.format("idx: %s type: %s ",idx,type));
            idx+=1;

        }
    }

}
