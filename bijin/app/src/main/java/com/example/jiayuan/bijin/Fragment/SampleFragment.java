package com.example.jiayuan.bijin.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.example.jiayuan.bijin.Adapter.ImageAdapter;
import com.example.jiayuan.bijin.R;

/**
 * Created by jiayuan on 2017/08/06.
 */

public class SampleFragment extends android.support.v4.app.Fragment implements View.OnClickListener,ViewSwitcher.ViewFactory{
        Integer[] images={R.drawable.bijin1,};
        ProgressBar progressBar=null;
        TextView Tx_progress=null,Tx_sam_count;
        ImageSwitcher imageSwitcher=null;
        ImageButton IBtn_next,IBtn_like;
        int index=0;
        int increment=0;
        int number_left=0;
    android.support.v4.app.FragmentManager fragmentManger=null;
    android.support.v4.app.FragmentTransaction trans=null;
public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.sample_input,container,false);
        ImageAdapter imageAdapter=new ImageAdapter(getActivity(),images);
        imageAdapter.createReflectedImages();
       imageSwitcher=(ImageSwitcher) root.findViewById(R.id.Gallery1);
        IBtn_next=(ImageButton)root.findViewById(R.id.sample_dislake);
        IBtn_like=(ImageButton)root.findViewById(R.id.sample_like);
        IBtn_like.setOnClickListener(this);
        IBtn_next.setOnClickListener(this);
        imageSwitcher.setFactory(this);
        imageSwitcher.setImageResource(images[0]);
        progressBar=(ProgressBar)root.findViewById(R.id.sample_progress);
        progressBar.setMax(100);
        Tx_progress=(TextView)root.findViewById(R.id.progress_recoder);
        Tx_sam_count=(TextView)root.findViewById(R.id.sample_count);
  fragmentManger=getFragmentManager();
   trans=fragmentManger.beginTransaction();
        return root;
        }
        @Override
        public void onClick(View v) {
           switch(v.getId()){
                   case R.id.sample_dislake:
                           if(index<images.length) {
                                   imageSwitcher.setImageResource(images[index]);
                                   index++;
                                   number_left=images.length-index;
                                   progressBar.setProgress(increment+25);
                                   increment=increment+25;
                                   Tx_progress.setText(""+increment+"%");
                                   Tx_sam_count.setText("あと"+number_left+"枚");
                           }
                           else{
                               InputEndFragment inputEnd=new InputEndFragment();
                               trans.replace(R.id.frg_root, inputEnd);
                               trans.commit();
                           }
                           break;
                   case R.id.sample_like:
                           if(index<images.length){
                                   imageSwitcher.setImageResource(images[index]);
                                   index++;
                               number_left=images.length-index;
                                   progressBar.setProgress(increment+25);
                                   increment=increment+25;
                                   Tx_progress.setText(""+increment+"%");
                                Tx_sam_count.setText("あと"+number_left+"枚");

                           }
                           else{
                               InputEndFragment inputEnd=new InputEndFragment();
                               trans.replace(R.id.frg_root, inputEnd);
                               trans.commit();
                           }
                           break;
           }
        }

        @Override
        public View makeView() {
                ImageView imageView=new ImageView(getActivity());
                        return imageView;
        }
}
