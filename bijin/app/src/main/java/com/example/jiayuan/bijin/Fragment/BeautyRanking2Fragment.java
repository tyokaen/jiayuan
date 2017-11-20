package com.example.jiayuan.bijin.Fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.jiayuan.bijin.R;

/**
 * Created by jiayuan on 2017/08/11.
 */

public class BeautyRanking2Fragment extends Fragment {
    private ImageView imageView=null;
    Bitmap bitmap=null;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.beauty_challenge_result,null);
        imageView=(ImageView)view.findViewById(R.id.show_challenge);
        Bundle bundle=getArguments();
        if (bundle != null) {
            byte[] bis = bundle.getByteArray("imagebyte");
            bitmap = BitmapFactory.decodeByteArray(bis, 0, bis.length);
            imageView.setImageBitmap(bitmap);
        }
        return view;
    }
}
