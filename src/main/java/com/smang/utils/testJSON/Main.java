package com.smang.utils.testJSON;

import org.apache.htrace.fasterxml.jackson.annotation.JsonAutoDetect;
import org.apache.htrace.fasterxml.jackson.annotation.PropertyAccessor;
import org.apache.htrace.fasterxml.jackson.core.JsonProcessingException;
import org.apache.htrace.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author smang
 */
public class Main {


    public static String toJSON(Object obj){
        String outJSON = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            outJSON= mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return outJSON;
    }


    public static void main(String[] args) {
        Company c1=new Company();
        Company c2=new Company();
        List<ProductDetails> pdList = new ArrayList<>();
        ProductDetails p1=new ProductDetails("coca", 1.5, "drinks");
        ProductDetails p2=new ProductDetails("fanta", 1.2, "drinks");
        ProductDetails p3=new ProductDetails("7up", 1.85, "drinks");

        pdList.add(p1);
        pdList.add(p2);
        pdList.add(p3);

        c1.setCompanyId("1");
        c1.setCompanyName("VM");
        c1.setProductList(pdList);
        System.out.println(toJSON(c1));

        pdList.remove(2);
        c2.setCompanyName("abc");
        c2.setCompanyId("2");
        c2.setProductList(pdList);

       /* Gson gson = new Gson();
        String json = gson.toJson(c1);
        System.out.println(json);
*/


        System.out.println(toJSON(c2));

        EOT_MSG eiFin =new EOT_MSG();

        eiFin.setId("1235");
        eiFin.setNb_pages(10);


        System.out.println(toJSON(eiFin));


    }
}
