package com.example.jiayuan.library;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;


public class login extends Activity {
CheckBox c,c1;
Button b3,b4;
EditText e2,e3,e4,e5;
LibraryDbOpenHelper libraryDbOpenHelper;
public static SharedPreferences sp;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        c=(CheckBox)findViewById(R.id.CheckBox1);
        c1=(CheckBox)findViewById(R.id.CheckBox2);
        e2=(EditText)findViewById(R.id.EditText3);
        e3=(EditText)findViewById(R.id.EditText4);
        e4=(EditText)findViewById(R.id.EditText5);
        e5=(EditText)findViewById(R.id.EditText6);
        InputFilter inputFilter=new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if(source.toString().matches("^[0-9a-zA@##.##_##-]+$")){
                    return source;
                }
                 else{
                    return "";
                }
                }
        };
        InputFilter[] filters=new InputFilter[]{
                inputFilter
        };
        e5.setFilters(filters);
        b3=(Button)findViewById(R.id.Button4);
        b4=(Button)findViewById(R.id.Button5);
        c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    e3.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                }
                else{
                    e3.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        c1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    e4.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else{
                    e4.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
      sp=this.getSharedPreferences("demo-02",MODE_PRIVATE);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(e3.getText().toString().equals(e4.getText().toString()))){
                    AlertDialog.Builder builder=new AlertDialog.Builder(login.this);
                    builder.setTitle("提示");
                    builder.setMessage("两次输入的密码有误");
                   AlertDialog alertDialog=builder.create();
                    alertDialog.show();
                }
                else{
                    SharedPreferences.Editor editor=sp.edit();
                    String zhanghao=e2.getText().toString();
                    editor.putString("Zhanghao",zhanghao);
                    String password=e3.getText().toString();
                    editor.putString("Password",password);
                    editor.commit();
                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String recieve[]=new String[1];
                recieve[0]=e5.getText().toString();
               // String subject=e2.getText().toString();  //获取主题
                String content=e3.getText().toString();  //攻取内容
                String content1=e4.getText().toString();
                Intent intent=new Intent(Intent.ACTION_SEND);   //发送邮件使用ACTION_SEND
                intent.setType("plain/text");                   //设置类型
                intent.putExtra(Intent.EXTRA_EMAIL,recieve);
                intent.putExtra(Intent.EXTRA_SUBJECT,"主题");
                intent.putExtra(Intent.EXTRA_TEXT,content);
                intent.putExtra(Intent.EXTRA_TEXT,content1);
                login.this.startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
