package com.example.jiayuan.bijin.net;

import android.content.Context;

import com.example.jiayuan.bijin.cache.UserTokenCache;

/**
 * Created by jiayuan on 2018/01/03.
 */

public class UserInfoRequest extends BaseRequest {
    Context context=null;
    public UserInfoRequest(Context context){
        super();
        this.context=context;
    }

    @Override
    public String getApi() {
        return "user?token="+ UserTokenCache.getInstance().getUserToken(context);
    }
    @Override
    public int getHttpMethod() {
        return HttpMethod.GET;
    }
}
