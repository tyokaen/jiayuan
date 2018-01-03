package com.example.jiayuan.bijin.Presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.example.jiayuan.bijin.net.UserInfoRequest;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by jiayuan on 2018/01/03.
 */

public class UserInfoAction {

    public void  getUserInfo(Context context, final Handler handler){
        String result="";
        UserInfoRequest userInfoRequest=new UserInfoRequest(context);
        userInfoRequest.enquene(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result=response.body().string();
                Message message=new Message();
                message.obj=result;
                message.arg1=2;
                handler.sendMessage(message);
            }
        });


    }

}
