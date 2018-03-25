package com.smang.utils.testJSON;

import java.util.ArrayList;
import java.util.List;

/**
 * @author smang
 */
public class Company {
    String companyId;
    String companyName;
    List<ProductDetails> productList=new ArrayList<>();

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<ProductDetails> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductDetails> productList) {
        this.productList = productList;
    }

/*    public static String toJson(Object obj){
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        return json;
    }*/
}
