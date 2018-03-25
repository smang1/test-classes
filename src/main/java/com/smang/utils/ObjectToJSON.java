package com.smang.utils;

import org.apache.htrace.fasterxml.jackson.core.JsonProcessingException;
import org.apache.htrace.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by smang on 21/11/2016.
 */
public class ObjectToJSON {

    /**
     * This method converts a Java method to a JSON String and returns it
     * @param obj: a java object
     * @return JSON String
     */
    public static String toJSON(Object obj){
        String outJSON = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            outJSON= mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return outJSON;
    }

}
