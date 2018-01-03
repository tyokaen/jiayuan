package com.example.jiayuan.bijin.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.example.jiayuan.bijin.Okhttp.OkhttpGet;
import com.example.jiayuan.bijin.R;
import com.example.jiayuan.bijin.cache.UserTokenCache;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;

import static com.example.jiayuan.bijin.R.id.Ed_user_birth;
import static com.example.jiayuan.bijin.R.id.Ed_user_local;
import static com.example.jiayuan.bijin.R.id.Ed_user_sex;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
EditText Ed_user_nickname,Ed_user_id,Ed_user_newPwd;
  TextView Tx_user_local,Tx_user_birth,Tx_user_sex,textView;
Button Btn_info_sub;
String citys[]=null;
String sexs[]=null;
String birthday=null,sex=null;
String result;
OkHttpClient okHttpClient=new OkHttpClient();
MyHandler myhandler=new MyHandler();
class MyHandler extends Handler{
    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        if(msg.arg1==1){
            textView.setText(result);
        }
    }
}

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Ed_user_nickname=(EditText)findViewById(R.id.Ed_user_name);
        Tx_user_local=(TextView) findViewById(Ed_user_local);
        Tx_user_birth=(TextView)findViewById(Ed_user_birth);
        Tx_user_sex=(TextView) findViewById(Ed_user_sex);
        Ed_user_id=(EditText)findViewById(R.id.Ed_user_id);
        Ed_user_newPwd=(EditText)findViewById(R.id.Ed_user_pwd);
        Ed_user_id.setText(UserTokenCache.getInstance().getUserScreenName(this).toString());
        Ed_user_nickname.setText(UserTokenCache.getInstance().getUserNick(this).toString());
        Tx_user_birth.setText(UserTokenCache.getInstance().getUserBirth(this).toString());
        Tx_user_birth.setOnClickListener(this);
        Tx_user_local.setText(UserTokenCache.getInstance().getUserLocal(this).toString());
        Tx_user_local.setOnClickListener(this);
        Tx_user_sex.setText(UserTokenCache.getInstance().getUserSex(this).toString());
        Btn_info_sub=(Button)findViewById(R.id.Btn_user_info);
        Btn_info_sub.setOnClickListener(this);
        Tx_user_sex.setOnClickListener(this);
        textView=(TextView)findViewById(R.id.text);
        citys=getResources().getStringArray(R.array.city);
        sexs=getResources().getStringArray(R.array.sex);

    }
    public void setTime(){
        TimePickerView pvTime = new TimePickerView.Builder(ProfileActivity.this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date2, View v) {//选中事件回调
                 birthday= getTime(date2);
                Tx_user_birth.setText(birthday.toString());
            }
        })
                .setType(TimePickerView.Type.YEAR_MONTH_DAY)//默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确定")//确认按钮文字
                .setContentSize(20)//滚轮文字大小
                .setTitleSize(20)//标题文字大小
                .setTitleText("请选择时间")//标题文字
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(true)//是否循环滚动
                .setTextColorCenter(Color.BLACK)//设置选中项的颜色
                .setTitleColor(Color.BLACK)//标题文字颜色
                .setSubmitColor(Color.BLUE)//确定按钮文字颜色
                .setCancelColor(Color.BLUE)//取消按钮文字颜色
                .isCenterLabel(false)
                .build();
        pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        pvTime.show();
    }
    public void setCitys() {
        OptionsPickerView pvOptions = new OptionsPickerView.Builder(ProfileActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String region = citys[options1];
                Tx_user_local.setText(region.toString());
            }
        })
                .setSubCalSize(20)//确定和取消文字大小
                .setSubmitColor(Color.BLUE)//确定按钮文字颜色
                .setCancelColor(Color.BLUE)//取消按钮文字颜色
                .setTextColorCenter(Color.BLACK)//设置选中项的颜色
                .build();
        pvOptions.setPicker(Arrays.asList(citys));
        pvOptions.show();
    }
    public void setSex() {
        OptionsPickerView pvOptions = new OptionsPickerView.Builder(ProfileActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String sex = citys[options1];
                Tx_user_sex.setText(sex.toString());
            }
        })
                .setSubCalSize(20)//确定和取消文字大小
                .setSubmitColor(Color.BLUE)//确定按钮文字颜色
                .setCancelColor(Color.BLUE)//取消按钮文字颜色
                .setTextColorCenter(Color.BLACK)//设置选中项的颜色
                .build();
        pvOptions.setPicker(Arrays.asList(sexs));
        pvOptions.show();
    }

    public String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        return format.format(date);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case Ed_user_birth:
                setTime();
                break;
            case Ed_user_local:
                setCitys();
                break;
            case Ed_user_sex:
                setSex();
                break;
            case R.id.Btn_user_info:
                postInfo();
                break;
        }
    }
    public void postInfo(){
        final FormBody formbody=new FormBody.Builder()
                .add("user_token",UserTokenCache.getInstance().getUserToken(this))
                .add("nickname",Ed_user_nickname.getText().toString())
                .add("location",Tx_user_local.getText().toString())
                .add("date_of_birth",Tx_user_birth.getText().toString())
                .add("gender",Tx_user_sex.getText().toString())
                .add("current_password",UserTokenCache.getInstance().getUserPwd(this))
                .add("new_password",Ed_user_newPwd.getText().toString())
                .build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                result= OkhttpGet.UsePost(okHttpClient,"http://192.168.0.103/BijinScience-Web/index.php/api/user/update","X-BijinScience","Bearer Mn6t5Dhfqz6hf4LtKToS19igKgeHDff0sCJNqQT6pzEvT0EEtT7L2FSnMWUzbaQuC9hSzbzF0eau4FYN859bl1pXxkxzknJNMRGmSgRtkSDF7C3gicht3wqQ7DqHRZ4EQkQJqIc1AGghs9n0CvKfIbWpEmW6l1kcCaLTJOut411NbFoDaYIJZFYERVldwvgZwSSfGnzl", formbody);
                Message message=new Message();
                message.arg1=1;
                myhandler.sendMessage(message);
            }
        }).start();


    }
}
