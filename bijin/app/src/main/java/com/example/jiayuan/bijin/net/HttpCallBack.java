package com.example.jiayuan.bijin.net;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by jiayuan on 2018/01/02.
 */

public interface HttpCallBack {
    public void onResponse(Call call, Response response );

    public void onFailure(Call call, IOException e);
}
