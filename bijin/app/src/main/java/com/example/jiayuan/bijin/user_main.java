package com.example.jiayuan.bijin;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.example.jiayuan.bijin.Fragment.BeautyRankFragment;
import com.example.jiayuan.bijin.Fragment.ModelRankFragment;
import com.example.jiayuan.bijin.Fragment.RankListFragment;
import com.example.jiayuan.bijin.Fragment.SampleFragment;
import com.example.jiayuan.bijin.Fragment.SettingFragment;
import com.example.jiayuan.bijin.Fragment.opinionFragment;

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
    BeautyRankFragment beautyRankFragment=null;
    opinionFragment opinionfragment=null;
   android.support.v4.app.Fragment myPageFragment=null;
    android.support.v4.app.FragmentManager fragmentManger;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);
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
        drawerLayout.openDrawer(navigationView);

        //left_menu.setAdapter(myAdapter);
        //left_menu.setOnItemClickListener(this);
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
                android.support.v4.app.FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction trans=fragmentManager.beginTransaction();
                switch (item.getItemId()){
                    case R.id.item1:
                        Transction(R.id.frg_root,myPageFragment,"com.example.jiayuan.bijin.Fragment.MyPageFragment",trans);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.item2:
                        Transction(R.id.frg_root,sampleFragment,"com.example.jiayuan.bijin.Fragment.SampleFragment",trans);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.item3:
                        Transction(R.id.frg_root,modelRankFragment,"com.example.jiayuan.bijin.Fragment.ModelRankFragment",trans);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.item4:
                        Transction(R.id.frg_root,opinionfragment,"com.example.jiayuan.bijin.Fragment.opinionFragment",trans);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.item5:
                        Transction(R.id.frg_root,beautyRankFragment,"com.example.jiayuan.bijin.Fragment.BeautyRankFragment",trans);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.item6:
                    case R.id.item7:
                        Transction(R.id.frg_root,rankListFragment,"com.example.jiayuan.bijin.Fragment.RankListFragment",trans);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.item8:
                        Transction(R.id.frg_root,settingFragment,"com.example.jiayuan.bijin.Fragment.SettingFragment",trans);
                        drawerLayout.closeDrawers();
                        break;

                }
                return true;
            }
        });
    }
   // public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.main_menu, menu);
       // setIconEnable(menu,true);
       // menu.add("jiayuan").setIcon(R.drawable.beauty);
       // return true;
    //}
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

    }



