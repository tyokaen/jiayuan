package com.example.jiayuan.bijin.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jiayuan.bijin.Okhttp.OkhttpGet;
import com.example.jiayuan.bijin.R;
import com.example.jiayuan.bijin.Tools.StringToJson;
import com.example.jiayuan.bijin.Activity.user_main;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

public class bijin_best_detail extends AppCompatActivity implements View.OnClickListener {
    TextView Tx_Bijin_name=null,Tx_bijin_address,Tx_bijin_job,Tx_birth,Tx_height;
    ImageView Img_bijin;
    Button Btn_bijin_choose=null;
    String bijin_info=null;
    OkHttpClient okHttpClient=new OkHttpClient();
    byte[] b=new byte[100];
    MyHandler myHandler=new MyHandler();
    String bijinname=null;
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.Btn_bijin_choose:
                Intent intent=new Intent(bijin_best_detail.this,user_main.class);
                    intent.putExtra("ImageToken",getIntent().getStringExtra("ImageToken"));
                    intent.putExtra("bijinname",bijinname);
                if(getIntent().getIntExtra("ButtonToken",100)==1)
                    bijin_best_detail.this.setResult(1,intent);
                else if(getIntent().getIntExtra("ButtonToken",100)==2)
                    bijin_best_detail.this.setResult(2,intent);
                else if(getIntent().getIntExtra("ButtonToken",100)==3)
                    bijin_best_detail.this.setResult(3,intent);
                finish();
        }
    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.arg1==1){
                Tx_Bijin_name.setText(StringToJson.JsonToString((String)msg.obj,"nickname"));
                bijinname=StringToJson.JsonToString((String)msg.obj,"nickname");
                Tx_bijin_address.setText(StringToJson.JsonToString((String)msg.obj,"prefecture"));
                Tx_bijin_job.setText(StringToJson.JsonToString((String)msg.obj,"occupation"));
                Tx_birth.setText(StringToJson.JsonToString((String)msg.obj,"birthday"));
                Tx_height.setText(StringToJson.JsonToString((String)msg.obj,"height"));
            }
            if(msg.arg1==2) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
                Img_bijin.setImageBitmap(bitmap);
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bijin_best_detail);
        Tx_Bijin_name=(TextView)findViewById(R.id.Tx_Best_bijin_name);
        Tx_bijin_address=(TextView)findViewById(R.id.Tx_Best_bijin_address);
        Tx_bijin_job=(TextView)findViewById(R.id.Tx_Best_bijin_job);
        Tx_birth=(TextView)findViewById(R.id.Tx_Best_bijin_birth);
        Tx_height=(TextView)findViewById(R.id.Tx_Best_bijin_height);
        Img_bijin=(ImageView)findViewById(R.id.Best_bijin_detail_show);
        bijin_info=getIntent().getStringExtra("ImageToken");
        Btn_bijin_choose=(Button)findViewById(R.id.Btn_bijin_choose);
        Btn_bijin_choose.setOnClickListener(this);
        getBijinInfo();
    }
    public void getBijinInfo(){
        final RequestBody requestBody=null;
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkhttpGet.UseGetString(okHttpClient, "http://192.168.0.118/BijinTemp/index.php/api/bijin/info?token="+getIntent().getStringExtra("ImageToken"),"X-BijinScience",
                        "Bearer Mn6t5Dhfqz6hf4LtKToS19igKgeHDff0sCJNqQT6pzEvT0EEtT7L2FSnMWUzbaQuC9hSzbzF0eau4FYN859bl1pXxkxzknJNMRGmSgRtkSDF7C3gicht3wqQ7DqHRZ4EQkQJqIc1AGghs9n0CvKfIbWpEmW6l1kcCaLTJOut411NbFoDaYIJZFYERVldwvgZwSSfGnzl", requestBody,myHandler,1);
                getImage(requestBody,myHandler);
            }
        }).start();
    }
    public void getImage(RequestBody requestBody,Handler handler){
        b = OkhttpGet.UseGetImage(okHttpClient, "http://192.168.0.118/BijinTemp/index.php/api/bijin/image?token=" + getIntent().getStringExtra("ImageToken") + "&size=large", "X-BijinScience",
                "Bearer Mn6t5Dhfqz6hf4LtKToS19igKgeHDff0sCJNqQT6pzEvT0EEtT7L2FSnMWUzbaQuC9hSzbzF0eau4FYN859bl1pXxkxzknJNMRGmSgRtkSDF7C3gicht3wqQ7DqHRZ4EQkQJqIc1AGghs9n0CvKfIbWpEmW6l1kcCaLTJOut411NbFoDaYIJZFYERVldwvgZwSSfGnzl", requestBody);
        Message message=new Message();
        message.arg1=2;
        handler.sendMessage(message);

    }
}
