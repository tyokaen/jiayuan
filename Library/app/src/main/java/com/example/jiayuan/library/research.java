package com.example.jiayuan.library;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class research extends Activity {
TextView tv1,tv2,tv3;
SharedPreferences sp1;
    String str;
  ArrayAdapter<String> adapter;
    ListView list;
    List<String> list1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_research);
        list=(ListView)findViewById(R.id.ListVIew3);
      //  tv1.setText(MainActivity.str2);
      //  tv2.setText(MainActivity.str3);
        list1=new ArrayList<String>();
       sp1=this.getSharedPreferences("demo-02",MODE_PRIVATE);
        for(int j=0;j<borrowbook.list1.size();j++){
              list1.add(sp1.getString(borrowbook.list1.get(j),"meizhaodao"));
        }
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list1);

        list.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_research, menu);
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
