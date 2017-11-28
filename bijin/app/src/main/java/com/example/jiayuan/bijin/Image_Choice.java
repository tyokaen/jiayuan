package com.example.jiayuan.bijin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.example.jiayuan.bijin.Adapter.ImageAdapter;
import com.example.jiayuan.bijin.Okhttp.OkhttpGet;
import com.example.jiayuan.bijin.Tools.StringToJson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

public class Image_Choice extends AppCompatActivity implements View.OnClickListener,ViewSwitcher.ViewFactory {
    Integer[] images = {R.drawable.bijin1};
    ProgressBar progressBar = null;
    TextView Tx_progress = null, Tx_sam_count;
    ImageSwitcher imageSwitcher = null;
    ImageButton IBtn_next, IBtn_like;
    int index = 0;
    int increment = 0;
    int number_left = 0;
    OkHttpClient okHttpClient = new OkHttpClient();
    String result = "jiyuan", ImageToken = null, userToken = null;
    ProgressDialog progressDialog = null;
    JSONArray jsonArray = null;
    byte[] b = new byte[10000];
    int arrayIndex = 0;
    boolean flag = true, IsClick = false;
    MyHandler myHandler = new MyHandler();
    MediaType type = MediaType.parse("application/x-www-form-urlencoded;charset=utf-8");
    ArrayList<byte[]> bytelist=new ArrayList<byte[]>();
    String vote = null;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image__choice);
        ImageAdapter imageAdapter = new ImageAdapter(this, images);
        imageAdapter.createReflectedImages();
        imageSwitcher = (ImageSwitcher) findViewById(R.id.Gallery1);
        IBtn_next = (ImageButton) findViewById(R.id.sample_dislake);
        IBtn_like = (ImageButton) findViewById(R.id.sample_like);
        IBtn_like.setOnClickListener(this);
        IBtn_next.setOnClickListener(this);
        imageSwitcher.setFactory(this);
        progressBar = (ProgressBar) findViewById(R.id.sample_progress);
        progressBar.setMax(100);
        Tx_progress = (TextView) findViewById(R.id.progress_recoder);
        Tx_sam_count = (TextView) findViewById(R.id.sample_count);
        int ArrayIndex = 0;
        Intent intent = this.getIntent();
        ImageToken = intent.getStringExtra("ImageToken");
        userToken = intent.getStringExtra("UserToken");
        Tx_sam_count.setText("あと" + (getArrayLength(ImageToken) ) + "枚");

    }
    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
             if(msg.arg1==1) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
                imageSwitcher.setImageDrawable(new BitmapDrawable(bitmap));
            }
        }
    }
    public void createDialog() {
        progressDialog = ProgressDialog.show(this, "提示", "正在获取");
    }
    public void Shift_function_choice(){
        Intent intent=new Intent(this,function_choice.class);
        intent.putExtra("UserToken",userToken);
        startActivity(intent);
    }
    protected void onResume() {
        super.onResume();
        getImage(1);

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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sample_dislake:
                if (index < getArrayLength(ImageToken)-1) {
                    if(index==0){
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        send_Image(jsonArray.getJSONObject(index).getString("token"),0);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }).start();
                        }

                    else {
                    getImage(0);
                    }
                    index++;
                    number_left = getArrayLength(ImageToken) - index ;
                    progressBar.setProgress(increment + 10);
                    increment = increment + 10;
                    Tx_progress.setText("" + increment + "%");
                    Tx_sam_count.setText("あと" + number_left + "枚");
                } else {
                    Shift_function_choice();
                }
                break;
            case R.id.sample_like:
                //getImage(1);
                if (index < getArrayLength(ImageToken)-1) {
                    if(index==0){
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        send_Image(jsonArray.getJSONObject(index).getString("token"),1);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }).start();
                    }
                    else {
                       getImage(1);
                    }
                    index++;
                    number_left = getArrayLength(ImageToken) - index ;
                    progressBar.setProgress(increment + 10);
                    increment = increment + 10;
                    Tx_progress.setText("" + increment + "%");
                    Tx_sam_count.setText("あと" + number_left + "枚");
                }
                else{
                    Shift_function_choice();
                }
                break;
        }
    }
    public View makeView() {
        ImageView imageView = new ImageView(this);
        return imageView;
    }
    public void send_Image(String ImgToken, int i) {
        if (i == 1)
            vote="upper";
        else vote = "lower";
              RequestBody  requestBody = RequestBody.create(type,"user_token="+userToken+"&bijin_token="+ImgToken+"&vote="+vote);
                result = OkhttpGet.UsePost(okHttpClient, "http://192.168.0.118/BijinTemp/index.php/api/vote","X-BijinScience",
                        "Bearer Mn6t5Dhfqz6hf4LtKToS19igKgeHDff0sCJNqQT6pzEvT0EEtT7L2FSnMWUzbaQuC9hSzbzF0eau4FYN859bl1pXxkxzknJNMRGmSgRtkSDF7C3gicht3wqQ7DqHRZ4EQkQJqIc1AGghs9n0CvKfIbWpEmW6l1kcCaLTJOut411NbFoDaYIJZFYERVldwvgZwSSfGnzl", requestBody);
}
    public byte[] getImage(final int i){
         new Thread(new Runnable() {
             @Override
             public void run() {
                 RequestBody requestBody = null;
                     b = OkhttpGet.UseGetImage(okHttpClient, "http://192.168.0.118/BijinTemp/index.php/api/bijin/image?token=" + getImageToken(jsonArray, arrayIndex) + "&size=large", "X-BijinScience",
                             "Bearer Mn6t5Dhfqz6hf4LtKToS19igKgeHDff0sCJNqQT6pzEvT0EEtT7L2FSnMWUzbaQuC9hSzbzF0eau4FYN859bl1pXxkxzknJNMRGmSgRtkSDF7C3gicht3wqQ7DqHRZ4EQkQJqIc1AGghs9n0CvKfIbWpEmW6l1kcCaLTJOut411NbFoDaYIJZFYERVldwvgZwSSfGnzl", requestBody);
                         if(arrayIndex>0) {
                             try {
                                 send_Image(jsonArray.getJSONObject(arrayIndex).getString("token"), i);
                             } catch (JSONException e) {
                                 e.printStackTrace();
                             }
                         }
                 Message message=new Message();
                 message.arg1=1;
                 myHandler.sendMessage(message);
                 arrayIndex++;
                     }
         }).start();

                       return b;
    }
}

