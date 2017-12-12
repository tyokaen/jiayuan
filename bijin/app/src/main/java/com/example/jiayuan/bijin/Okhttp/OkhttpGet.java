package com.example.jiayuan.bijin.Okhttp;

import android.os.Handler;
import android.os.Message;

import com.example.jiayuan.bijin.Tools.StringToJson;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by jiayuan on 2017/10/29.
 */

public class OkhttpGet {
   static Response response=null;
    /*
    需要更新UI控件
     */
public  static String UseGetString(OkHttpClient okHttpClient, String url, String headerKey, String headerVal, RequestBody body, final Handler handler, final int arg){
        String result="";
    Request request = new Request.Builder().url(url)
            .header(headerKey,headerVal)
            .method("GET", body)
            .build();
    Call call=okHttpClient.newCall(request);
    call.enqueue(new Callback() {
        public void onFailure(Call call, IOException e) {
        }
        @Override
        public void onResponse(Call call, Response response) throws IOException {
            String result=response.body().string();
            Message message=new Message();
            message.obj=result;
            message.arg1=arg;
            handler.sendMessage(message);
        }
    });
    return result;
}
/*
获取图片token的JSONArray

 */
    public  static String UseGetArray(OkHttpClient okHttpClient, String url, String headerKey, String headerVal, RequestBody body) {
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
        return result;
    }
    public  static String UseGetList(OkHttpClient okHttpClient, String url, String headerKey, String headerVal, RequestBody body,ArrayList<String> arrayList,String arrayKey) {
       int ArraySize=0;
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
                JSONArray jsonArray=new JSONArray();
                jsonArray= StringToJson.getJSonArray(jsonArray,result,arrayKey);
                ArraySize=jsonArray.length();
                for(int i=0;i<jsonArray.length();i++){
                    try {
                        String token=jsonArray.getJSONObject(i).getString("bijin_token");
                        arrayList.add(token);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
             return result;
    }
    public  static String UseGetString1(OkHttpClient okHttpClient, String url, String headerKey, String headerVal, RequestBody body) {
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
        return result;
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
    /*
  获取图片并显示到imageview上
     */
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
