package com.example.jiayuan.bijin.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jiayuan.bijin.Adapter.RecycleAdapter;
import com.example.jiayuan.bijin.R;
import com.example.jiayuan.bijin.Tools.SpaceItemDecoration;

/**
 * Created by jiayuan on 2017/08/18.
 */

public class opinionFragment extends Fragment {
    public RecyclerView recyclerView,recyclerView1;
    int[] image={R.drawable.a12,R.drawable.a12,R.drawable.a12,R.drawable.a12,R.drawable.a12
    ,R.drawable.a12,R.drawable.a12};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.public_opinion,null);
        recyclerView=(RecyclerView)view.findViewById(R.id.recycle);
       recyclerView1=(RecyclerView)view.findViewById(R.id.recycle1);
        RecycleAdapter recycleAdapter=new RecycleAdapter(image);
        RecyclerView.LayoutManager layoutManager=new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL);
        RecyclerView.LayoutManager layoutManager1=new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recycleAdapter);
        recyclerView.addItemDecoration(new SpaceItemDecoration(8));
        recyclerView1.setLayoutManager(layoutManager1);
        recyclerView1.addItemDecoration(new SpaceItemDecoration(8));
       recyclerView1.setAdapter(recycleAdapter);
        return view;
    }
}
