package com.example.jiayuan.bijin;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.jiayuan.bijin.Okhttp.OkhttpGet;
import com.example.jiayuan.bijin.login.MainActivity;
import com.example.jiayuan.bijin.login.signup;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

public class function_choice extends AppCompatActivity implements View.OnClickListener {
Button Btn_SignUp,Btn_Login,Btn_ImageChoice=null;
String ImageToken=null;
OkHttpClient okHttpClient=new OkHttpClient();
RequestBody body=null;
Myhandler myhandler=new Myhandler();
class Myhandler extends Handler{
    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        if(msg.arg1==1) {
            Intent intentChoice = new Intent(function_choice.this, Image_Choice.class);
            intentChoice.putExtra("ImageToken", ImageToken);
            startActivity(intentChoice);
        }
    }
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function_choice);
        Btn_ImageChoice=(Button) findViewById(R.id.To_ImageChoice);
        Btn_SignUp=(Button)findViewById(R.id.To_SignUp);
        Btn_Login=(Button)findViewById(R.id.To_login);
        Btn_ImageChoice.setOnClickListener(this);
        Btn_Login.setOnClickListener(this);
        Btn_SignUp.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
         switch(v.getId()){
             case R.id.To_SignUp:
                 Intent intentSign=new Intent(function_choice.this,signup.class);
                 intentSign.putExtra("UserToken",function_choice.this.getIntent().getStringExtra("UserToken"));
                 startActivity(intentSign);
                 break;
             case R.id.To_login:
                 Intent intentLogin=new Intent(function_choice.this, MainActivity.class);
                 startActivity(intentLogin);
                 break;
             case R.id.To_ImageChoice:
                 new Thread(new Runnable() {
                     @Override
                     public void run() {
                         ImageToken=OkhttpGet.UseGet(okHttpClient,"http://192.168.0.118/BijinTemp/index.php/api/bijin/setup?user_token=4cc2dd5dd4d3e24738606d97aac890b0&count=10","X-BijinScience",
                                 "Bearer Mn6t5Dhfqz6hf4LtKToS19igKgeHDff0sCJNqQT6pzEvT0EEtT7L2FSnMWUzbaQuC9hSzbzF0eau4FYN859bl1pXxkxzknJNMRGmSgRtkSDF7C3gicht3wqQ7DqHRZ4EQkQJqIc1AGghs9n0CvKfIbWpEmW6l1kcCaLTJOut411NbFoDaYIJZFYERVldwvgZwSSfGnzl",body);
                         Message message=new Message();
                         message.arg1=1;
                         myhandler.sendMessage(message);

                     }
                 }).start();

                 break;
         }
    }
}
