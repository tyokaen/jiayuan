package com.example.jiayuan.library;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
public class buybook extends Activity {
ImageView imageView;
Button button,button1,button2;
TextView tx;
 int i=0;
   int j;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buybook);
        imageView = (ImageView) findViewById(R.id.ImageView4);
        button = (Button) findViewById(R.id.Button10);
        button1 = (Button) findViewById(R.id.Button11);
        button2=(Button)findViewById(R.id.Button12);
        tx=(TextView)findViewById(R.id.TextView15);
        imageView.setImageResource(showFragment.image[showFragment.i]);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 if(i<=0){
                     tx.setText(""+0);
                 }
                 else{
                     tx.setText(""+(--i));
                 }
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tx.setText(""+(++i));
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(buybook.this);
                builder.setTitle("确认信息");
                builder.setMessage("您需要支付:"+i*showFragment.price[showFragment.i]+"元");
                AlertDialog dialog=builder.create();
                dialog.show();
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_buybook, menu);
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
