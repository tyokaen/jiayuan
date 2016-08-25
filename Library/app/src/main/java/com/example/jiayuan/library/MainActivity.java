package com.example.jiayuan.library;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends Activity {
EditText ed,ed1;
    int i=0;
 Button b,b1,b2,b5;
  static String str,str1,str2,str3;
    TextView t;
 LibraryDbOpenHelper libraryDbOpenHelper;
 public SharedPreferences sp;
   public String str4;
    ImageView imageView;
    Handler handler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed=(EditText)findViewById(R.id.EditText1);
        ed1=(EditText)findViewById(R.id.EditText2);
        b=(Button)findViewById(R.id.button1);
        b1=(Button)findViewById(R.id.button2);
        b2=(Button)findViewById(R.id.button3);
        b5=(Button)findViewById(R.id.button6);
        t=(TextView)findViewById(R.id.TextView10);
        imageView=(ImageView)findViewById(R.id.ImageView2);
        sp=this.getSharedPreferences("demo-02",MODE_PRIVATE);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed.setText(null);
                ed1.setText(null);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),login.class);
                startActivity(intent);
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                str=ed.getText().toString();
                str1=ed1.getText().toString();
                str2=sp.getString("Zhanghao","未查到关键字");
                str3=sp.getString("Password","未查到关键字");
                t.setText(str3);
                if(!(str.equals(str2))||!(str1.equals(str3))){
                AlertDialog.Builder builder1=new AlertDialog.Builder(MainActivity.this);
                            builder1.setTitle("提示");
                            builder1.setMessage("请重新输入");
                            AlertDialog alertDialog=builder1.create();
                            alertDialog.show();
                        }
              else {
                    Intent intent1 = new Intent(getApplicationContext(), Main.class);
                    startActivity(intent1);
               }
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              changepassword(MainActivity.this);
      }
    });
        bofang();
    }
    public void bofang(){
        handler.postDelayed(runnable,1000);
    }
    public Runnable runnable = new Runnable(){
        public void run(){
            handler.postDelayed(this,1000);
            imageView.setImageResource(bookshow.image[i]);
            i++;
            if(i==9){
                i=0;
            }
        }
    };
    public void changepassword(Context context){
        final EditText editText=new EditText(this);
        AlertDialog.Builder builder1=new AlertDialog.Builder(context);
        builder1.setTitle("重新输入密码");
        //editText.setId(0);
        builder1.setView(editText);
        str4=editText.getText().toString();
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("Password",str4);
        editor.commit();
        builder1.setPositiveButton("确认",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,editText.getText(),Toast.LENGTH_SHORT).show();
            }
        });
        builder1.setNegativeButton("重新",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                editText.setText(null);
            }
        });
        AlertDialog alertDialog1=builder1.create();
        alertDialog1.show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
