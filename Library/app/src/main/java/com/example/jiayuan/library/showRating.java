package com.example.jiayuan.library;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.FeatureGroupInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiayuan on 2016/09/11.
 */
public class showRating extends Fragment {
    static String[] writer={"张三","张三","张三","张三","张三","张三","张三","张三","张三","张三"};
    String[] name = {"java核心技术", "head fist in java", "java语言程序设计", "java开发实战1200例", "java编程思想",
            "深入分析java web", "Effective java", "深入分析java虚拟机", "java并发实战", "java从入门到精通"
    };
    static int[] image = {R.drawable.a2, R.drawable.a3, R.drawable.a4, R.drawable.a5, R.drawable.a6,
            R.drawable.a7, R.drawable.a8, R.drawable.a9, R.drawable.a10, R.drawable.a11};
    static float[] rating={5,4,4,4,4,3,3,3,2,2,1};
    ListView L;
    List<String> list;
            List<String>list1;
    List<Integer>list2;
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View layout=inflater.inflate(R.layout.show_r_list,container,false);
        L=(ListView) layout.findViewById(R.id.ListView4);
        list=new ArrayList<String>();
        list1=new ArrayList<String>();
        list2=new ArrayList<Integer>();
        for(int i=0;i<name.length;i++){
            list.add(name[i]);
        }
        for(int i=0;i<name.length;i++){
            list1.add(writer[i]);
        }
        for(int i=0;i<name.length;i++){
            list2.add(image[i]);
        }
        MyAdapter myAdapter=new MyAdapter(getActivity());
        L.setAdapter(myAdapter);
        L.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(),showComment.class);
                intent.putExtra("name",name[position]);
                intent.putExtra("Rating",rating[position]);
               startActivity(intent);

            }
        });
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
            ViewHolder1 viewHolder=null;
            if(convertView==null) {
                viewHolder=new ViewHolder1();
                convertView=mInflater.inflate(R.layout.rating_list,null);
                viewHolder.t=(TextView) convertView.findViewById(R.id.TextView16);
                viewHolder.t1=(TextView) convertView.findViewById(R.id.TextView17);
                viewHolder.i=(ImageView) convertView.findViewById(R.id.ImageView5);
                viewHolder.r=(RatingBar) convertView.findViewById(R.id.Ratingbar1);
                convertView.setTag(viewHolder);
            }
            else{

                viewHolder=(ViewHolder1) convertView.getTag();
            }
            viewHolder.t.setText(list.get(position));
            viewHolder.t1.setText(""+list1.get(position));
            viewHolder.i.setImageResource(list2.get(position));
            viewHolder.r.setRating(rating[position]);

            return convertView;
        }
    }
}
