package com.example.jiayuan.bijin.diy_view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jiayuan.bijin.R;

/**
 * Created by jiayuan on 2017/08/05.
 */

public class MysettingView extends RelativeLayout {
    private TextView setting_title;
    private ImageView setting_image;
    public MysettingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        String title=attrs.getAttributeValue("http://schemas.android.com/apk/res-auto","title");
        setting_title.setText(title);
    }
    private void init(){
        View view=View.inflate(getContext(), R.layout.setting_view,this);
        setting_image=(ImageView)view.findViewById(R.id.setting_image);
        setting_title=(TextView)view.findViewById(R.id.setting_title);

    }
}
