package com.example.jiayuan.library;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by jiayuan on 2017/03/07.
 */
public class page extends Fragment {
    ImageView Tx_Show_Image;
    TextView Tx_Show_Addr,Tx_Show_Kinds,Tx_Show_Price;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.tab1,null);
        TextView textView=(TextView) view.findViewById(R.id.Goods_name);
        ImageView imageView=(ImageView)view.findViewById(R.id.Goods_Image);
        TextView textView1=(TextView)view.findViewById(R.id.Time);
        Bundle bundle=getArguments();
        System.out.println(bundle.hashCode());
       if(!(bundle==null))
        textView.setText(bundle.getString("Kinds"));
        imageView.setImageResource(bundle.getInt("Images"));
        textView1.setText(bundle.getString("Time"));
        return view;
    }
}
