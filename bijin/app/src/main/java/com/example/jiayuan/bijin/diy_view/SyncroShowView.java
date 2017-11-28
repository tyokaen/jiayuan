package com.example.jiayuan.bijin.diy_view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jiayuan.bijin.R;

/**
 * Created by jiayuan on 2017/11/27.
 */

public class SyncroShowView extends LinearLayout {

    TextView Tx_syncro_value,Tx_syncro_title;
    RoundProgressbar Pro_syncro_value;
    public SyncroShowView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public void init(){
        View view= View.inflate(getContext(), R.layout.syncro,this);
        Tx_syncro_value=(TextView) view.findViewById(R.id.Tx_syncro_value);
        Pro_syncro_value=(RoundProgressbar)view.findViewById(R.id.Pro_syncro_value);

    }
    public void setTitle(String str){
        Tx_syncro_title.setText(str);
    }

}
