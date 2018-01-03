package com.example.jiayuan.bijin.Presenter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.example.jiayuan.bijin.Tools.ToolsBitmap;
import com.example.jiayuan.bijin.net.ImageRequest;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by jiayuan on 2018/01/03.
 */

public class ImageAction  {
    ImageRequest imageRequest=new ImageRequest();
    public void getImage(String imagetoken, final Activity activity, final ImageView imageView){
        imageRequest.setImageToken(imagetoken);
        imageRequest.enquene(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final byte[] b = response.body().bytes();
                final Bitmap bitmap= ToolsBitmap.getInstance().yuanjiao(ToolsBitmap.getInstance().ImageCrop(b,true),50);
                if(activity!=null){
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setImageBitmap(bitmap);
                            imageView.clearAnimation();
                        }
                    });
                }

            }
        });

    }
}
