package com.example.jiayuan.bijin.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.jiayuan.bijin.R;

public class input_finish extends AppCompatActivity implements View.OnClickListener {
    Button Btn_back_mypage=null,Btn_back_like,Btn_back_image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_finish);
        Btn_back_mypage=(Button)findViewById(R.id.back_to_mypage);
        Btn_back_mypage.setOnClickListener(this);
        Btn_back_like=(Button)findViewById(R.id.back_to_likelist);
        Btn_back_like.setOnClickListener(this);
        Btn_back_image=(Button)findViewById(R.id.sample_oncemore);
        Btn_back_image.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_to_mypage:
                Intent intent=new Intent(input_finish.this,user_main.class);
                startActivity(intent);
                break;
            case R.id.back_to_likelist:
                Intent intent1=new Intent(input_finish.this,user_main.class);
                intent1.putExtra("shift","ToLikeList");
                startActivity(intent1);
                break;
            case R.id.sample_oncemore:
                Intent intent2=new Intent(input_finish.this,user_main.class);
                intent2.putExtra("shift","ToInputSample");
                startActivity(intent2);
                break;
        }
    }
}
