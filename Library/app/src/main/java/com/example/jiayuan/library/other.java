package com.example.jiayuan.library;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class other extends Activity {
    private DrawerLayout drawerLayout;
    private RelativeLayout leftLayout;
    ArrayList<String> list;
    MyAdapter myAdapter;
    showRating showrating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        leftLayout = (RelativeLayout) findViewById(R.id.left);
        ListView listView = (ListView) leftLayout.findViewById(R.id.left_listview);
        list = new ArrayList<String>();
        list.add("最流行");
        list.add("新上市");
        myAdapter=new MyAdapter(this);
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showrating=new showRating();
                FragmentManager manager=getFragmentManager();
                FragmentTransaction transaction=manager.beginTransaction();
                transaction.replace(R.id.LinearLayout9,showrating);
                transaction.commit();
            }
        });
    }

    class MyAdapter extends BaseAdapter {
        private LayoutInflater mInflater;

        public MyAdapter(Context context) {
            this.mInflater = LayoutInflater.from(context);
        }

        public int getCount() {
            return list.size();
        }

        public String getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder2 viewHolder2=null;
            if(convertView==null) {
                viewHolder2=new ViewHolder2();
                convertView=mInflater.inflate(R.layout.drawlayout_adapter,null);
                viewHolder2.t=(TextView)convertView.findViewById(R.id.Tx_draw);
                viewHolder2.i=(ImageView)convertView.findViewById(R.id.Tx_Image);
                convertView.setTag(viewHolder2);

            }
            else{
                viewHolder2=(ViewHolder2) convertView.getTag();
            }
            viewHolder2.t.setText(list.get(position));
            viewHolder2.i.setImageResource(R.drawable.zhang);
            return convertView;
        }

    }
}

