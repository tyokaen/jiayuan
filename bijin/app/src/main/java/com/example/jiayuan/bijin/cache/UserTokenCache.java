package com.example.jiayuan.bijin.cache;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

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
    public void storeUserBestToken(Context context,String BestBijn1,String BestBijin2,String BestBjin3){
        SharedPreferences sharedPreferences=context.getSharedPreferences("BijinToken",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("Token1",BestBijn1);
        editor.putString("Token2",BestBijin2);
        editor.putString("Token3",BestBjin3);
        editor.commit();
    }
    public String getUserToken(Context context){
        String userToken=null;
        SharedPreferences sharedPreferences=context.getSharedPreferences("User",Context.MODE_PRIVATE);
        userToken=sharedPreferences.getString("UserToken","");
        return userToken;
    }
    public ArrayList<String> getBestToken(Context context){
        ArrayList<String> bijinToken=new ArrayList<String>();
        SharedPreferences sharedPreferences=context.getSharedPreferences("BijinToken",Context.MODE_PRIVATE);
        bijinToken.add(sharedPreferences.getString("Token1",""));
        bijinToken.add(sharedPreferences.getString("Token2",""));
        bijinToken.add(sharedPreferences.getString("Token3",""));
        return bijinToken;
    }
}
