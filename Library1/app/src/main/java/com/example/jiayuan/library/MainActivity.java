package com.example.jiayuan.library;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class MainActivity extends Activity {
    ListView lv;
    DrawerLayout drawerLayout;
    RelativeLayout relativeLayout;
    ArrayList<String> list=new ArrayList<String>();
    ArrayAdapter<String> adapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawerlayout);
        relativeLayout=(RelativeLayout)findViewById(R.id.Left);
        lv=(ListView)relativeLayout.findViewById(R.id.ListView1);
        list.add("游客");
        list.add("发布人");
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    Customer_login customer_login=new Customer_login();
                    FragmentManager manager=getFragmentManager();
                    FragmentTransaction transition=manager.beginTransaction();
                    transition.replace(R.id.LinearLayout,customer_login) ;
                    transition.commit();
                }
                if(i==1){
                  Login_provider login_provider=new Login_provider();
                    FragmentManager manager=getFragmentManager();
                    FragmentTransaction transaction=manager.beginTransaction();
                    transaction.replace(R.id.LinearLayout,login_provider);
                    transaction.commit();
                }
            }
        });

    }

    }

