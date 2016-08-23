package com.example.jiayuan.firstapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class jiayuan extends Activity {
  TextView getDis,getCa,getspeed;
  EditText ed;
  double i,k,b,e;
  int j,a;
  Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jiayuan);
        getDis=(TextView)findViewById(R.id.TextView5);
        getCa=(TextView)findViewById(R.id.TextView8);
        ed=(EditText)findViewById(R.id.EditText1);
        button=(Button)findViewById(R.id.button3);
        getspeed=(TextView)findViewById(R.id.TextView11);
        i=MainActivity.step*0.7;
        getDis.setText(""+i+"m");
        e=60*MainActivity.c+MainActivity.d;
        getspeed.setText(""+i/e+"m/s");
    }
   public void changeClick(View view){
       j=Integer.parseInt(ed.getText().toString());
       k=j*i*0.0001;
       getCa.setText(""+k+"Kcal");
   }
   // @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_jiayuan, menu);
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
