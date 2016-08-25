package com.example.jiayuan.library;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
public class borrowbook extends Activity {
  ListView L2;
    List<String> list;
   SharedPreferences sp1;
     static int i=0;
    String[] name = {"java核心技术", "head fist in java", "java语言程序设计", "java开发实战1200例", "java编程思想",
            "深入分析java web", "Effective java", "深入分析java虚拟机", "java并发实战", "java从入门到精通"
    };
     static List<String> list1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        sp1=this.getSharedPreferences("demo-02",MODE_PRIVATE);
        setContentView(R.layout.activity_borrowbook);
        L2=(ListView)findViewById(R.id.ListView2);
        list=new ArrayList<String>();
        list1=new ArrayList<String>();
        for(int i=0;i<name.length;i++){
            list.add(name[i]);
        }
        MyAdapter myAdapter=new MyAdapter();
        L2.setAdapter(myAdapter);
        L2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    showInfo(position);
            }
        });
}
    public void showInfo(final int position){
        AlertDialog.Builder builder=new AlertDialog.Builder(borrowbook.this);
        builder.setTitle("确认借阅信息");
        builder.setMessage("您所借阅的书是:"+name[position]);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
         SharedPreferences.Editor editor1=sp1.edit();
         list1.add("jia"+i);
            String str=list1.get(i);
              editor1.putString(str,name[position]);
              i++;
              editor1.commit();
        }
    }
        );
        AlertDialog dialog=builder.create();
        dialog.show();
    }
 class MyAdapter extends BaseAdapter{
     public int getCount(){
         return list.size();
     }
     public String getItem(int position){
         return list.get(position);
     }
     public long getItemId(int position){
         return position;
     }
     public View getView(int position,View ConvertView,ViewGroup parent){
             LayoutInflater inflater = LayoutInflater.from(borrowbook.this);
             View view = inflater.inflate(R.layout.myadapter_list_item, null);
           TextView textView  = (TextView) view.findViewById(R.id.TextView14);
           textView.setText(getItem(position));
         return view;
     }
 }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_borrowbook, menu);
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
