package com.example.jiayuan.library;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;


public class Main extends FragmentActivity {
RadioGroup radioGroup;
    showFragment showFragment;
    borrowFragment borrowfragment;
PopupWindow p=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        radioGroup = (RadioGroup) findViewById(R.id.Radiogroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.RadioButton1:
                        showFragment = new showFragment();
                        FragmentManager manager = getFragmentManager();
                        FragmentTransaction transaction = manager.beginTransaction();
                        transaction.replace(R.id.LinearLayout1, showFragment);
                        transaction.commit();
                        break;
                    case R.id.RadioButton2:
                       borrowfragment=new borrowFragment();
                        FragmentManager manager1=getFragmentManager();
                        FragmentTransaction transaction1=manager1.beginTransaction();
                        transaction1.replace(R.id.LinearLayout1,borrowfragment);
                        transaction1.commit();
                        break;
                    case R.id.RadioButton3:
                        Intent intent2 = new Intent(getApplicationContext(), research.class);
                        startActivity(intent2);
                        break;
                    case R.id.RadioButton4:
                        Intent intent3 = new Intent(getApplicationContext(), other.class);
                        startActivity(intent3);
                        break;
                }
            }
        });
    }
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
