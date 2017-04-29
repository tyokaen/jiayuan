package com.example.jiayuan.library;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import Tool.MakeClock;

public class book_info extends Activity {
MakeClock make=new MakeClock();
    Handler handler=new Handler();
    MyRunnable myRunnable=new MyRunnable();
    ImageView imageView;
    String style=null,time=null;
    int image=0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);
        ActionBar actionBar=getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ActionBar.Tab tab=actionBar.newTab();
        ActionBar.Tab tab1=actionBar.newTab();
        ActionBar.Tab tab2=actionBar.newTab();
        tab.setText("商品信息").setTabListener(new Inner<>(this,page.class));
        tab2.setText("提供者信息").setTabListener(new Inner<>(this,page2.class));
        actionBar.addTab(tab);
        actionBar.addTab(tab2);

        System.out.println(style);

    }
    class MyRunnable implements Runnable{
        int i=0;
        public void run() {
            handler.postDelayed(this,1000);
            if(i==60){

            }
            else if(i==3600){

            }
            imageView.setImageBitmap(make.doDrawClock(6*i));
            i++;
        }
    }
    class Inner<T extends Fragment> implements ActionBar.TabListener{
       private Context context;
       private Class<T> clazz;
       private Fragment fragment;
       Inner(Context context,Class<T> clazz) {
           this.context=context;
           this.clazz=clazz;
       }

        @Override
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
           if(fragment==null){
               image=getIntent().getIntExtra("Image",0);
               style=getIntent().getStringExtra("Kinds");
               time=getIntent().getStringExtra("Time");
               this.fragment=Fragment.instantiate(context,clazz.getName());
               Bundle bundle=new Bundle();
               this.fragment.setArguments(bundle);
               ft.add(android.R.id.content,fragment);
               bundle.putString("Kinds",style);
               bundle.putInt("Images",image);
               bundle.putString("Time",time);
               System.out.println("zba"+bundle.getString("Kinds"));
           }
            else{
               ft.attach(fragment);
           }
        }

        @Override
        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
             ft.detach(fragment);
        }

        @Override
        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_connect,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.connect:
             showAlert();
            break;

        }
        return super.onOptionsItemSelected(item);
    }
    public void showAlert(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("販売者と連絡");
        builder.setCancelable(false);
        handler.postDelayed(myRunnable,1000);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               handler.removeCallbacks(myRunnable);
            }
        });
        LayoutInflater layoutInflater=this.getLayoutInflater();
       View layout=(View) layoutInflater.inflate(R.layout.connect_to_pro,null);
        imageView=(ImageView) layout.findViewById(R.id.show_clock);
        imageView.setImageBitmap(make.doDrawClock(0));
        builder.setView(layout);
       AlertDialog dialog=builder.create();
        dialog.show();

    }
}

