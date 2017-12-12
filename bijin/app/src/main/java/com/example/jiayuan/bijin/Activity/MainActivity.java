package com.example.jiayuan.bijin.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jiayuan.bijin.Okhttp.OkhttpGet;
import com.example.jiayuan.bijin.R;
import com.example.jiayuan.bijin.Tools.StringToJson;
import com.example.jiayuan.bijin.cache.UserTokenCache;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText Ed_id, Ed_pwd;
    TextView textView=null,textView1,textView2,textView3,textView4,textView5;
    Button Btn_login, Btn_register;
    TextView Tx_res;
    TextWatcher textWatcher=null;
    ScrollView main_scroll=null;
    private int radius=20;
    String userToken=null;
    String result=null;
    MyHandler myhandler=new MyHandler();
    String flag="";
    ArrayList<String> arrayList=new ArrayList<String>();
    OkHttpClient okHttpClient=new OkHttpClient();
    String imagetoken=null;
    StringBuffer sb=new StringBuffer();
ExecutorService executorService= Executors.newCachedThreadPool();
    int count=1;
    MyHandler myHandler=new MyHandler();
    ArrayList<Future> futureArrayList=new ArrayList<Future>();
    ProgressDialog progressDialog=null;
    class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            if (msg.arg1==1) {
                UserTokenCache.getInstance().storeUserToken(MainActivity.this,StringToJson.JsonToString(result,"user_token"));
                ShiftToMain(Boolean.parseBoolean((String)msg.obj));
            }
        }
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0 && Ed_pwd.getText().length() > 0) {
                    Btn_login.setClickable(true);
                    Btn_login.setBackground(getResources().getDrawable(R.drawable.to_login_button));
                }
            }
        };
        Ed_id.addTextChangedListener(textWatcher);
        Ed_pwd.addTextChangedListener(textWatcher);
        Btn_login.setOnClickListener(this);

        //Tx_res.setOnClickListener(this);
    }
public void init(){
    Ed_id=(EditText)findViewById(R.id.Input_ID);
    Ed_pwd=(EditText)findViewById(R.id.Input_Pwd);
    Btn_login=(Button)findViewById(R.id.login);
    main_scroll=(ScrollView)findViewById(R.id.main_root);
   // Btn_register.setBackgroundColor(Color.parseColor("#FF8C00"));
}
    @Override
    public void onClick(View v) {
        switch(v.getId()){
           // case R.id.register:
               // Intent intent=new Intent(this,signup.class);
               // startActivity(intent);
            case R.id.login:
                //login_success(Ed_id.getText().toString(),Ed_pwd.getText().toString());
                //login_success(Ed_id.getText().toString(),Ed_pwd.getText().toString());
            //getRankingImage();
                Intent intent=new Intent(MainActivity.this,user_main.class);
                startActivity(intent);
        }
    }
    public  void ShiftToMain(boolean flag){
        if(flag){
            Intent intent=new Intent(this, user_main.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(this,"账号或密码有误",Toast.LENGTH_SHORT).show();
        }
    }
    public boolean login_success(String Id,String Pwd){
        final FormBody formBody=new FormBody.Builder()
                .add("screen_name",Id)
                .add("password",Pwd)
                .add("device_os","ios")
                .build();
        createDialog();
        new Thread(new Runnable() {
            public void run() {
                result= OkhttpGet.UsePost(okHttpClient,"http://192.168.0.118/BijinTemp/index.php/api/login","X-BijinScience","Bearer Mn6t5Dhfqz6hf4LtKToS19igKgeHDff0sCJNqQT6pzEvT0EEtT7L2FSnMWUzbaQuC9hSzbzF0eau4FYN859bl1pXxkxzknJNMRGmSgRtkSDF7C3gicht3wqQ7DqHRZ4EQkQJqIc1AGghs9n0CvKfIbWpEmW6l1kcCaLTJOut411NbFoDaYIJZFYERVldwvgZwSSfGnzl", formBody);
                try {
                    flag= StringToJson.ToJSon(result).getString("result");
                } catch (JSONException e) {
                    //e.printStackTrace();
                }
                Message message=new Message();
                message.arg1=1;
                message.obj=flag;
                myhandler.sendMessage(message);
            }
        }).start();
       return Boolean.parseBoolean(flag);
    }
    public void createDialog() {
        progressDialog = ProgressDialog.show(this, "提示", "ログインしています");
    }
    }




