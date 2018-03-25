package com.smang.utils.testJSON;

import org.apache.htrace.fasterxml.jackson.core.JsonProcessingException;
import org.apache.htrace.fasterxml.jackson.databind.ObjectMapper;
import org.codehaus.jettison.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author smang
 */
public class MapToJson {

    public static void main(String[] args) {

        Map< String, Object > jsonValues = new HashMap< String, Object >();
        jsonValues.put("X-Message-id", "409c607f-3ed7-44bd-9a58-ad8cfa8741b2");
        jsonValues.put("X-Message-type", "evtInit_p");
        jsonValues.put("X-Correlation-id", "2DO1703000015500");
        JSONObject json = new JSONObject(jsonValues);
        try {
            System.out.println(new ObjectMapper().writeValueAsString(jsonValues));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
