package com.example.jiayuan.bijin.Fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.jiayuan.bijin.R;

import java.io.ByteArrayOutputStream;

/**
 * Created by jiayuan on 2017/08/11.
 */

public class BeautyRanking1Fragment extends Fragment implements View.OnClickListener {
    ImageView imageView = null;
    Bundle bundle = null;
    Button button=null;
    Bitmap bitmap=null;
    byte[] imagebyte=null;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.image_agree, null);
        imageView = (ImageView) view.findViewById(R.id.image_agree);
        button=(Button)view.findViewById(R.id.image_challenge);
        button.setOnClickListener(this);
        bundle = getArguments();
        if (bundle != null) {
            byte[] bis = bundle.getByteArray("imagebyte");
           bitmap = BitmapFactory.decodeByteArray(bis, 0, bis.length);
            imageView.setImageBitmap(bitmap);

        }
        return view;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.image_challenge:
                imagebyte=Bitmap2Bytes(bitmap);
                ToBRFragment();

        }
    }
    public static byte[] Bitmap2Bytes(Bitmap bm){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }
    public void ToBRFragment(){
        FragmentManager fragmentManager = getFragmentManager();
        BeautyRanking2Fragment beautyRanking2Fragment=new BeautyRanking2Fragment();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Bundle bundle=new Bundle();
        bundle.putByteArray("imagebyte",imagebyte);
        beautyRanking2Fragment.setArguments(bundle);
        transaction.replace(R.id.frg_root,beautyRanking2Fragment);
        transaction.commit();
    }

}
