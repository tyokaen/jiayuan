package com.example.jiayuan.bijin.Tools;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by jiayuan on 2017/11/20.
 */

public class GetUserToken {
    public static GetUserToken userToken=null;
    private static  SharedPreferences sharedPreferences=null;
    private GetUserToken(){};
    public static  GetUserToken getInstance(){
        if(userToken==null)
            userToken=new GetUserToken();
        return  userToken;
    }
    public  SharedPreferences getSp(Context context){
        sharedPreferences=context.getSharedPreferences("UserToken",Context.MODE_PRIVATE);
        return sharedPreferences;

    }


    private String GetToken(Context context){
       String usertoken;
        usertoken=sharedPreferences.getString("UserToken","jiayuan");
        return usertoken;

    }
}
