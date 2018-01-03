package com.example.jiayuan.bijin.net;

/**
 * Created by jiayuan on 2018/01/03.
 */

public class TotalInfoRequest extends BaseRequest {
    public TotalInfoRequest(){
        super();
    }
    public String getApi() {
        return "general/subject";
    }

    @Override
    public int getHttpMethod() {
        return HttpMethod.GET;
    }
}
