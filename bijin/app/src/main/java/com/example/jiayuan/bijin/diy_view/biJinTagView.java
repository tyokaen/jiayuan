package com.example.jiayuan.bijin.diy_view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jiayuan.bijin.R;

/**
 * Created by jiayuan on 2017/12/08.
 */

public class biJinTagView extends LinearLayout {
    TextView Tx_tag_name=null;
    public biJinTagView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }
    public void initView(){
        View view=View.inflate(getContext(), R.layout.bijin_tag,this);
        Tx_tag_name=(TextView) view.findViewById(R.id.Tx_tag_name);
    }
    public void setTitle(String title){
        Tx_tag_name.setText(title);
    }

}
