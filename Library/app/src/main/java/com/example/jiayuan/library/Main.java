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
                        Intent intent1 = new Intent(getApplicationContext(), borrowbook.class);
                        startActivity(intent1);
                        break;
                    case R.id.RadioButton3:
                        Intent intent2 = new Intent(getApplicationContext(), research.class);
                        startActivity(intent2);
                        break;
                    case R.id.RadioButton4:
                        if(p!=null){
                        }
                        else {
                            initmPopupWindowView();
                            p.showAsDropDown(group, 1150, 0);
                        }
                }
            }
        });

    }
    public void initmPopupWindowView(){
        View view=getLayoutInflater().inflate(R.layout.pop_window,null,false);

     p = new PopupWindow(view, 250, 280);
     p.setOutsideTouchable(true);



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
