package com.example.jiayuan.bijin.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;

import com.example.jiayuan.bijin.Adapter.GridView1Adapter;
import com.example.jiayuan.bijin.Adapter.GridviewAdapter;
import com.example.jiayuan.bijin.Adapter.RecycleAdapter;
import com.example.jiayuan.bijin.R;
import com.example.jiayuan.bijin.diy_view.MyGridView;

/**
 * Created by jiayuan on 2017/08/09.
 */

public class RankListFragment extends android.support.v4.app.Fragment implements View.OnClickListener {
   MyGridView Grid=null;
   Button Btn_choice1,Btn_choice2,Btn_choice3;
   Dialog dialog=null;
    private RecyclerView.LayoutManager layoutManager;
    MyGridView gridView=null;
    GridView1Adapter gridView1Adapter=null;
    ImageView Img_choice1,Img_choice2,Img_choice3;
    int image[]={R.drawable.a12,R.drawable.a12,R.drawable.a12,R.drawable.a12,R.drawable.a12,
            R.drawable.a12,R.drawable.a12,R.drawable.a12,R.drawable.a12,R.drawable.a12,
            R.drawable.a12,R.drawable.a12,
    };
   RecycleAdapter recycleAdapter=null;
    GridviewAdapter gridviewAdapter=null;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.like_list,null);
        Grid=(MyGridView) view.findViewById(R.id.Grid1);
        Btn_choice1=(Button)view.findViewById(R.id.btn_best1);
        Btn_choice2=(Button)view.findViewById(R.id.btn_best2);
        Btn_choice3=(Button)view.findViewById(R.id.btn_best3);
        Img_choice1=(ImageView)view.findViewById(R.id.image_best1);
        Img_choice2=(ImageView)view.findViewById(R.id.image_best2);
        Img_choice3=(ImageView)view.findViewById(R.id.image_best3);
        Btn_choice1.setOnClickListener(this);
        Btn_choice2.setOnClickListener(this);
        Btn_choice3.setOnClickListener(this);
        gridviewAdapter=new GridviewAdapter(image,getActivity());
        Grid.setAdapter(gridviewAdapter);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_best1:
                MakeChoiceDialog(Img_choice1);
                break;
            case R.id.btn_best2:
                MakeChoiceDialog(Img_choice2);
                break;
            case R.id.btn_best3:
                MakeChoiceDialog(Img_choice3);
                break;
        }
    }
    public void MakeChoiceDialog(ImageView imageView){
            dialog=new Dialog(getActivity(),R.style.ActionSheetDialogStyle);
            View view=LayoutInflater.from(getContext()).inflate(R.layout.choose_like_dialog,null);
            gridView=(MyGridView) view.findViewById(R.id.Grid2);
            gridView1Adapter=new GridView1Adapter(image,getActivity(),imageView);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                }
            });
            gridView.setAdapter(gridView1Adapter);
            dialog.setContentView(view);
            Window window=dialog.getWindow();
            window.setGravity(Gravity.BOTTOM);
            WindowManager windowManager=getActivity().getWindowManager();
            WindowManager.LayoutParams layoutParams=window.getAttributes();
            Display d=windowManager.getDefaultDisplay();
            layoutParams.y=20;
            layoutParams.height=(int)(d.getHeight()*0.7);
            layoutParams.width=(int)(d.getWidth()*1.0);
            window.setAttributes(layoutParams);
            dialog.show();
        }



}
