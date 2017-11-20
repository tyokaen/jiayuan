package com.example.jiayuan.bijin.diy_view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jiayuan.bijin.R;

/**
 * Created by jiayuan on 2017/08/20.
 */

public class MyPageView extends LinearLayout {
TextView tx_title,tx_content;
ImageView image_title;

    public MyPageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        String title=attrs.getAttributeValue("http://schemas.android.com/apk/res-auto","pagetitle");
        String content=attrs.getAttributeValue("http://schemas.android.com/apk/res-auto","pagetext");
        init();
        tx_title.setText(title);
        tx_content.setText(content);

    }

    private void init(){
        View view=View.inflate(getContext(), R.layout.my_page_zuhe,this);
        tx_title=(TextView) view.findViewById(R.id.sample_number_title);
        tx_content=(TextView)view.findViewById(R.id.sample_number_text);
        image_title=(ImageView)view.findViewById(R.id.sample_number_image);

    }
    public void setImage(int id){
        image_title.setImageResource(id);
    }
}
