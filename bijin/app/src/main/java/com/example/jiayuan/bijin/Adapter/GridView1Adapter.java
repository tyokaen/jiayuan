package com.example.jiayuan.bijin.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.example.jiayuan.bijin.R;

/**
 * Created by jiayuan on 2017/08/09.
 */

public class GridView1Adapter extends BaseAdapter
    {

        int[] image=null;
        Context context;
        ImageView imageview=null;
    public GridView1Adapter(int[] image, Context context, ImageView imageview){
            this.image=image;
            this.context=context;
            this.imageview=imageview;
        }
    public int getCount() {
        return image.length;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        MyHolder myHolder = null;
        if (convertView == null) {
            myHolder = new MyHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.choose_like_list, null);
            myHolder.imageView = (ImageView) convertView.findViewById(R.id.Imageview_like);
            myHolder.c=(CheckBox)convertView.findViewById(R.id.checkbox1);
           myHolder.c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
               @Override
               public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                   if(isChecked==true)
                       imageview.setImageResource(image[position]);
               }
           });
            convertView.setTag(myHolder);
        } else {
            myHolder = (MyHolder) convertView.getTag();

        }
        myHolder.imageView.setImageResource(image[position]);

        return convertView;
    }
        class MyHolder {

        ImageView imageView;
        CheckBox c;

    }
}
