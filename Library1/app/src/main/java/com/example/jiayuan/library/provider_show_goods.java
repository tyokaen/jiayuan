package com.example.jiayuan.library;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

import Provider.Provider_Good_dao;
import Provider.Provider_Goods;
import Tool.Goods_File;

public class provider_show_goods extends Activity {
    ArrayList<Provider_Goods> provider_goodsArrayList = null;
    Provider_Good_dao provider_good_dao = new Provider_Good_dao(this);
    ListView listView;
    String name = null;
    File file2=new File(Environment.getExternalStorageDirectory(),"Furniture_info.csv");
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_show_goods);
        listView = (ListView) findViewById(R.id.Pro_show);
        name = getIntent().getStringExtra("pro_name");
        provider_goodsArrayList = provider_good_dao.findPro_Goods(name);
        if (provider_goodsArrayList == null) {
            provider_goodsArrayList = new ArrayList<Provider_Goods>();
            provider_goodsArrayList = Goods_File.getGoods_file().readObject2(file2);
            System.out.println(provider_goodsArrayList.size());
            for (int i = 0; i < provider_goodsArrayList.size(); i++) {
                provider_good_dao.addPro_Goods(provider_goodsArrayList.get(i));
            }
        }
        listView.setAdapter(new MyAdapter());

    }
    class MyAdapter extends BaseAdapter{


        @Override
        public int getCount() {
            return provider_goodsArrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder=null;
            if(viewHolder==null){
                viewHolder=new ViewHolder();
                convertView=provider_show_goods.this.getLayoutInflater().inflate(R.layout.provider_good_list,null);
                viewHolder.textView=(TextView) convertView.findViewById(R.id.Pro_Goods_Kinds);
                viewHolder.textView1=(TextView)convertView.findViewById(R.id.Pro_Goods_Price);
                viewHolder.imageView=(ImageView)convertView.findViewById(R.id.Pro_Goods_img);
                convertView.setTag(viewHolder);

            }
            else{
                viewHolder=(ViewHolder) convertView.getTag();
            }
            viewHolder.textView.setText(provider_goodsArrayList.get(position).get_P_Style0());
            viewHolder.textView1.setText(provider_goodsArrayList.get(position).get_P_Price());
            viewHolder.imageView.setImageResource(R.drawable.a1);

            return null;
        }
    }

}
