package com.example.jiayuan.bijin.net;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by jiayuan on 2017/12/08.
 */

public abstract  class BaseRequest {
    OkHttpClient okHttpClient = null;
    private  Call mcall = null;
    FormBody formBody=null;
    HashMap<String,String> hashmap=new HashMap<String,String>();
    public BaseRequest(){
        okHttpClient=new OkHttpClient();
    }
    public BaseRequest(FormBody formBody) {
        this.formBody=formBody;
        okHttpClient = new OkHttpClient();
    }

    public String getHost() {
        return HttpConfig.HOST;
    }
    public abstract String getApi();
    public String getUrl() {
        return String.format("%s%s",getHost(),getApi());
    }

    public String execute() throws IOException {
        Response response = this.okHttpClient.newCall(buildeRequest()).execute();
        int code = response.code();
        if (code == 200) {
            ResponseBody body = response.body();
            return body.string();

        } else {
            return null;
        }
    }

    public abstract int getHttpMethod();
    public Request buildeRequest(){
        RequestBody requestBody=null;
        Request.Builder builder=new Request.Builder();
       switch(getHttpMethod()){
           case HttpMethod.GET:
            builder.url(getUrl())
                    .header(HttpHeader.HEADER,HttpHeader.HEADERVAL)
                    .method("GET",requestBody);
             break;
           case HttpMethod.POST:
               builder.url(getUrl())
                       .header(HttpHeader.HEADER,HttpHeader.HEADERVAL)
                       .post(formBody);
               break;
           default:
               break;
       }
       return builder.build();

    }
    public void enquene(Callback callback) {
        if (mcall == null) {
            mcall = okHttpClient.newCall(buildeRequest());
            mcall.enqueue(callback);
        }
    }

    }

