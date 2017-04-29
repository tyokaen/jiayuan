package com.example.jiayuan.library;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

import Provider.Electrical;
import Provider.ElectricalDao;
import Provider.Furniture;
import Provider.FurnitureDao;
import Provider.Provider_Good_dao;
import Provider.Provider_Goods;
import Tool.GetTime;
import Tool.Goods_File;

public class Info_Register extends Activity  {
ListView listView;
String[] goods_name={"图书","家电","家具"};
int[] good_image={R.drawable.book,R.drawable.electrical,R.drawable.furniture};
MyAdapter myAdapter;
ElectricalDao dao=new ElectricalDao(this);
FurnitureDao furnitureDao=new FurnitureDao(this);
Provider_Good_dao provider_good_dao=new Provider_Good_dao(this);
    ArrayList<Electrical> electricalArrayList=null;
    ArrayList<Furniture> furnitureArrayList=null;
    ArrayList<Provider_Goods> provider_goodsArrayList=null;
    File file=new File(Environment.getExternalStorageDirectory(),"electrical_info.csv");
    File file1=new File(Environment.getExternalStorageDirectory(),"Furniture_info.csv");
    File file2=new File(Environment.getExternalStorageDirectory(),"Provider_Good.csv");
    String Pro_name=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info__register);
        listView=(ListView) findViewById(R.id.goods_list);
        myAdapter=new MyAdapter();
        listView.setAdapter(myAdapter);
        Intent intent=getIntent();
        Pro_name=intent.getStringExtra("name");
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Input(position);
            }
        });
       Pro_name=getIntent().getStringExtra("name");

    }

    public void Input(final int i){
        LayoutInflater layoutInflater=(LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View layout=layoutInflater.inflate(R.layout.good_register,(ViewGroup)this.findViewById(R.id.LinearLayout_good_Register));
        final EditText editText=(EditText) layout.findViewById(R.id.good_Style);
        final EditText editText1=(EditText) layout.findViewById(R.id.good_Price);
        final EditText editText2=(EditText)layout.findViewById(R.id.good_Address);
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("输入信息");
        builder.setView(layout);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.setPositiveButton("提交", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String style=editText.getText().toString();
                String price=editText1.getText().toString();
                String address=editText2.getText().toString();
                String time= GetTime.getTimeInstance().getTime();
                System.out.println("打印"+which);
                if(i==1){
                    dao.addElectrical(new Electrical(style, price, address,time));
                    electricalArrayList = dao.findElectrical();
                    Goods_File.getGoods_file().saveToCard(electricalArrayList,file);
                    provider_good_dao.addPro_Goods(new Provider_Goods(Pro_name,style,price,address));
                    provider_goodsArrayList=provider_good_dao.findPro_Goods(Pro_name);
                    Goods_File.getGoods_file().saveToCard(provider_goodsArrayList,file2);
                }
                else if(i==2){
                  furnitureDao.addFurniture(new Furniture(style,price,address,time));
                   furnitureArrayList=furnitureDao.findFurniture();
                    Goods_File.getGoods_file().saveToCard(furnitureArrayList,file1);
                    provider_good_dao.addPro_Goods(new Provider_Goods(Pro_name,style,price,address));
                    provider_goodsArrayList=provider_good_dao.findPro_Goods(Pro_name);
                    System.out.println("sssdsdsdsds"+provider_goodsArrayList.get(0).get_P_ID());
                    Goods_File.getGoods_file().saveToCard(provider_goodsArrayList,file2);
                }
            }
        });
       AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }

   class  MyAdapter extends BaseAdapter{


       @Override
       public int getCount() {
           return goods_name.length;
       }

       @Override
       public Object getItem(int position) {
           return goods_name[position];
       }

       @Override
       public long getItemId(int position) {
           return 0;
       }

       @Override
       public View getView(int position, View convertView, ViewGroup parent) {
           ViewHolder2 viewHolder2=null;
           if(convertView==null){
               viewHolder2 = new ViewHolder2();
               convertView = Info_Register.this.getLayoutInflater().inflate(R.layout.list_goods, null);
               viewHolder2.t=(TextView) convertView.findViewById(R.id.Goods_name);
               viewHolder2.i=(ImageView)convertView.findViewById(R.id.Goods_image);
               convertView.setTag(viewHolder2);

           }
           else {
               viewHolder2=(ViewHolder2) convertView.getTag();
           }
           viewHolder2.i.setImageResource(good_image[position]);
           viewHolder2.t.setText(goods_name[position]);
           return convertView;
       }
   }

}
