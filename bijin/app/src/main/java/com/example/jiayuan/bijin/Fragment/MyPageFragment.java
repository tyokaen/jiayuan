package com.example.jiayuan.bijin.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jiayuan.bijin.R;
import com.example.jiayuan.bijin.diy_view.MyPageView;

/**
 * Created by jiayuan on 2017/08/20.
 */

public class MyPageFragment extends Fragment {
    MyPageView view1,view2,view3;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.mypage_main,null);
        view1=(MyPageView)view.findViewById(R.id.sample_count);
        view1.setImage(R.drawable.sorting);
        view2=(MyPageView)view.findViewById(R.id.opinion_count);
        view2.setImage(R.drawable.synchro);
        view3=(MyPageView)view.findViewById(R.id.challenge_count);
        view3.setImage(R.drawable.mypageranking);
        return view;
    }
}
