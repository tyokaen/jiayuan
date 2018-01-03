package com.example.jiayuan.bijin.cache;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by jiayuan on 2017/11/28.
 */

public class UserTokenCache {
    public static UserTokenCache userTokenCache=null;
    public  SharedPreferences s=null;
    SharedPreferences.Editor editor=null;
    private UserTokenCache(){};
    public static UserTokenCache getInstance(){
        if(userTokenCache==null){
            userTokenCache=new UserTokenCache();
        }
        else return userTokenCache;
        return userTokenCache;
    }
    public void storeUserToken(Context context,String user){
        s=context.getSharedPreferences("User",Context.MODE_PRIVATE);
        editor=s.edit();
        editor.putString("UserToken",user);
        editor.commit();
    }
    public void storeUserInfo(Context context,String userId,String nickname,String local,String birth,String sex){
        s=context.getSharedPreferences("User",Context.MODE_PRIVATE);
        editor=s.edit();
        editor.putString("UserId",userId);
        editor.putString("Usernick",nickname);
        editor.putString("Userlocal",local);
        editor.putString("Userbirth",birth);
        editor.putString("UserSex",sex);
        editor.commit();
    }
    public void storeUserPwd(Context context,String pwd){
        s=context.getSharedPreferences("User",Context.MODE_PRIVATE);
        editor=s.edit();
        editor.putString("UserPwd",pwd);
        editor.commit();

    }
    public String getUserPwd(Context context){
        String Pwd=null;
        s=context.getSharedPreferences("User",Context.MODE_PRIVATE);
        Pwd=s.getString("UserPwd","");
        return Pwd;
    }

    public String getUserToken(Context context){
        String userToken=null;
        s=context.getSharedPreferences("User",Context.MODE_PRIVATE);
        userToken=s.getString("UserToken","");
        return userToken;
    }
    public String getUserScreenName(Context context){
        String screenName=null;
        s=context.getSharedPreferences("User",Context.MODE_PRIVATE);
        screenName=s.getString("UserId","");
        return screenName;
    }
    public String getUserNick(Context context){
        String nickname=null;
        s=context.getSharedPreferences("User",Context.MODE_PRIVATE);
        nickname=s.getString("Usernick","");
        return nickname;
    }
    public String getUserLocal(Context context){
        String UserLocal=null;
        s=context.getSharedPreferences("User",Context.MODE_PRIVATE);
        UserLocal=s.getString("Userlocal","");
        return UserLocal;
    }
    public String getUserBirth(Context context){
        String UserBirth=null;
        s=context.getSharedPreferences("User",Context.MODE_PRIVATE);
        UserBirth=s.getString("Userbirth","");
        return UserBirth;
    }
    public String getUserSex(Context context){
        String UserSex=null;
        s=context.getSharedPreferences("User",Context.MODE_PRIVATE);
        UserSex=s.getString("UserSex","");
        return UserSex;
    }
   public void deldToken(){
       editor.clear();
       editor.commit();
   }
   public boolean isEmpty(Context context){
       if(getUserNick(context)==null&&getUserBirth(context)==null&&getUserLocal(context)==null&&getUserSex(context)==null)
           return true;
       else return false;

   }

}
