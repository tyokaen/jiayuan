package com.example.jiayuan.bijin.login;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.example.jiayuan.bijin.R;

import java.util.ArrayList;
import java.util.Arrays;

public class signup extends AppCompatActivity implements View.OnClickListener {
private ScrollView sign_Sco;
private EditText Ed_sign_Id,Ed_sign_Pwd,Ed_sign_Pwd1,Ed_answer;
private TextView Tx_problem=null;
private Button Btn_to_sign2;
String[] problems=null;
String Id=null,Pwd=null,Answer=null;
int QuesId=0;
    ArrayList<String> list=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
         problems=getResources().getStringArray(R.array.problem);
        init();
    }
    public void init(){
       // sign_Sco=(ScrollView)findViewById(R.id.sign_root);
        Ed_sign_Id=(EditText)findViewById(R.id.Input_ID);
       setHint("ユーザー  *英数字で6文字以上" ,Ed_sign_Id);
        Ed_sign_Pwd=(EditText)findViewById(R.id.input_password);
        setHint("パスワート *英数字で8文字以上",Ed_sign_Pwd);
        Ed_sign_Pwd1=(EditText)findViewById(R.id.input_password1);
        setHint("パスワート(確認 )",Ed_sign_Pwd1);
        Btn_to_sign2=(Button)findViewById(R.id.btn_signup);
        Btn_to_sign2.setOnClickListener(this);
        Tx_problem=(TextView) findViewById(R.id.input_problem);
        //setHint("秘密の質問",Ed_problem);
        Tx_problem.setOnClickListener(this);
        Ed_answer=(EditText)findViewById(R.id.input_answer);
        setHint("秘密の質問",Ed_answer);
    }
    public void setHint(String hintStr,EditText editText){
        SpannableString ss=new SpannableString(hintStr);
        AbsoluteSizeSpan ass=new AbsoluteSizeSpan(15,true);
        ss.setSpan(ass,0,ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        editText.setHint(new SpannableString(ss));
    }
   public void putVal(Intent intent){
       intent.putExtra("Id",Ed_sign_Id.getText().toString());
       intent.putExtra("Pwd",Ed_sign_Pwd.getText().toString());
       intent.putExtra("QuesId",""+QuesId);
       intent.putExtra("Aws",Ed_answer.getText().toString());
       intent.putExtra("UserToken",this.getIntent().getStringExtra("UserToken"));
   }
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_signup:
                Intent intent=new Intent(this,signup2.class);
                putVal(intent);
                startActivity(intent);
                break;
            case R.id.input_problem:
                setOptionsPicker();
                break;
        }
    }
    public void setOptionsPicker(){
        OptionsPickerView pvOptions = new  OptionsPickerView.Builder(signup.this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3 ,View v) {
                //返回的分别是三个级别的选中位置
                String s =  problems[options1];
                QuesId=options1;
                Tx_problem.setText(""+QuesId);
            }
        })
//                        .setSubmitText("确定")//确定按钮文字
//                        .setCancelText("取消")//取消按钮文字
//                        .setTitleText("城市选择")//标题
                .setSubCalSize(20)//确定和取消文字大小
//                        .setTitleSize(20)//标题文字大小
//                        .setTitleColor(Color.BLACK)//标题文字颜色
                .setSubmitColor(Color.BLUE)//确定按钮文字颜色
                .setCancelColor(Color.BLUE)//取消按钮文字颜色
//                        .setTitleBgColor(0xFF333333)//标题背景颜色 Night mode
//                        .setBgColor(0xFF000000)//滚轮背景颜色 Night mode
//                        .setContentTextSize(18)//滚轮文字大小
//                        .setTextColorCenter(Color.BLUE)//设置选中项的颜色
                .setTextColorCenter(Color.BLACK)//设置选中项的颜色
//                        .setLineSpacingMultiplier(1.6f)//设置两横线之间的间隔倍数
//                        .setLinkage(false)//设置是否联动，默认true
//                        .setLabels("省", "市", "区")//设置选择的三级单位
//                        .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
//                        .setCyclic(false, false, false)//循环与否
//                        .setSelectOptions(1, 1, 1)  //设置默认选中项
//                        .setOutSideCancelable(false)//点击外部dismiss default true
//                        .isDialog(true)//是否显示为对话框样式
                .build();
        pvOptions.setPicker(Arrays.asList(problems));
        pvOptions.show();
    }
}
