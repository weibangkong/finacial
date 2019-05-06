package com.kwb.util.common;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.kwb.entity.Product;
import oracle.jrockit.jfr.openmbean.ProducerDescriptorType;

import java.net.MalformedURLException;
import java.net.URL;

public class JsonRpcClient {
    static JsonRpcHttpClient client;

   public void getRpcMessage() {
       try {
           client = new JsonRpcHttpClient(new URL("http://192.168.1.2:8081/manager/rpc/products"));
       } catch (MalformedURLException e) {
           e.printStackTrace();
       }
       Product product = null;
       try {
           product = (Product)client.invoke("findOne", 001, Product.class);
       } catch (Throwable throwable) {
           throwable.printStackTrace();
       }
       System.out.println(product);
   }
}
