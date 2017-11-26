package com.example.jiayuan.bijin.diy_view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jiayuan.bijin.R;

/**
 * Created by jiayuan on 2017/11/21.
 */

public class AgeRankingView extends LinearLayout {
    private TextView setting_title;
    private ImageView setting_image;
    public AgeRankingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    private void init(){
        View view=View.inflate(getContext(),R.layout.age_ranking,this);
        setting_image=(ImageView)view.findViewById(R.id.age_image);
    }
    public void setAgeImage(int id){
        setting_image.setImageResource(id);
    }
}
