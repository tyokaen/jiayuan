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

public class WorldRankingView extends LinearLayout {
    private TextView world_title;
    private ImageView world_image;
    public WorldRankingView (Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    private void init(){
        View view=View.inflate(getContext(), R.layout.world_ranking,this);
        world_image=(ImageView)view.findViewById(R.id.world_ranking_image);
       world_title=(TextView)view.findViewById(R.id.world_ranking_text);
    }
    public void setWorldImage(int id){
       world_image.setImageResource(id);
    }
    public void setWorldTitle(String string){
        world_title.setText(string);
    }
}
