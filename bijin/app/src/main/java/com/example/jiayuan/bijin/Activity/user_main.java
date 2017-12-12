package com.example.jiayuan.bijin.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.example.jiayuan.bijin.Fragment.ModelRankFragment;
import com.example.jiayuan.bijin.Fragment.RankListFragment;
import com.example.jiayuan.bijin.Fragment.SampleFragment;
import com.example.jiayuan.bijin.Fragment.SettingFragment;
import com.example.jiayuan.bijin.Fragment.TagChoiceFragment;
import com.example.jiayuan.bijin.Fragment.opinionFragment;
import com.example.jiayuan.bijin.Okhttp.OkhttpGet;
import com.example.jiayuan.bijin.R;
import com.example.jiayuan.bijin.cache.UserTokenCache;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.concurrent.Future;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

public class user_main extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ListView left_menu;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView=null;
    SettingFragment settingFragment=null;
  SampleFragment sampleFragment=null;
    ModelRankFragment modelRankFragment=null;
    RankListFragment rankListFragment=null;
    opinionFragment opinionfragment=null;
    TagChoiceFragment tagChoiceFragment=null;
   android.support.v4.app.Fragment myPageFragment=null;
    android.support.v4.app.FragmentManager fragmentManger;
    String imagetoken=null;
    String likelist=null,best1_name,best2_name,best3_name;
    ArrayList<String>bijinToken=new ArrayList<String>();
    JSONArray jsonArray=new JSONArray();
    MyHandler myHandler=new MyHandler();
    ProgressDialog progressdialog=null;
    StringBuffer sb=new StringBuffer();
    ArrayList<Future> futureArrayList=new ArrayList<Future>();
    OkHttpClient okHttpClient=new OkHttpClient();
    Bundle bundle=new Bundle();
    class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.arg1==1) {
                imagetoken=(String)msg.obj;
                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction trans=fragmentManager.beginTransaction();
                Transction(R.id.frg_root,sampleFragment,"com.example.jiayuan.bijin.Fragment.SampleFragment",trans);
                drawerLayout.closeDrawers();
            }
            else if(msg.arg1==2){
                likelist=(String)msg.obj;
                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction trans=fragmentManager.beginTransaction();
                Transction(R.id.frg_root,rankListFragment,"com.example.jiayuan.bijin.Fragment.RankListFragment",trans);
                drawerLayout.closeDrawers();
            }
        }
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);
         Intent intent=getIntent();
        if(!(intent==null)) {
            String str = intent.getStringExtra("shift");
            if (str != null) {
                if (str.equals("ToTagChoice")) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction trans = fragmentManager.beginTransaction();
                    Transction(R.id.frg_root, tagChoiceFragment, "com.example.jiayuan.bijin.Fragment.TagChoiceFragment", trans);
                }
            } else {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction trans = fragmentManager.beginTransaction();
                Transction(R.id.frg_root, myPageFragment, "com.example.jiayuan.bijin.Fragment.MyPageFragment", trans);
            }
        }

        init();
        toolbar.setTitle("toolbar");
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open, R.string.close){
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        actionBarDrawerToggle.syncState();
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        drawerLayout.closeDrawers();
    }
    public void init(){
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer);
        //left_menu=(ListView)findViewById(R.id.lv_menu);
        toolbar=(Toolbar)findViewById(R.id.tool);
        navigationView=(NavigationView)findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction trans;
                trans=fragmentManager.beginTransaction();
                switch (item.getItemId()){
                    case R.id.item1:
                        Transction(R.id.frg_root, myPageFragment, "com.example.jiayuan.bijin.Fragment.MyPageFragment", trans);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.item2:
                       getImageToken();
                        break;
                    case R.id.item3:
                        Transction(R.id.frg_root,opinionfragment,"com.example.jiayuan.bijin.Fragment.opinionFragment",trans);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.item4:
                        getLikeListToken();
                        break;
                    case R.id.item5:
                        Transction(R.id.frg_root,modelRankFragment,"com.example.jiayuan.bijin.Fragment.ModelRankFragment",trans);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.item6:
                        Transction(R.id.frg_root,tagChoiceFragment,"com.example.jiayuan.bijin.Fragment.TagChoiceFragment",trans);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.item7:
                        Transction(R.id.frg_root,settingFragment,"com.example.jiayuan.bijin.Fragment.SettingFragment",trans);
                        drawerLayout.closeDrawers();
                        break;
                }
                return true;
            }
        });
    }
    public void Transction(int id, android.support.v4.app.Fragment fragment, String classname,FragmentTransaction transaction){
       Class clazz=null;
           try {
               clazz= Class.forName(classname);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
           if(fragment==null){
               try {
                   fragment=(android.support.v4.app.Fragment)clazz.newInstance();
                   transaction.replace(id,fragment);
                   transaction.commit();
                   if(fragment instanceof SampleFragment){
                       bundle.putString("ImageToken",imagetoken);
                       fragment.setArguments(bundle);
                   }
                   else if(fragment instanceof RankListFragment){
                       bundle.putString("bijinlist",likelist);
                       fragment.setArguments(bundle);
                   }
               } catch (InstantiationException e) {
                   e.printStackTrace();
               } catch (IllegalAccessException e) {
                   e.printStackTrace();
               }
           }
           else {
               transaction.replace(id, fragment);
               transaction.commit();
           }

    }
    public void createDialog() {
        progressdialog = ProgressDialog.show(this, "提示", "正在获取");
    }
    public void getImageToken(){
        final RequestBody body=null;
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkhttpGet.UseGetString(okHttpClient,"http://192.168.0.118/BijinTemp/index.php/api/bijin/setup?user_token="+ UserTokenCache.getInstance().getUserToken(user_main.this)+"&count=10","X-BijinScience",
                        "Bearer Mn6t5Dhfqz6hf4LtKToS19igKgeHDff0sCJNqQT6pzEvT0EEtT7L2FSnMWUzbaQuC9hSzbzF0eau4FYN859bl1pXxkxzknJNMRGmSgRtkSDF7C3gicht3wqQ7DqHRZ4EQkQJqIc1AGghs9n0CvKfIbWpEmW6l1kcCaLTJOut411NbFoDaYIJZFYERVldwvgZwSSfGnzl",body,myHandler,1);
            }
        }).start();
    }
    public void getLikeListToken(){
        final RequestBody body=null;
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkhttpGet.UseGetString(okHttpClient,"http://192.168.0.118/BijinTemp/index.php/api/vote?user_token="+UserTokenCache.getInstance().getUserToken(user_main.this)+"&vote=upper","X-BijinScience",
                        "Bearer Mn6t5Dhfqz6hf4LtKToS19igKgeHDff0sCJNqQT6pzEvT0EEtT7L2FSnMWUzbaQuC9hSzbzF0eau4FYN859bl1pXxkxzknJNMRGmSgRtkSDF7C3gicht3wqQ7DqHRZ4EQkQJqIc1AGghs9n0CvKfIbWpEmW6l1kcCaLTJOut411NbFoDaYIJZFYERVldwvgZwSSfGnzl",body,myHandler,2);
            }
        }).start();
    }
    }



