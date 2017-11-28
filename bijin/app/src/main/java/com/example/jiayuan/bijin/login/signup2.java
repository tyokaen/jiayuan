package com.example.jiayuan.bijin.login;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.example.jiayuan.bijin.Okhttp.OkhttpGet;
import com.example.jiayuan.bijin.R;
import com.example.jiayuan.bijin.Tools.StringToJson;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;

public class signup2 extends AppCompatActivity implements View.OnClickListener {
    ScrollView sign2_scr;
    Spinner Sp_city;
    private int radius=20;
    Button Btn_sign;
    TextView Tx_birth,Tx_region,Tx_result;
    EditText Ed_name;
    String[] citys=null;
    String result=null,male=null;
    MediaType mediaType=MediaType.parse("application/x-www-form-urlencoded,charset=utf-8");
    RadioGroup radiogroup=null;
    OkHttpClient okhttpclient=new OkHttpClient();
    Myhandler myhandler=new Myhandler();
    JSONObject jsonobject=new JSONObject();
    JSONObject postobject=new JSONObject();
    class Myhandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.arg1==1)
                Tx_result.setText(result);
            if(StringToJson.JsonToString(result,"result").equals("true")){
                Intent intent=new Intent(signup2.this,MainActivity.class);
                startActivity(intent);
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);
        init();
        setCheckedListener();
    }

    private void setCheckedListener() {
        radiogroup.setOnCheckedChangeListener(mylistener);
    }
    RadioGroup.OnCheckedChangeListener mylistener=new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
            RadioButton radiobutton=(RadioButton)findViewById(checkedId);
            male=radiobutton.getText().toString();
        }
    };

    public void init(){
      Ed_name=(EditText) findViewById(R.id.Input_nickname);
        Tx_birth=(TextView)findViewById(R.id.input_birth);
        Tx_birth.setOnClickListener(this);
        Tx_region=(TextView)findViewById(R.id.input_region);
        Tx_region.setOnClickListener(this);
        radiogroup=(RadioGroup)findViewById(R.id.radioGroup);
        Btn_sign=(Button)findViewById(R.id.submit);
        Btn_sign.setOnClickListener(this);
        Tx_result=(TextView)findViewById(R.id.result);
        citys=getResources().getStringArray(R.array.city);
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.input_birth:
                setTime();
                break;
            case R.id.input_region:
                setCitys();
                break;
            case R.id.submit:
                post_User_Info();
        }
    }
    public void setTime(){
        TimePickerView pvTime = new TimePickerView.Builder(signup2.this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date2, View v) {//选中事件回调
                String time = getTime(date2);
                Tx_birth.setText(time);
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
        OptionsPickerView pvOptions = new OptionsPickerView.Builder(signup2.this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String s = citys[options1];
                Tx_region.setText(s);
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
    public String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        return format.format(date);
    }
    public String post_User_Info(){
         jsonobject=new JSONObject();
        postobject=new JSONObject();
        final FormBody formbody=new FormBody.Builder()
                .add("token",this.getIntent().getStringExtra("UserToken"))
                .add("screen_name",this.getIntent().getStringExtra("Id"))
                .add("password",this.getIntent().getStringExtra("Pwd"))
                .add("nickname",Ed_name.getText().toString())
                .add("gender","男性")
                .add("location",Tx_region.getText().toString())
                .add("date_of_birth","1992/01/12")
                .add("secret_question_id",this.getIntent().getStringExtra("Aws"))
                .add("secret_answer",this.getIntent().getStringExtra("Aws"))
                .add("device_os","ios")
                .build();
            /*
            jsonobject.put("token",this.getIntent().getStringExtra("UserToken"));
            jsonobject.put("screen_name",this.getIntent().getStringExtra("Id"));
            jsonobject.put("password",this.getIntent().getStringExtra("Pwd"));
            jsonobject.put("nickname",Ed_name.getText().toString());
            jsonobject.put("gender","男性");
            jsonobject.put("location",Tx_region.getText().toString());
            jsonobject.put("date_of_birth",
                    "1992/01/12");
            jsonobject.put("secret_question_id",this.getIntent().getStringExtra("QuesId"));
            jsonobject.put("secret_answer",this.getIntent().getStringExtra("Aws"));
            jsonobject.put("device_os","ios");
            postobject.put("Parameters",jsonobject);
            body=RequestBody.create(mediaType, String.valueOf(postobject));
            */
       /* body=RequestBody.create(mediaType,"token="+this.getIntent().getStringExtra("UserToken")+"&screen_name="+this.getIntent().getStringExtra("Id")
        +"&password="+this.getIntent().getStringExtra("Pwd")+"&nickname="+Ed_name.getText().toString()
        +"&gender="+"男性"+"&location="+Tx_region.getText().toString()+"&date_of_birth="+"1992/01/12"+"&secret_question_id="+this.getIntent().getStringExtra("QuesId")
        +"&secret_answer="+this.getIntent().getStringExtra("Aws")+"&device_os="+"ios");
        final RequestBody finalBody = body;
        */
        new Thread(new Runnable() {
            @Override
            public void run() {
                result=OkhttpGet.UsePost(okhttpclient,"http://192.168.0.118/BijinTemp/index.php/api/guest/upgrade","X-BijinScience","Bearer Mn6t5Dhfqz6hf4LtKToS19igKgeHDff0sCJNqQT6pzEvT0EEtT7L2FSnMWUzbaQuC9hSzbzF0eau4FYN859bl1pXxkxzknJNMRGmSgRtkSDF7C3gicht3wqQ7DqHRZ4EQkQJqIc1AGghs9n0CvKfIbWpEmW6l1kcCaLTJOut411NbFoDaYIJZFYERVldwvgZwSSfGnzl", formbody);
                Message message=new Message();
                message.arg1=1;
                myhandler.sendMessage(message);
            }
        }).start();

        return  result;
    }

}
