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

public class MaleRankingView extends LinearLayout {
    private TextView male_title;
    private ImageView male_image;
    public MaleRankingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    private void init(){
        View view=View.inflate(getContext(), R.layout.male_rangking,this);
        male_image=(ImageView)view.findViewById(R.id.male_image);
        male_title=(TextView)view.findViewById(R.id.male_text);
    }
    public void setMaleImage(int id){
        male_image.setImageResource(id);
    }
    public void setMaleTitle(String string){
        male_title.setText(string);
        male_title.setTextColor(getResources().getColor(R.color.blue));
    }
}
