package com.example.jiayuan.bijin.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jiayuan.bijin.Activity.Pre_recommend;
import com.example.jiayuan.bijin.Okhttp.OkhttpGet;
import com.example.jiayuan.bijin.R;
import com.example.jiayuan.bijin.Tools.StringToJson;
import com.example.jiayuan.bijin.diy_view.CircleImageView;
import com.example.jiayuan.bijin.diy_view.biJinTagView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by jiayuan on 2017/12/0.
 */

public class TagChoiceFragment extends Fragment implements View.OnClickListener{
    biJinTagView tagView,tagView1,tagView2,tagView3,tagView4,tagView5,tagView6;
    TextView Tx_tag_name1,Tx_tag_name2,Tx_tag_name3,Tx_tag_name4,Tx_tag_name5,Tx_tag_name6;
    CircleImageView Img_tag1,Img_tag2,Img_tag3,Img_tag4,Img_tag5,Img_tag6;
    TagHandler myhandler=new TagHandler();
    OkHttpClient okHttpClient=new OkHttpClient();
    JSONArray jsonArray=new JSONArray();

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Tag1:
                lanuch(4);
                break;
            case R.id.Tag2:
                lanuch(6);
                break;
            case R.id.Tag3:
               lanuch(1);
                break;
            case R.id.Tag4:
                lanuch(3);
                break;
            case R.id.Tag5:
                lanuch(5);
                break;
            case R.id.Tag6:
                lanuch(2);
                break;
        }
    }
    class TagHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.arg1==2){
                jsonArray=StringToJson.getJSonArray(jsonArray,(String)msg.obj,"taglist");
                RequestBody requestBody=null;
                try {
                    Tx_tag_name1.setText(jsonArray.getJSONObject(0).getString("tagname"));
                   getTagImage(okHttpClient, "http://192.168.0.103/BijinScience-Web/index.php/api/tag/image?tagid=4","X-BijinScience","Bearer Mn6t5Dhfqz6hf4LtKToS19igKgeHDff0sCJNqQT6pzEvT0EEtT7L2FSnMWUzbaQuC9hSzbzF0eau4FYN859bl1pXxkxzknJNMRGmSgRtkSDF7C3gicht3wqQ7DqHRZ4EQkQJqIc1AGghs9n0CvKfIbWpEmW6l1kcCaLTJOut411NbFoDaYIJZFYERVldwvgZwSSfGnzl", requestBody,Img_tag1);
                    Tx_tag_name2.setText(jsonArray.getJSONObject(1).getString("tagname"));
                    getTagImage(okHttpClient, "http://192.168.0.103/BijinScience-Web/index.php/api/tag/image?tagid=6","X-BijinScience","Bearer Mn6t5Dhfqz6hf4LtKToS19igKgeHDff0sCJNqQT6pzEvT0EEtT7L2FSnMWUzbaQuC9hSzbzF0eau4FYN859bl1pXxkxzknJNMRGmSgRtkSDF7C3gicht3wqQ7DqHRZ4EQkQJqIc1AGghs9n0CvKfIbWpEmW6l1kcCaLTJOut411NbFoDaYIJZFYERVldwvgZwSSfGnzl", requestBody,Img_tag2);
                    Tx_tag_name3.setText(jsonArray.getJSONObject(2).getString("tagname"));
                    getTagImage(okHttpClient, "http://192.168.0.103/BijinScience-Web/index.php/api/tag/image?tagid=1","X-BijinScience","Bearer Mn6t5Dhfqz6hf4LtKToS19igKgeHDff0sCJNqQT6pzEvT0EEtT7L2FSnMWUzbaQuC9hSzbzF0eau4FYN859bl1pXxkxzknJNMRGmSgRtkSDF7C3gicht3wqQ7DqHRZ4EQkQJqIc1AGghs9n0CvKfIbWpEmW6l1kcCaLTJOut411NbFoDaYIJZFYERVldwvgZwSSfGnzl", requestBody,Img_tag3);
                    Tx_tag_name4.setText(jsonArray.getJSONObject(3).getString("tagname"));
                    getTagImage(okHttpClient, "http://192.168.0.103/BijinScience-Web/index.php/api/tag/image?tagid=3","X-BijinScience","Bearer Mn6t5Dhfqz6hf4LtKToS19igKgeHDff0sCJNqQT6pzEvT0EEtT7L2FSnMWUzbaQuC9hSzbzF0eau4FYN859bl1pXxkxzknJNMRGmSgRtkSDF7C3gicht3wqQ7DqHRZ4EQkQJqIc1AGghs9n0CvKfIbWpEmW6l1kcCaLTJOut411NbFoDaYIJZFYERVldwvgZwSSfGnzl", requestBody,Img_tag4);
                    Tx_tag_name5.setText(jsonArray.getJSONObject(4).getString("tagname"));
                    getTagImage(okHttpClient, "http://192.168.0.103/BijinScience-Web/index.php/api/tag/image?tagid=5","X-BijinScience","Bearer Mn6t5Dhfqz6hf4LtKToS19igKgeHDff0sCJNqQT6pzEvT0EEtT7L2FSnMWUzbaQuC9hSzbzF0eau4FYN859bl1pXxkxzknJNMRGmSgRtkSDF7C3gicht3wqQ7DqHRZ4EQkQJqIc1AGghs9n0CvKfIbWpEmW6l1kcCaLTJOut411NbFoDaYIJZFYERVldwvgZwSSfGnzl", requestBody,Img_tag5);
                    Tx_tag_name6.setText(jsonArray.getJSONObject(5).getString("tagname"));
                    getTagImage(okHttpClient, "http://192.168.0.103/BijinScience-Web/index.php/api/tag/image?tagid=2","X-BijinScience","Bearer Mn6t5Dhfqz6hf4LtKToS19igKgeHDff0sCJNqQT6pzEvT0EEtT7L2FSnMWUzbaQuC9hSzbzF0eau4FYN859bl1pXxkxzknJNMRGmSgRtkSDF7C3gicht3wqQ7DqHRZ4EQkQJqIc1AGghs9n0CvKfIbWpEmW6l1kcCaLTJOut411NbFoDaYIJZFYERVldwvgZwSSfGnzl", requestBody,Img_tag6);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


        }
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View root=inflater.inflate(R.layout.activity_tag_choice,null);
        initview(root);
        getTagName();
        return root;
    }
    public void lanuch(int id){
        Intent intent=new Intent(this.getActivity(), Pre_recommend.class);
        intent.putExtra("TagId",id);
        startActivity(intent);

    }
    public void initview(View view){
        tagView1=(biJinTagView)view.findViewById(R.id.Tag1);
        tagView1.setOnClickListener(this);
        tagView2=(biJinTagView)view.findViewById(R.id.Tag2);
        tagView2.setOnClickListener(this);
        tagView3=(biJinTagView)view.findViewById(R.id.Tag3);
        tagView3.setOnClickListener(this);
        tagView4=(biJinTagView)view.findViewById(R.id.Tag4);
        tagView4.setOnClickListener(this);
        tagView5=(biJinTagView)view.findViewById(R.id.Tag5);
        tagView5.setOnClickListener(this);
        tagView6=(biJinTagView)view.findViewById(R.id.Tag6);
        tagView6.setOnClickListener(this);
        Tx_tag_name1=(TextView)tagView1.findViewById(R.id.Tx_tag_name);
        Tx_tag_name2=(TextView)tagView2.findViewById(R.id.Tx_tag_name);
        Tx_tag_name3=(TextView)tagView3.findViewById(R.id.Tx_tag_name);
        Tx_tag_name4=(TextView)tagView4.findViewById(R.id.Tx_tag_name);
        Tx_tag_name5=(TextView)tagView5.findViewById(R.id.Tx_tag_name);
        Tx_tag_name6=(TextView)tagView6.findViewById(R.id.Tx_tag_name);
        Img_tag1=(CircleImageView)tagView1.findViewById(R.id.Img_tag);
        Img_tag2=(CircleImageView)tagView2.findViewById(R.id.Img_tag);
        Img_tag3=(CircleImageView)tagView3.findViewById(R.id.Img_tag);
        Img_tag4=(CircleImageView)tagView4.findViewById(R.id.Img_tag);
        Img_tag5=(CircleImageView)tagView5.findViewById(R.id.Img_tag);
        Img_tag6=(CircleImageView)tagView6.findViewById(R.id.Img_tag);

    }
    public void getTagName(){
        new Thread(new Runnable() {
           final RequestBody requestBody=null;
            @Override
            public void run() {
                OkhttpGet.UseGetString(okHttpClient, "http://192.168.0.103/BijinScience-Web/index.php/api/tag/info","X-BijinScience","Bearer Mn6t5Dhfqz6hf4LtKToS19igKgeHDff0sCJNqQT6pzEvT0EEtT7L2FSnMWUzbaQuC9hSzbzF0eau4FYN859bl1pXxkxzknJNMRGmSgRtkSDF7C3gicht3wqQ7DqHRZ4EQkQJqIc1AGghs9n0CvKfIbWpEmW6l1kcCaLTJOut411NbFoDaYIJZFYERVldwvgZwSSfGnzl", requestBody,myhandler,2);
            }
        }).start();
    }
    public void getTagImage(final OkHttpClient okHttpClient, String url, String headerKey, String headerVal, RequestBody body, final ImageView imageView) {
       final  Request request = new Request.Builder().url(url)
                .header(headerKey, headerVal)
                .method("GET", body)
                .build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    public void onFailure(Call call, IOException e) {
                    }
                    public void onResponse(final Call call, Response response) throws IOException {
                        final byte[] b = response.body().bytes();
                        final Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
                        if (TagChoiceFragment.this.getActivity() != null) {
                            TagChoiceFragment.this.getActivity().runOnUiThread(new Runnable() {
                                public void run() {
                                    imageView.setImageBitmap(bitmap);
                                }
                            });
                        }
                    }
                });
            }
        }).start();
    }

}
