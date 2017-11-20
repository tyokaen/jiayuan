package com.example.jiayuan.bijin.Okhttp;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by jiayuan on 2017/10/29.
 */

public class OkhttpGet {
   static Response response=null;
public  static  String UseGet(OkHttpClient okHttpClient,String url,String headerKey,String headerVal,RequestBody body){
    String result="";
    Request request = new Request.Builder().url(url)
            .header(headerKey,headerVal)
            .method("GET", body)
            .build();
    Response response= null;
    try {
        response = okHttpClient.newCall(request).execute();
    } catch (IOException e) {
        e.printStackTrace();
    }
    if(response.isSuccessful()) {
        try {
            result= response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     return  result;

}
    public  static  byte[] UseGetImage(OkHttpClient okHttpClient,String url,String headerKey,String headerVal,RequestBody body){
        byte[] b=new byte[10000];
        Request request = new Request.Builder().url(url)
                .header(headerKey,headerVal)
                .method("GET", body)
                .build();
        Response response= null;
        try {
            response = okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(response.isSuccessful()) {
            try {
                b= response.body().bytes();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return  b;

    }
    public static String UsePost(OkHttpClient okHttpClient, String url, String headerKey, String headerVal, RequestBody requestBody)  {
        Request request=new Request.Builder().url(url)
                .addHeader(headerKey,headerVal)
                .post(requestBody)
                .build();
        try {
             response=okHttpClient.newCall(request).execute();
            if(response.isSuccessful())
                return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
            return ""+response.isSuccessful();


    }


}
