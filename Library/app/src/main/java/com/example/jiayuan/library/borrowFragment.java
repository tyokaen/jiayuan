package com.example.jiayuan.library;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiayuan on 2016/09/11.
 */
public class borrowFragment extends Fragment {
    ListView L2;
    List<String> list;
    SharedPreferences sp1;
    static int i=0;
    String[] name = {"java核心技术", "head fist in java", "java语言程序设计", "java开发实战1200例", "java编程思想",
            "深入分析java web", "Effective java", "深入分析java虚拟机", "java并发实战", "java从入门到精通"
    };
    static List<String> list1;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View layout=inflater.inflate(R.layout.activity_borrowbook,container,false);
        L2=(ListView)layout.findViewById(R.id.ListView2);
        list=new ArrayList<String>();
        list1=new ArrayList<String>();
        for(int i=0;i<name.length;i++){
            list.add(name[i]);
        }
        MyAdapter myAdapter=new MyAdapter(getActivity());

        L2.setAdapter(myAdapter);
       return layout;
    }
    class MyAdapter extends BaseAdapter {
        private LayoutInflater mInflater;
        public MyAdapter(Context context){
            this.mInflater = LayoutInflater.from(context);
        }
        public int getCount(){
            return list.size();
        }
        public String getItem(int position){
            return null;
        }
        public long getItemId(int position){
            return 0;
        }
        public View getView( int position, View convertView, ViewGroup parent){
            ViewHolder viewHolder=null;
            if(convertView==null) {
                viewHolder=new ViewHolder();
                convertView=mInflater.inflate(R.layout.myadapter_list_item,null);
                viewHolder.textView=(TextView) convertView.findViewById(R.id.TextView14);
                viewHolder.button=(Button)convertView.findViewById(R.id.Button_borrow);
                convertView.setTag(viewHolder);
            }
            else{

                viewHolder=(ViewHolder) convertView.getTag();
            }
            viewHolder.textView.setText(list.get(position));
            viewHolder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                    builder.setTitle("提示信息");
                    builder.setMessage("确认要借");
                    AlertDialog alertDialog=builder.create();
                    alertDialog.show();
                }
            });
            return convertView;
        }
    }
}