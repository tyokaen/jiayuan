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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.jiayuan.bijin.Okhttp.OkhttpGet;
import com.example.jiayuan.bijin.R;
import com.example.jiayuan.bijin.Tools.StringToJson;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by jiayuan on 2017/08/07.
 */

public class ModelRankFragment extends Fragment implements AdapterView.OnItemSelectedListener,View.OnClickListener {
    Spinner spin_Age,spin_Sex,spin_City;
    Button btn_send;
    String[] sex,age,city;
    TextView textView=null;
    ArrayList<ImageView> imageViewArrayList=new ArrayList<ImageView>();
    ImageView Img_one,Img_two,Img_three,Img_four,Img_five,Img_six,Img_seven,Img_eight,Img_nine;
    String sexStr=null,ageString=null,cityString=null;
    int ClickCount=1;
    int intage=0;
    int TokenIndex=0;
    JSONArray jsonArray=new JSONArray();
    OkHttpClient okHttpClient=new OkHttpClient();
    MyHandler myHandler=new MyHandler();

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Btn_send_Req:
               sendRankReq();
                break;
            case R.id.Img_rank_one:
                TokenIndex=imageViewArrayList.indexOf((ImageView)v);
                launch(TokenIndex);
                break;
            case R.id.Img_rank_two:
                TokenIndex=imageViewArrayList.indexOf((ImageView)v);
                launch(TokenIndex);
                break;
            case R.id.Img_rank_three:
                TokenIndex=imageViewArrayList.indexOf((ImageView)v);
                launch(TokenIndex);
                break;
            case R.id.Img_rank_four:
                TokenIndex=imageViewArrayList.indexOf((ImageView)v);
                launch(TokenIndex);
                break;
            case R.id.Img_rank_five:
                TokenIndex=imageViewArrayList.indexOf((ImageView)v);
                launch(TokenIndex);
                break;
            case R.id.Img_rank_six:
                TokenIndex=imageViewArrayList.indexOf((ImageView)v);
                launch(TokenIndex);
                break;
            case R.id.Img_rank_seven:
                TokenIndex=imageViewArrayList.indexOf((ImageView)v);
                launch(TokenIndex);
                break;
            case R.id.Img_rank_eight:
                TokenIndex=imageViewArrayList.indexOf((ImageView)v);
                launch(TokenIndex);
                break;
            case R.id.Img_rank_nine:
                TokenIndex=imageViewArrayList.indexOf((ImageView)v);
                launch(TokenIndex);
                break;
        }

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.model_ranking, null);
        initView(view);
        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.Spin_sex:
                sexStr = sex[position];
                if (sexStr.equals("男性")) {
                    sexStr = "man";
                } else sexStr = "woman";
                break;
            case R.id.Spin_age:
                ageString = age[position];
                intage = Integer.valueOf(ageString);
                break;
            case R.id.Spin_address:
                cityString = city[position];
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.arg1 == 3) {
                jsonArray = StringToJson.getJSonArray(jsonArray, (String) msg.obj, "popular_bijin");
                if(jsonArray.length()==0){

                }
                else {
                    final RequestBody requestBody = null;
                    new Thread(new Runnable() {
                        int i = 0;
                        public void run() {
                            while (i < 9) {
                                try {
                                    getRangkingImage(okHttpClient, "http://192.168.0.103/BijinTemp/index.php/api/bijin/image?token=" + jsonArray.getJSONObject(i).getString("bijin_token") + "&size=small", "X-BijinScience",
                                            "Bearer Mn6t5Dhfqz6hf4LtKToS19igKgeHDff0sCJNqQT6pzEvT0EEtT7L2FSnMWUzbaQuC9hSzbzF0eau4FYN859bl1pXxkxzknJNMRGmSgRtkSDF7C3gicht3wqQ7DqHRZ4EQkQJqIc1AGghs9n0CvKfIbWpEmW6l1kcCaLTJOut411NbFoDaYIJZFYERVldwvgZwSSfGnzl", requestBody, imageViewArrayList.get(i));
                                } catch (JSONException e) {
                                    //e.printStackTrace();
                                }
                                i++;
                            }
                        }
                    }).start();
                }
            }
        }
    }

        public void initView(View view) {
            spin_Sex = (Spinner) view.findViewById(R.id.Spin_sex);
            spin_Age = (Spinner) view.findViewById(R.id.Spin_age);
            spin_City = (Spinner) view.findViewById(R.id.Spin_address);
            Img_one = (ImageView) view.findViewById(R.id.Img_rank_one);
            Img_one.setOnClickListener(this);
            Img_two = (ImageView) view.findViewById(R.id.Img_rank_two);
            Img_two.setOnClickListener(this);
            Img_three = (ImageView) view.findViewById(R.id.Img_rank_three);
            Img_three.setOnClickListener(this);
            Img_four = (ImageView) view.findViewById(R.id.Img_rank_four);
            Img_four.setOnClickListener(this);
            Img_five = (ImageView) view.findViewById(R.id.Img_rank_five);
            Img_five.setOnClickListener(this);
            Img_six = (ImageView) view.findViewById(R.id.Img_rank_six);
            Img_six.setOnClickListener(this);
            Img_seven = (ImageView) view.findViewById(R.id.Img_rank_seven);
            Img_seven.setOnClickListener(this);
            Img_eight = (ImageView) view.findViewById(R.id.Img_rank_eight);
            Img_eight.setOnClickListener(this);
            Img_nine = (ImageView) view.findViewById(R.id.Img_rank_nine);
            Img_nine.setOnClickListener(this);
            btn_send=(Button)view.findViewById(R.id.Btn_send_Req);
            btn_send.setOnClickListener(this);
            imageViewArrayList.add(Img_one);
            imageViewArrayList.add(Img_two);
            imageViewArrayList.add(Img_three);
            imageViewArrayList.add(Img_four);
            imageViewArrayList.add(Img_five);
            imageViewArrayList.add(Img_six);
            imageViewArrayList.add(Img_seven);
            imageViewArrayList.add(Img_eight);
            imageViewArrayList.add(Img_nine);
            textView=(TextView)view.findViewById(R.id.text);
            sex = getResources().getStringArray(R.array.sex);
            age = getResources().getStringArray(R.array.age);
            city = getResources().getStringArray(R.array.city);
            ArrayAdapter<String> sexAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, sex);
            spin_Sex.setOnItemSelectedListener(this);
            spin_Sex.setAdapter(sexAdapter);
            spin_Sex.setOnItemSelectedListener(this);
            ArrayAdapter<String> ageAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, age);
            spin_Age.setAdapter(ageAdapter);
            spin_Age.setOnItemSelectedListener(this);
            ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, city);
            spin_City.setAdapter(cityAdapter);
            spin_City.setOnItemSelectedListener(this);
    }
        public void sendRankReq() {
            final RequestBody requestBody = null;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    OkhttpGet.UseGetString(okHttpClient, "http://192.168.0.103/BijinTemp/index.php/api/general/hotranking?gender="+sexStr+"&generation="+intage+"&area="+cityString, "X-BijinScience", "Bearer Mn6t5Dhfqz6hf4LtKToS19igKgeHDff0sCJNqQT6pzEvT0EEtT7L2FSnMWUzbaQuC9hSzbzF0eau4FYN859bl1pXxkxzknJNMRGmSgRtkSDF7C3gicht3wqQ7DqHRZ4EQkQJqIc1AGghs9n0CvKfIbWpEmW6l1kcCaLTJOut411NbFoDaYIJZFYERVldwvgZwSSfGnzl", requestBody, myHandler, 3);
                }
            }).start();
        }
        public void getRangkingImage(final OkHttpClient okHttpClient, String url, String headerKey, String headerVal, RequestBody body, final ImageView imageView) {
            Request request = new Request.Builder().url(url)
                    .header(headerKey, headerVal)
                    .method("GET", body)
                    .build();
            Call call = okHttpClient.newCall(request);
            call.enqueue(new Callback() {
                public void onFailure(Call call, IOException e) {
                }
                public void onResponse(final Call call, Response response) throws IOException {
                    final byte[] b = response.body().bytes();
                    final Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
                    if (ModelRankFragment.this.getActivity() != null) {
                        ModelRankFragment.this.getActivity().runOnUiThread(new Runnable() {
                            public void run() {
                                imageView.setImageBitmap(bitmap);
                            }
                        });
                    }
                }
            });
        }
        public void launch( int index){
            Intent intent=new Intent(getActivity(),bijin_detail.class);
            try {
                intent.putExtra("ImageToken",jsonArray.getJSONObject(index).getString("bijin_token"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            startActivity(intent);
        }
    }



