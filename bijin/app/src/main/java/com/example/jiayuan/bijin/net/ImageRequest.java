package com.example.jiayuan.bijin.net;

/**
 * Created by jiayuan on 2018/01/03.
 */

public class ImageRequest extends BaseRequest {
    String imageToken="";

    public void setImageToken(String imageToken){
        this.imageToken=imageToken;
    }
    public String getImageToken(){
        return imageToken;
    }
    public ImageRequest(){
        super();

    }
    public String getApi() {
        return "bijin/image?token="+ getImageToken() + "&size=small" ;
    }

    @Override
    public int getHttpMethod() {
        return HttpMethod.GET;
    }
}
