package com.smang.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Smang on 17/11/2016.
 */
public class ConvertJavaListToScalaList {

    private static scala.collection.immutable.List<String> javaListToScalaList(List<String> javaList){

        return scala.collection.JavaConverters.asScalaIterableConverter(javaList).asScala().toList();
    }

    public static void main(String[] args) {

        List<String> jList=new ArrayList<>();
        jList.add("1");
        jList.add("2");
        System.out.println("jList: "+jList);
        scala.collection.immutable.List<String> sList=javaListToScalaList(jList);
        System.out.println(sList);

    }
}
