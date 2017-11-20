package com.example.jiayuan.bijin.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.jiayuan.bijin.R;

/**
 * Created by jiayuan on 2017/08/22.
 */

public class InputEndFragment extends Fragment implements View.OnClickListener {
    Button Btn_back_My=null;
    Button IBn_back_Sam=null;
    android.support.v4.app.FragmentManager fragmentManger=null;
    android.support.v4.app.FragmentTransaction trans=null;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.input_end,null);
        Btn_back_My=(Button)view.findViewById(R.id.back_to_mypage);
        IBn_back_Sam=(Button)view.findViewById(R.id.sample_oncemore);
        fragmentManger=getFragmentManager();
        trans=fragmentManger.beginTransaction();
        Btn_back_My.setOnClickListener(this);
        IBn_back_Sam.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_to_mypage:
                MyPageFragment Mypage=new MyPageFragment();
                trans.replace(R.id.frg_root, Mypage);
                trans.commit();
                break;
            case R.id.sample_oncemore:
                SampleFragment sampleFragment=new SampleFragment();
                trans.replace(R.id.frg_root, sampleFragment);
                trans.commit();
                break;
        }
    }
}
