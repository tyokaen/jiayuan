package com.example.jiayuan.bijin.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.jiayuan.bijin.R;

/**
 * Created by jiayuan on 2017/08/09.
 */

public class GridviewAdapter extends BaseAdapter {
    int[] image=null;
    Context context;
    public GridviewAdapter(int[] image, Context context){
        this.image=image;
        this.context=context;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        MyHolder myHolder = null;
        if (convertView == null) {
            myHolder = new MyHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.like_list_image, null);
            myHolder.imageView = (ImageView) convertView.findViewById(R.id.like_image);
            convertView.setTag(myHolder);
        } else {
            myHolder = (MyHolder) convertView.getTag();

        }
        myHolder.imageView.setImageResource(image[position]);

        return convertView;
    }
    class MyHolder {

        ImageView imageView;

    }
}
