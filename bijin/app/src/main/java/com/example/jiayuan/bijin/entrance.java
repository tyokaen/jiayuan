package com.example.jiayuan.bijin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.jiayuan.bijin.Okhttp.OkhttpGet;
import com.example.jiayuan.bijin.Tools.GetUserToken;
import com.example.jiayuan.bijin.Tools.StringToJson;
import com.example.jiayuan.bijin.login.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

public class entrance extends AppCompatActivity implements View.OnClickListener {
Button Btn_image_page=null,To_main_page=null;
TextView textView=null,textView1=null;
SharedPreferences sp=null;
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    OkHttpClient httpClient=new OkHttpClient();
    String result,ImageToken=null,UserToken;
    ProgressDialog dialog=null;
    MyHandler myhadler=new MyHandler();
class MyHandler extends Handler{
    @Override
    public void handleMessage(Message msg) {
        if(msg.arg1==1) {
            dialog.cancel();
            textView.setText(result);
            textView1.setText(ImageToken);
             ShiftToImage(result);
        }
    }
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrance);
        Btn_image_page=(Button)findViewById(R.id.image_choice_page);
        To_main_page=(Button)findViewById(R.id.login_page);
        textView=(TextView)findViewById(R.id.Successful);
        textView1=(TextView) findViewById(R.id.Successful2);
        Btn_image_page.setOnClickListener(this);
        To_main_page.setOnClickListener(this);
    }
   void ToMainActivity(){
        Intent intent=new Intent(entrance.this,MainActivity.class);
        startActivity(intent);
    }
    String doGet() throws IOException, JSONException {
        MediaType Json=MediaType.parse("application/json;charset=utf-8");
        JSONObject json=new JSONObject();
        json.put("","");
        RequestBody requestBody=RequestBody.create(Json, String.valueOf(json));
        result=OkhttpGet.UsePost(httpClient,"http://192.168.0.118/BijinTemp/index.php/api/guest/register","X-BijinScience","Bearer Mn6t5Dhfqz6hf4LtKToS19igKgeHDff0sCJNqQT6pzEvT0EEtT7L2FSnMWUzbaQuC9hSzbzF0eau4FYN859bl1pXxkxzknJNMRGmSgRtkSDF7C3gicht3wqQ7DqHRZ4EQkQJqIc1AGghs9n0CvKfIbWpEmW6l1kcCaLTJOut411NbFoDaYIJZFYERVldwvgZwSSfGnzl",requestBody);
        return  result;
    }
    public void createDialog(){
        dialog=ProgressDialog.show(this,"提示","正在获取");
    }
    public void ShiftToImage(String target){
            if(target.equals("true")) {
                Intent intent = new Intent(this, Image_Choice.class);
                intent.putExtra("ImageToken",ImageToken);
                intent.putExtra("UserToken",UserToken);
                startActivity(intent);
            }
    }
    void ToImageChoice()  {
        createDialog();
        new Thread(new Runnable() {
            RequestBody body=null;
            @Override
            public void run() {
                try {
                    try {
                        if(doGet()!=null)
                            try {
                                String AllResult=doGet();
                               result= StringToJson.ToJSon(AllResult).getString("result");
                                UserToken=StringToJson.ToJSon(AllResult).getString("token");
                                storeUserToken(UserToken);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }  catch (IOException e) {
                    e.printStackTrace();
                }
                ImageToken= OkhttpGet.UseGet(httpClient,"http://192.168.0.118/BijinTemp/index.php/api/bijin/setup?user_token=4cc2dd5dd4d3e24738606d97aac890b0&count=10","X-BijinScience",
                        "Bearer Mn6t5Dhfqz6hf4LtKToS19igKgeHDff0sCJNqQT6pzEvT0EEtT7L2FSnMWUzbaQuC9hSzbzF0eau4FYN859bl1pXxkxzknJNMRGmSgRtkSDF7C3gicht3wqQ7DqHRZ4EQkQJqIc1AGghs9n0CvKfIbWpEmW6l1kcCaLTJOut411NbFoDaYIJZFYERVldwvgZwSSfGnzl",body);
                Message message = new Message();
                message.arg1 = 1;
                myhadler.sendMessage(message);
            }
        }).start();
}
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_choice_page:
                    ToImageChoice();
                break;
            case R.id.login_page:
                ToMainActivity();
                break;
        }
    }

    /**
     * Created by jiayuan on 2017/11/01.
     */
    public void storeUserToken(String usertoken){
     sp= GetUserToken.getInstance().getSp(this);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("usertoken",usertoken);
        editor.commit();
    }
}
