package com.example.jiayuan.bijin.Presenter;

import android.os.Handler;
import android.os.Message;

import com.example.jiayuan.bijin.Tools.StringToJson;
import com.example.jiayuan.bijin.net.LoginRequest;

import org.json.JSONException;

import java.io.IOException;

import okhttp3.FormBody;

/**
 * Created by jiayuan on 2018/01/02.
 */

public class LoginAction {
    FormBody formBody=null;
  String result="",flag="";

   public  String  Login(String Id, String Pwd, final Handler handler) throws IOException {
       formBody=new FormBody.Builder()
               .add("screen_name",Id)
               .add("password",Pwd)
               .add("device_os","ios")
               .build();
       final LoginRequest loginRequest=new LoginRequest(formBody);
       new Thread(new Runnable() {
           @Override
           public void run() {
               try {
                   result= loginRequest.execute();
                   Message message=Message.obtain();
                   message.arg1=1;
                   try {
                       flag= StringToJson.ToJSon(result).getString("result");
                   } catch (JSONException e) {
                       e.printStackTrace();
                   }
                   message.obj=flag;
                   handler.sendMessage(message);
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
       }).start();

          return result;
   }
}
