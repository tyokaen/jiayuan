package com.example.jiayuan.bijin.Presenter;

import android.os.Handler;
import android.os.Message;

import com.example.jiayuan.bijin.net.TotalInfoRequest;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by jiayuan on 2018/01/03.
 */

public class TotalInfoAction {
    public void getTotal(final Handler handler){
        TotalInfoRequest totalInfoRequest=new TotalInfoRequest();
        totalInfoRequest.enquene(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result=response.body().string();
                Message message=new Message();
                message.obj=result;
                message.arg1=3;
                handler.sendMessage(message);
            }
        });


    }
}
