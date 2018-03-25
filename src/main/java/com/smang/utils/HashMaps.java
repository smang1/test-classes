package com.smang.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by smang on 30/11/2016.
 */
public class HashMaps {


    private static HashMap<String, List<String>> aggregateHMapByKey(HashMap <String, String> hmap) {
        HashMap<String, List<String>> hmapAgg = new HashMap<>();

        for(Map.Entry<String, String> currElmt: hmap.entrySet()) {
            String key=currElmt.getKey();
            String value=currElmt.getValue();
            if (!hmapAgg.containsKey(key)) {
                hmapAgg.put(key, new ArrayList<String>());
            }
            hmapAgg.get(key).add(value);
        }
      //  System.out.println(hmapAgg);
        return hmapAgg;
    }


    /**
     * This method adds the value to the List associated to that key
     * If list does not exists it creates one, if list already exists
     * it adds the value to the list
     * @param map
     * @param key
     * @param value
     */
    private static void addToMap(HashMap <String, List<String>> map, String key, String value){
        if(!map.containsKey(key)){
            map.put(key, new ArrayList<String>());
        }
        map.get(key).add(value);
    }



    public static void main(String[] args) {
        HashMap<String/*type*/, List<String>/*ids*/> hmap = new HashMap<>();//new HashMap<String, ArrayList<String>>();
/*        hmap.put("PP","PP1");
        hmap.put("OF","OF1");
        hmap.put("PP","PP2");
        hmap.put("PM","PM1");
        hmap.put("LM","LM1");
        hmap.put("OF","OF2");
        hmap.put("OF","OF3");*/
        addToMap(hmap,"OF","OF1");
        addToMap(hmap,"PP","PP1");
        addToMap(hmap,"PP","PP2");
        addToMap(hmap,"PM","PM1");
        addToMap(hmap,"LM","LM1");
        addToMap(hmap,"OF","OF2");
        addToMap(hmap,"OF","OF3");
        addToMap(hmap,"OF","OF4");


        System.out.println(hmap);


        String test="PPFIA";
        System.out.println("my val: "+ test.split("_")[0]);


        //System.out.println(aggregateHMapByKey(hmap));



    }
}
