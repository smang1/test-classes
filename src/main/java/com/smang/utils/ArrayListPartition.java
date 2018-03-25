package com.smang.utils;

import java.util.ArrayList;
import java.util.List;


/**
 * @author SMA
 * @Date 08/09/2017
 */
public class ArrayListPartition {
    public static void main(String[] args) {
        int targetSize =5;
        List<Integer> li = new ArrayList<>();

        //fill up the test list
        for(int i=1; i <50; i++){
            li.add(i);
        }
        System.out.println("input:"+li);

        List<List<Integer>> lli= org.apache.commons.collections4.ListUtils.partition(li, targetSize);
        System.out.println(lli);
    }
}
