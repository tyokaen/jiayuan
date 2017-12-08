package com.example.jiayuan.bijin.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.example.jiayuan.bijin.Adapter.ImageAdapter;
import com.example.jiayuan.bijin.Okhttp.OkhttpGet;
import com.example.jiayuan.bijin.R;
import com.example.jiayuan.bijin.Tools.StringToJson;
import com.example.jiayuan.bijin.cache.UserTokenCache;
import com.example.jiayuan.bijin.Activity.user_main;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

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
       byte[] b=new byte[10000];
       ArrayList<byte[]> bytes=new ArrayList<byte[]>();
       int count=0;
    //android.support.v4.app.FragmentManager fragmentManger=null;
    //android.support.v4.app.FragmentTransaction trans=null;
       String bijinToken=null,result=null;
    JSONArray jsonArray=null;
    String vote=null;
    int arrayIndex=0;
    OkHttpClient okHttpClient=new OkHttpClient();
    MyHandler myhandler=new MyHandler();
    MediaType type = MediaType.parse("application/x-www-form-urlencoded;charset=utf-8");
    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.arg1==1) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes.get(count), 0, bytes.get(count).length);
                imageSwitcher.setImageDrawable(new BitmapDrawable(bitmap));
            }
            else if(msg.arg1==2){
                count++;
                if(count<10) {
                    imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.left_in));
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes.get(count), 0, bytes.get(count).length);
                    if(bitmap==null)
                        imageSwitcher.setImageResource(R.drawable.defaultuserimage);
                    imageSwitcher.setImageDrawable(new BitmapDrawable(bitmap));
                }
                if(count==10){
                    Intent intent=new Intent(getActivity(), user_main.class);
                    startActivity(intent);
                }


            }

        }
    }
public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.sample_input,container,false);
        ImageAdapter imageAdapter=new ImageAdapter(getActivity(),images);
        imageAdapter.createReflectedImages();
        bijinToken=getArguments().getString("ImageToken");
        Log.i("zhangjiayuanyuan",bijinToken);
       imageSwitcher=(ImageSwitcher) root.findViewById(R.id.Gallery1);
        IBtn_next=(ImageButton)root.findViewById(R.id.sample_dislake);
        IBtn_like=(ImageButton)root.findViewById(R.id.sample_like);
        IBtn_like.setOnClickListener(this);
        IBtn_next.setOnClickListener(this);
        imageSwitcher.setFactory(this);
        progressBar=(ProgressBar)root.findViewById(R.id.sample_progress);
        progressBar.setMax(100);
        Tx_progress=(TextView)root.findViewById(R.id.progress_recoder);
        Tx_sam_count=(TextView)root.findViewById(R.id.sample_count);
        Tx_sam_count.setText("あと"+getArrayLength(bijinToken)+"枚");
        getImage();
        return root;
        }
        @Override
        public void onClick(View v) {
           switch(v.getId()){
                   case R.id.sample_dislake:
                       if (index < getArrayLength(bijinToken)) {
                           try {
                               send_Image(index,0);
                           } catch (JSONException e) {
                               e.printStackTrace();
                           }
                           index++;
                           number_left = getArrayLength(bijinToken) - index;
                           progressBar.setProgress(increment + 10);
                           increment = increment + 10;
                           Tx_progress.setText("" + increment + "%");
                           Tx_sam_count.setText("あと" + number_left + "枚");
                       }
                           else{

                           }
                           break;
                   case R.id.sample_like:
                       if (index < getArrayLength(bijinToken)) {
                           try {
                               send_Image(index,1);
                           } catch (JSONException e) {
                               e.printStackTrace();
                           }
                           index++;
                           number_left = getArrayLength(bijinToken) - index ;
                           progressBar.setProgress(increment + 10);
                           increment = increment + 10;
                           Tx_progress.setText("" + increment + "%");
                           Tx_sam_count.setText("あと" + number_left + "枚");
                       }
                       else{

                       }
                           break;
           }
        }

        @Override
        public View makeView() {
                ImageView imageView=new ImageView(getActivity());
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                        return imageView;
        }
    public int getArrayLength(String target) {
        try {
            jsonArray = StringToJson.ToJSon(target).getJSONArray("bijin_token_list");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonArray.length();
    }
    public String getImageToken(JSONArray array, int index) {
        String token = "";
        try {
            JSONObject jsonObject = array.getJSONObject(index);
            token = jsonObject.getString("token");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return token;
    }
    public void send_Image(final int index, int i) throws JSONException {
        if (i == 1)
            vote="upper";
        else vote = "lower";
       final  RequestBody requestBody = RequestBody.create(type,"user_token="+ UserTokenCache.getInstance().getUserToken(getActivity())+"&bijin_token="+jsonArray.getJSONObject(index).getString("token")+"&vote="+vote);
        new Thread(new Runnable() {
            @Override
            public void run() {
                result = OkhttpGet.UsePost(okHttpClient, "http://192.168.0.118/BijinTemp/index.php/api/vote","X-BijinScience",
                        "Bearer Mn6t5Dhfqz6hf4LtKToS19igKgeHDff0sCJNqQT6pzEvT0EEtT7L2FSnMWUzbaQuC9hSzbzF0eau4FYN859bl1pXxkxzknJNMRGmSgRtkSDF7C3gicht3wqQ7DqHRZ4EQkQJqIc1AGghs9n0CvKfIbWpEmW6l1kcCaLTJOut411NbFoDaYIJZFYERVldwvgZwSSfGnzl", requestBody);
                if(index<10) {
                    Message message = new Message();
                    message.arg1 = 2;
                    myhandler.sendMessage(message);
                }
            }
        }).start();

    }
    public byte[] getImage(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestBody requestBody = null;
                while (arrayIndex < 10) {
                    b = OkhttpGet.UseGetImage(okHttpClient, "http://192.168.0.118/BijinTemp/index.php/api/bijin/image?token=" + getImageToken(jsonArray, arrayIndex) + "&size=large", "X-BijinScience",
                            "Bearer Mn6t5Dhfqz6hf4LtKToS19igKgeHDff0sCJNqQT6pzEvT0EEtT7L2FSnMWUzbaQuC9hSzbzF0eau4FYN859bl1pXxkxzknJNMRGmSgRtkSDF7C3gicht3wqQ7DqHRZ4EQkQJqIc1AGghs9n0CvKfIbWpEmW6l1kcCaLTJOut411NbFoDaYIJZFYERVldwvgZwSSfGnzl", requestBody);
                    bytes.add(b);
                    arrayIndex++;
                }
                Message message = new Message();
                message.arg1 = 1;
                myhandler.sendMessage(message);
            }
        }).start();

        return b;
    }

}
