package com.example.jiayuan.bijin.net;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Request;

/**
 * Created by jiayuan on 2018/01/02.
 */

public class LoginRequest extends BaseRequest {
    public LoginRequest(FormBody formBody){
        super(formBody);

    }
    @Override
    public String getApi() {
        return "login";
    }
    @Override
    public int getHttpMethod() {
        return HttpMethod.POST;
    }

    @Override
    public Request buildeRequest() {
        return super.buildeRequest();
    }


    public void enquene(Callback callback) {
      super.enquene(callback);
    }



}
