package com.smang.webservices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by smang on 29/07/2016.
 */
public class GetSeqNum {

    public static void main(String[] args) {

        try {
            URL url = new URL("http://localhost:8080//uniqueSeq?count=1");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }


            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));


            String output;
           // System.out.println("Output from Server .... \n");
            int lineNbr=0;
           while ((output = br.readLine()) != null) {
               lineNbr+=1;

                String outArray[]= output.trim().replace("[","").replace("]","").split(",");

                System.out.println("Got from server: "+output);
                System.out.println("Length of the array received: "+ outArray.length);
                System.out.println("First element: "+outArray[0]);

/*               if(lineNbr==1){
                   break;
               }*/
            }

            conn.disconnect();
            System.out.println("disconnected");

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

}

