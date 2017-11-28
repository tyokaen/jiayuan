package com.example.jiayuan.bijin.cache;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by jiayuan on 2017/11/28.
 */

public class UserTokenCache {
    public static UserTokenCache userTokenCache=null;
    private UserTokenCache(){};
    public static UserTokenCache getInstance(){
        if(userTokenCache==null){
            userTokenCache=new UserTokenCache();
        }
        else return userTokenCache;
        return userTokenCache;
    }
    public void storeUserToken(Context context,String user){
        SharedPreferences sharedPreferences=context.getSharedPreferences("User",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("UserToken",user);
        editor.commit();
    }
    public String getUserToken(Context context){
        String userToken=null;
        SharedPreferences sharedPreferences=context.getSharedPreferences("User",Context.MODE_PRIVATE);
        userToken=sharedPreferences.getString("UserToken","");
        return userToken;
    }
}
