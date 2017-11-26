package com.example.jiayuan.bijin.Fragment;

import android.app.ProgressDialog;
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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jiayuan.bijin.Okhttp.OkhttpGet;
import com.example.jiayuan.bijin.R;
import com.example.jiayuan.bijin.Tools.StringToJson;
import com.example.jiayuan.bijin.diy_view.AgeRankingView;
import com.example.jiayuan.bijin.diy_view.MaleRankingView;
import com.example.jiayuan.bijin.diy_view.MyPageView;
import com.example.jiayuan.bijin.diy_view.MyScrollView;
import com.example.jiayuan.bijin.diy_view.WorldRankingView;

import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by jiayuan on 2017/08/20.
 */

public class MyPageFragment extends Fragment implements View.OnClickListener {
    byte[] b=new byte[10000];
    ArrayList<String> ImageData=new ArrayList<String>();
    int tokenCount=0,imageCount=0;
    View myPageView;
    MyPageView Myview1,Myview2,Myview3;
    AgeRankingView ten_view,twnety_view,thirty_view,forty_view,fifity_view;
    MaleRankingView man_view,woman_view;
    WorldRankingView world_man_view,world_woman_view;
    TextView textView=null,textView1,textView2,textView3,textView4;
    ImageView imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,
            imageView7,imageView8,imageView9,imageView10,imageView11,imageView12,imageView13,
            imageView14,imageView15,imageView16,imageView17,imageView18,imageView19,imageView20,
            imageView21,imageView22,imageView23,imageView24,imageView25,imageView26,imageView27,
    imageView28,imageView29,imageView30,imageView31;
    TextView Tx_user_name,Tx_user_level,Tx_user_birth,Tx_Total_Num,Tx_Sample,Tx_best3;
    OkHttpClient okHttpClient=new OkHttpClient();
    ArrayList<ImageView> imageViewArrayList=new ArrayList<ImageView>();
    StringBuffer stringBuffer=new StringBuffer();
    MyHandler myHandler=new MyHandler();
    String ImageToken=null;
    ArrayList<Future> futureArrayList=new ArrayList<Future>();
    ArrayList<String> ImageTokenArray=new ArrayList<String>();
    ExecutorService executorService= Executors.newCachedThreadPool();
    JSONArray jsonArray=new JSONArray();
    ArrayList<String> bijinToken=new ArrayList<String>();
    ArrayList<byte[]> bytes=new ArrayList<byte[]>();
    Bitmap bitmap=null;
    MyScrollView scrollview=null;
    ProgressDialog dialog=null;
   Animation anim;
    String userInfo;
    String usernumber;
    String text=null;
    @Override
    public void onClick(View v) {
        int index=imageViewArrayList.indexOf((ImageView)v);
        Intent intent=new Intent(getActivity(),bijin_detail.class);
        intent.putExtra("ImageToken",bijinToken.get(index));
        startActivity(intent);
    }
    class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.arg1==2){
                Tx_user_name.setText(StringToJson.JsonToString((String)msg.obj,"nickname"));
                Tx_user_birth.setText("生年月日" +StringToJson.JsonToString((String)msg.obj,"date_of_birth"));
                Tx_Sample.setText(StringToJson.JsonToString((String)msg.obj,"sorting")+"回");
                if(StringToJson.JsonToString((String)msg.obj,"is_best3_configured").equals("true"))
                Tx_best3.setText("ベスト3設定完了しました");
                else
                Tx_best3.setText("ベスト3を設定してください");
            }
            else if(msg.arg1==3){
                String total=StringToJson.JsonToString((String)msg.obj,"total");
                String totalMale=StringToJson.JsonToString((String)msg.obj,"man");
                String totalFemale=StringToJson.JsonToString((String)msg.obj,"woman");
                Tx_Total_Num.setText(StringToJson.JsonToString((String)msg.obj,"total")+"名");
                world_man_view.setWorldTitle(ChangToPer(total,totalMale)+"%");
                world_woman_view.setWorldTitle(String.valueOf(100-Integer.parseInt(ChangToPer(total,totalMale)))+"%");
                //dialog.cancel();
           }
        }
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        myPageView=inflater.inflate(R.layout.mypage_main,null);
        Tx_best3=(TextView) myPageView.findViewById(R.id.if_likelist);
        Myview1=(MyPageView)myPageView.findViewById(R.id.sample_count);
        Tx_Sample=(TextView) Myview1.findViewById(R.id.sample_number_text);
        Myview1.setImage(R.drawable.sorting);
        Myview2=(MyPageView)myPageView.findViewById(R.id.opinion_count);
        Myview2.setImage(R.drawable.synchro);
        Tx_user_name=(TextView)myPageView.findViewById(R.id.user_screen_name);
        Tx_user_birth=(TextView) myPageView.findViewById(R.id.user_birth);
        Tx_Total_Num=(TextView)myPageView.findViewById(R.id.user_total);
        scrollview=(MyScrollView) myPageView.findViewById(R.id.scrollView);
        textView1=(TextView)myPageView.findViewById(R.id.testResult2);
        anim= AnimationUtils.loadAnimation(getContext(), R.anim.loading_dialog);
       initAgeRanking();
        initMaleRanking();
        initWorldRanking();
        initImageView();
        setAni(imageViewArrayList);
        getRankingImageArray();
        getAllInfo();
        return myPageView;
    }
    public void initAgeRanking(){
        ten_view=(AgeRankingView)myPageView.findViewById(R.id.age_ten);
        ten_view.setAgeImage(R.drawable.rank10);
        twnety_view=(AgeRankingView)myPageView.findViewById(R.id.age_twenty);
        twnety_view.setAgeImage(R.drawable.rank20);
        thirty_view=(AgeRankingView)myPageView.findViewById(R.id.age_thirty);
        thirty_view.setAgeImage(R.drawable.rank30);
        forty_view=(AgeRankingView)myPageView.findViewById(R.id.age_forty);
        forty_view.setAgeImage(R.drawable.rank40);
        fifity_view=(AgeRankingView)myPageView.findViewById(R.id.age_fifty);
        fifity_view.setAgeImage(R.drawable.rank50);
    }
    public void initMaleRanking(){
        man_view=(MaleRankingView)myPageView.findViewById(R.id.man_ranking);
        man_view.setMaleTitle("男性から人気BIJIN");
        man_view.setMaleImage(R.drawable.manheader);
        woman_view=(MaleRankingView)myPageView.findViewById(R.id.woman_ranking);
        woman_view.setMaleImage(R.drawable.womanheader);
        woman_view.setMaleTitle("女性から人気BIJIN");
    }
    public void initWorldRanking(){
        world_man_view=(WorldRankingView) myPageView.findViewById(R.id.world_man);
        world_woman_view=(WorldRankingView)myPageView.findViewById(R.id.world_woman);
        world_man_view.setWorldImage(R.drawable.man);
        world_woman_view.setWorldImage(R.drawable.woman);
    }
    public void initImageView(){
        imageView1=(ImageView)ten_view.findViewById(R.id.age_rangking1);
        imageViewArrayList.add(imageView1);
        imageView1.setOnClickListener(this);
        imageView2=(ImageView)ten_view.findViewById(R.id.age_rangking2);
        imageViewArrayList.add(imageView2);
        imageView2.setOnClickListener(this);
        imageView3=(ImageView)ten_view.findViewById(R.id.age_rangking3);
        imageViewArrayList.add(imageView3);
        imageView3.setOnClickListener(this);
        imageView4=(ImageView)ten_view.findViewById(R.id.age_rangking4);
        imageViewArrayList.add(imageView4);
        imageView4.setOnClickListener(this);
        imageView5=(ImageView)ten_view.findViewById(R.id.age_rangking5);
        imageViewArrayList.add(imageView5);
        imageView5.setOnClickListener(this);
        imageView6=(ImageView)twnety_view.findViewById(R.id.age_rangking1);
        imageViewArrayList.add(imageView6);
        imageView6.setOnClickListener(this);
        imageView7=(ImageView)twnety_view.findViewById(R.id.age_rangking2);
        imageViewArrayList.add(imageView7);
        imageView7.setOnClickListener(this);
        imageView8=(ImageView)twnety_view.findViewById(R.id.age_rangking3);
        imageViewArrayList.add(imageView8);
        imageView8.setOnClickListener(this);
        imageView9=(ImageView)twnety_view.findViewById(R.id.age_rangking4);
        imageViewArrayList.add(imageView9);
        imageView9.setOnClickListener(this);
        imageView10=(ImageView)twnety_view.findViewById(R.id.age_rangking5);
        imageViewArrayList.add(imageView10);
        imageView10.setOnClickListener(this);
        imageView11=(ImageView)thirty_view.findViewById(R.id.age_rangking1);
        imageViewArrayList.add(imageView11);
        imageView11.setOnClickListener(this);
        imageView12=(ImageView)thirty_view.findViewById(R.id.age_rangking2);
        imageViewArrayList.add(imageView12);
        imageView12.setOnClickListener(this);
        imageView13=(ImageView)thirty_view.findViewById(R.id.age_rangking3);
        imageViewArrayList.add(imageView13);
        imageView13.setOnClickListener(this);
        imageView14=(ImageView)thirty_view.findViewById(R.id.age_rangking4);
        imageViewArrayList.add(imageView14);
        imageView14.setOnClickListener(this);
        imageView15=(ImageView)thirty_view.findViewById(R.id.age_rangking5);
        imageViewArrayList.add(imageView15);
        imageView15.setOnClickListener(this);
        imageView16=(ImageView)forty_view.findViewById(R.id.age_rangking1);
        imageViewArrayList.add(imageView16);
        imageView16.setOnClickListener(this);
        imageView17=(ImageView)forty_view.findViewById(R.id.age_rangking2);
        imageViewArrayList.add(imageView17);
        imageView17.setOnClickListener(this);
        imageView18=(ImageView)forty_view.findViewById(R.id.age_rangking3);
        imageViewArrayList.add(imageView18);
        imageView18.setOnClickListener(this);
        imageView19=(ImageView)forty_view.findViewById(R.id.age_rangking4);
        imageViewArrayList.add(imageView19);
        imageView19.setOnClickListener(this);
        imageView20=(ImageView)forty_view.findViewById(R.id.age_rangking5);
        imageViewArrayList.add(imageView20);
        imageView20.setOnClickListener(this);
        imageView21=(ImageView)fifity_view.findViewById(R.id.age_rangking1);
        imageViewArrayList.add(imageView21);
        imageView21.setOnClickListener(this);
        imageView22=(ImageView)fifity_view.findViewById(R.id.age_rangking2);
        imageViewArrayList.add(imageView22);
        imageView22.setOnClickListener(this);
        imageView23=(ImageView)fifity_view.findViewById(R.id.age_rangking3);
        imageViewArrayList.add(imageView23);
        imageView23.setOnClickListener(this);
        imageView24=(ImageView)fifity_view.findViewById(R.id.age_rangking4);
        imageViewArrayList.add(imageView24);
        imageView24.setOnClickListener(this);
        imageView25=(ImageView)fifity_view.findViewById(R.id.age_rangking5);
        imageViewArrayList.add(imageView25);
        imageView25.setOnClickListener(this);
        imageView26=(ImageView)man_view.findViewById(R.id.male_rangking1);
        imageViewArrayList.add(imageView26);
        imageView26.setOnClickListener(this);
        imageView27=(ImageView)man_view.findViewById(R.id.male_rangking2);
        imageViewArrayList.add(imageView27);
        imageView27.setOnClickListener(this);
        imageView28=(ImageView)man_view.findViewById(R.id.male_rangking3);
        imageViewArrayList.add(imageView28);
        imageView28.setOnClickListener(this);
        imageView29=(ImageView)woman_view.findViewById(R.id.male_rangking1);
        imageViewArrayList.add(imageView29);
        imageView29.setOnClickListener(this);
        imageView30=(ImageView)woman_view.findViewById(R.id.male_rangking2);
        imageViewArrayList.add(imageView30);
        imageView30.setOnClickListener(this);
        imageView31=(ImageView)woman_view.findViewById(R.id.male_rangking3);
        imageViewArrayList.add(imageView31);
        imageView31.setOnClickListener(this);
    }
    public void setAni(ArrayList<ImageView> list){
        for(int i=0;i<list.size();i++){
            list.get(i).setAnimation(anim);
        }
    }

    /*
     获取图片jsonArray
     */
    public void getRankingImageArray() {
        for (int i = 10; i <= 70; i = i + 10) {
            final RequestBody body = null;
            final int finalI = i;
            final Future future = executorService.submit(new Callable() {
                @Override
                public Object call() throws Exception {
                    if (finalI == 60) {
                        ImageToken = OkhttpGet.UseGetArray(okHttpClient, "http://192.168.0.118/BijinTemp/index.php/api/general/trend?gender=man&count=" + 3, "X-BijinScience",
                                "Bearer Mn6t5Dhfqz6hf4LtKToS19igKgeHDff0sCJNqQT6pzEvT0EEtT7L2FSnMWUzbaQuC9hSzbzF0eau4FYN859bl1pXxkxzknJNMRGmSgRtkSDF7C3gicht3wqQ7DqHRZ4EQkQJqIc1AGghs9n0CvKfIbWpEmW6l1kcCaLTJOut411NbFoDaYIJZFYERVldwvgZwSSfGnzl", body);
                    } else if (finalI == 70) {
                        ImageToken = OkhttpGet.UseGetArray(okHttpClient, "http://192.168.0.118/BijinTemp/index.php/api/general/trend?gender=woman&count=" + 3, "X-BijinScience",
                                "Bearer Mn6t5Dhfqz6hf4LtKToS19igKgeHDff0sCJNqQT6pzEvT0EEtT7L2FSnMWUzbaQuC9hSzbzF0eau4FYN859bl1pXxkxzknJNMRGmSgRtkSDF7C3gicht3wqQ7DqHRZ4EQkQJqIc1AGghs9n0CvKfIbWpEmW6l1kcCaLTJOut411NbFoDaYIJZFYERVldwvgZwSSfGnzl", body);
                    } else {
                        ImageToken = OkhttpGet.UseGetArray(okHttpClient, "http://192.168.0.118/BijinTemp/index.php/api/general/trend?generation=" + finalI + "&count=" + 5, "X-BijinScience",
                                "Bearer Mn6t5Dhfqz6hf4LtKToS19igKgeHDff0sCJNqQT6pzEvT0EEtT7L2FSnMWUzbaQuC9hSzbzF0eau4FYN859bl1pXxkxzknJNMRGmSgRtkSDF7C3gicht3wqQ7DqHRZ4EQkQJqIc1AGghs9n0CvKfIbWpEmW6l1kcCaLTJOut411NbFoDaYIJZFYERVldwvgZwSSfGnzl", body);
                    }
                    return ImageToken;
                }

            });
            futureArrayList.add(future);
        }
    }
    /*
    转换成百分比
     */
public String  ChangToPer(String Total,String maleNum){
    String resultStr=null;
    double resultDouble=Double.parseDouble(maleNum)/(Double.parseDouble(Total));
    resultStr=String.valueOf(resultDouble).substring(2,4);
    return resultStr;



}

   /*

  获取图片token
    */
    public void getToken(ArrayList<Future> list){
        for(int i=0;i<list.size();i++){
            try {
                jsonArray= StringToJson.getJSonArray(jsonArray,(String) list.get(i).get(),"popular_bijin");

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            StringToJson.getImageToken(bijinToken,jsonArray,"bijin_token");
        }

    }
    /*

    获取mypage中所有信息

     */

public void getAllInfo(){
    new Thread(new Runnable() {
        @Override
        public void run() {
            getUserInfo();
            getTotal();
            getImage();

        }
    }).start();

}
/*
获取图片
 */
    public void getImage(){

        getToken(futureArrayList);
        while (tokenCount < 31) {
            RequestBody requestBody = null;
            UseGetImage1(okHttpClient, "http://192.168.0.118/BijinTemp/index.php/api/bijin/image?token=" + bijinToken.get(tokenCount) + "&size=small", "X-BijinScience",
                    "Bearer Mn6t5Dhfqz6hf4LtKToS19igKgeHDff0sCJNqQT6pzEvT0EEtT7L2FSnMWUzbaQuC9hSzbzF0eau4FYN859bl1pXxkxzknJNMRGmSgRtkSDF7C3gicht3wqQ7DqHRZ4EQkQJqIc1AGghs9n0CvKfIbWpEmW6l1kcCaLTJOut411NbFoDaYIJZFYERVldwvgZwSSfGnzl", requestBody,imageViewArrayList.get(tokenCount));
            tokenCount++;
        }
    }
/*
获取用户信息
 */

    public void getUserInfo(){
        RequestBody requestBody = null;
       OkhttpGet.UseGetString(okHttpClient, "http://192.168.0.118/BijinTemp/index.php/api/user?token=4cc2dd5dd4d3e24738606d97aac890b0", "X-BijinScience",
                "Bearer Mn6t5Dhfqz6hf4LtKToS19igKgeHDff0sCJNqQT6pzEvT0EEtT7L2FSnMWUzbaQuC9hSzbzF0eau4FYN859bl1pXxkxzknJNMRGmSgRtkSDF7C3gicht3wqQ7DqHRZ4EQkQJqIc1AGghs9n0CvKfIbWpEmW6l1kcCaLTJOut411NbFoDaYIJZFYERVldwvgZwSSfGnzl", requestBody,myHandler,2);
    }

    /*
    获取用户数量
     */
    public void getTotal(){
        RequestBody requestBody = null;
       OkhttpGet.UseGetString(okHttpClient, "http://192.168.0.118/BijinTemp/index.php/api/general/subject", "X-BijinScience", "Bearer Mn6t5Dhfqz6hf4LtKToS19igKgeHDff0sCJNqQT6pzEvT0EEtT7L2FSnMWUzbaQuC9hSzbzF0eau4FYN859bl1pXxkxzknJNMRGmSgRtkSDF7C3gicht3wqQ7DqHRZ4EQkQJqIc1AGghs9n0CvKfIbWpEmW6l1kcCaLTJOut411NbFoDaYIJZFYERVldwvgZwSSfGnzl", requestBody,myHandler,3);
    }
    public void createDialog(){
        dialog= ProgressDialog.show(getActivity(),"提示","正在获取");
    }
    /*
    异步加载图片
     */
    public void  UseGetImage1(OkHttpClient okHttpClient, String url, String headerKey, String headerVal, RequestBody body, final ImageView imageView){
        Request request = new Request.Builder().url(url)
                .header(headerKey,headerVal)
                .method("GET", body)
                .build();
        Call call=okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            public void onFailure(Call call, IOException e) {
            }
            public void onResponse(Call call, Response response) throws IOException {
               final byte[] b =response.body().bytes();
                final Bitmap bitmap= BitmapFactory.decodeByteArray(b, 0, b.length);
                MyPageFragment.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        imageView.setImageBitmap(bitmap);
                        imageView.clearAnimation();
                    }
                });
            }
        });
    }
}
