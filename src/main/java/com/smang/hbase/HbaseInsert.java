package com.smang.hbase;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by smang on 26/10/2016.
 */
public class HbaseInsert {



    private static List<JSONObject> getJSONObjs(String dataFile){
        Charset charset = Charset.forName("UTF-8");
        Path dataFilePath = Paths.get(dataFile);
        String currLine;
        List<JSONObject> listJSONObjs=new ArrayList<>();
        int currLineNbr=0;

        try {
            BufferedReader reader = Files.newBufferedReader(dataFilePath, charset);

            while( (currLine = reader.readLine()) != null ){
                currLineNbr=currLineNbr+1;
               JSONObject currJSONObj = new JSONObject(currLine);
                listJSONObjs.add(currJSONObj);
                          }


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return listJSONObjs;
    }

    private static void insertHbase(){

    }



    public static void main(String[] args) {

        System.out.println(getJSONObjs("data\\testJSON.txt") );

    }
}
