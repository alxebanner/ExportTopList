/**
  * Copyright 2023 bejson.com 
  */
package com.uid939948.DO.Emoticons;

import java.util.Map;

/**
 * Auto-generated: 2023-02-25 14:29:36
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Data {

    private int fans_brand;
    private Map<String, Emoticons> data;
    private String purchase_url;
    public void setFans_brand(int fans_brand) {
         this.fans_brand = fans_brand;
     }
     public int getFans_brand() {
         return fans_brand;
     }

    public void setData(Map<String, Emoticons> data) {
         this.data = data;
     }
     public Map<String, Emoticons> getData() {
         return data;
     }

    public void setPurchase_url(String purchase_url) {
         this.purchase_url = purchase_url;
     }
     public String getPurchase_url() {
         return purchase_url;
     }

}