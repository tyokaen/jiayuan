package com.example.jiayuan.library;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class bookshow extends Activity {
ListView L1;
ImageView v1;
List<Map<String,Object>> list;
Map<String,Object> map;
SimpleAdapter adapter;
static int i;
    static int [] price={14,29,32,21,42,30,28,21,19,22};
    static String[] writer={"张三","张三","张三","张三","张三","张三","张三","张三","张三","张三"};
     static int[] image = {R.drawable.a2, R.drawable.a3, R.drawable.a4, R.drawable.a5, R.drawable.a6,
            R.drawable.a7, R.drawable.a8, R.drawable.a9, R.drawable.a10, R.drawable.a11};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookshow);
        L1 = (ListView) findViewById(R.id.ListView1);
        list = new ArrayList<Map<String, Object>>();
        String[] name = {"java核心技术", "head fist in java", "java语言程序设计", "java开发实战1200例", "java编程思想",
                "深入分析java web", "Effective java", "深入分析java虚拟机", "java并发实战", "java从入门到精通"
        };
        for (int i = 0; i < name.length; i++) {
            map = new HashMap<String, Object>();
            map.put("Image", image[i]);
            map.put("Title", name[i]);
            map.put("comment","非常好的一本java书");
            list.add(map);
        }
       String[] from={"Image","Title","comment"};
       int[] to={R.id.ImageView1,R.id.TextView12,R.id.TextView13};
        adapter=new SimpleAdapter(this,list,R.layout.simple_adapter_item,from,to);
        L1.setAdapter(adapter);
        L1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showInfo(position);
            }
        });
    }
         public void showInfo(final int position){
             ImageView img=new ImageView(bookshow.this);
             img.setImageResource(image[position]);
             new AlertDialog.Builder(this).setView(img)
                     .setTitle("详情" + position)
                     .setMessage("作者：" + writer[position] + "价格:" + price[position]+"元")
                     .setPositiveButton("借阅", new DialogInterface.OnClickListener() {

                         public void onClick(DialogInterface dialog, int which) {
                             Intent intent = new Intent(getApplicationContext(), borrowbook.class);
                             startActivity(intent);
                         }
                     })
                    .setNegativeButton("购物车", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(getApplicationContext(), buybook.class);
                            startActivity(intent);
                            i = position;
                        }
                    })
                     .show();
         }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bookshow, menu);
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
