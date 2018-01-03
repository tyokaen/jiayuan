package com.example.jiayuan.bijin.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jiayuan.bijin.Okhttp.OkhttpGet;
import com.example.jiayuan.bijin.R;
import com.example.jiayuan.bijin.Tools.StringToJson;
import com.example.jiayuan.bijin.Tools.ToolsBitmap;
import com.example.jiayuan.bijin.cache.BijinImageCache;
import com.example.jiayuan.bijin.cache.UserTokenCache;
import com.example.jiayuan.bijin.diy_view.MyGridView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by jiayuan on 2017/08/09.
 */

public class RankListFragment extends android.support.v4.app.Fragment implements View.OnClickListener {
    MyGridView Grid = null;
    Button Btn_choice1, Btn_choice2, Btn_choice3,Btn_bijin_Register;
    TextView Tx_best_name1,Tx_best_name2,Tx_best_name3;
    Dialog dialog = null;
    private RecyclerView.LayoutManager layoutManager;
    MyGridView gridView = null;
    GridviewAdapter gridView1Adapter = null;
    ImageView Img_choice1, Img_choice2, Img_choice3;
    GridviewAdapter gridviewAdapter = null;
    TextView textView = null;
    ArrayList<String> bijinToken = new ArrayList<>();
    HashMap<Integer,String> bestBijinToken=new HashMap<Integer,String>();
    OkHttpClient okHttpClient = new OkHttpClient();
    int ClickCount=0;
    public static int REQUEST_DETAIL=0;
    String result=null;
    Myhandler myhandler=new Myhandler();
    JSONArray jsonArray=new JSONArray();
    ArrayList<String> arr_best_bijin_token=new ArrayList<String>();
    byte[] b=new byte[10000];
    ArrayList<ImageView> imageViews=new ArrayList<ImageView>();
    class Myhandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.arg1==1){
                Toast.makeText(getActivity(),"登録完了しました",Toast.LENGTH_SHORT).show();

            }
            else if(msg.arg1==3){
               jsonArray=StringToJson.getJSonArray(jsonArray,(String)msg.obj,"best");
                try {
                    Tx_best_name1.setText(jsonArray.getJSONObject(0).getString("bijin_name"));
                    Tx_best_name2.setText(jsonArray.getJSONObject(1).getString("bijin_name"));
                    Tx_best_name3.setText(jsonArray.getJSONObject(2).getString("bijin_name"));
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            int i=0;
                            RequestBody requestBody=null;
                            while(i<3)
                                try {
                                    getBestBijin(okHttpClient, "http://192.168.0.103/BijinTemp/index.php/api/bijin/image?token=" + jsonArray.getJSONObject(i).getString("bijin_token") + "&size=small", "X-BijinScience",
                                            "Bearer Mn6t5Dhfqz6hf4LtKToS19igKgeHDff0sCJNqQT6pzEvT0EEtT7L2FSnMWUzbaQuC9hSzbzF0eau4FYN859bl1pXxkxzknJNMRGmSgRtkSDF7C3gicht3wqQ7DqHRZ4EQkQJqIc1AGghs9n0CvKfIbWpEmW6l1kcCaLTJOut411NbFoDaYIJZFYERVldwvgZwSSfGnzl", requestBody,imageViews.get(i));
                                    i++;
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                        }
                    }).start();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.like_list, null);
        // Grid=(MyGridView) view.findViewById(R.id.Grid1);
        Btn_choice1 = (Button) view.findViewById(R.id.btn_best1);
        Btn_choice2 = (Button) view.findViewById(R.id.btn_best2);
        Btn_choice3 = (Button) view.findViewById(R.id.btn_best3);
        Btn_bijin_Register=(Button)view.findViewById(R.id.btn_best＿register);
        Img_choice1 = (ImageView) view.findViewById(R.id.image_best1);
        imageViews.add(Img_choice1);
        Img_choice2 = (ImageView) view.findViewById(R.id.image_best2);
        imageViews.add(Img_choice2);
        Img_choice3 = (ImageView) view.findViewById(R.id.image_best3);
        imageViews.add(Img_choice3);
        Tx_best_name1=(TextView)view.findViewById(R.id.Tx_best1_name);
        Tx_best_name2=(TextView)view.findViewById(R.id.Tx_best2_name);
        Tx_best_name3=(TextView)view.findViewById(R.id.Tx_best3_name);
        //textView = (TextView) view.findViewById(R.id.text_like_list);
        Btn_choice1.setOnClickListener(this);
        Btn_choice2.setOnClickListener(this);
        Btn_choice3.setOnClickListener(this);
        Btn_bijin_Register.setOnClickListener(this);
        getImageToken(getArguments().getString("bijinlist"), bijinToken);
        getBestName();
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_best1:
                MakeChoiceDialog(1);
                break;
            case R.id.btn_best2:
                MakeChoiceDialog(2);
                break;
            case R.id.btn_best3:
                MakeChoiceDialog(3);
                break;
            case R.id.btn_best＿register:
                if(ClickCount<3){
                    Toast.makeText(getActivity(),"ベスト3を入力してください",Toast.LENGTH_SHORT).show();
                }
                else {
                    sendLikeBest();
                }
        }
    }

    public void getImageToken(String result, ArrayList<String> list) {
        jsonArray = StringToJson.getJSonArray(jsonArray, result, "vote_list");
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                list.add(jsonArray.getJSONObject(i).getString("bijin_token"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    public void MakeChoiceDialog(final int i) {
        dialog = new Dialog(getActivity(), R.style.ActionSheetDialogStyle);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.choose_like_dialog, null);
        gridView = (MyGridView) view.findViewById(R.id.Grid2);
        gridView1Adapter = new GridviewAdapter(getActivity());
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                  dialog.cancel();
                  Intent intent=new Intent(getActivity(),bijin_best_detail.class);
                  intent.putExtra("ImageToken",bijinToken.get(position));
                  intent.putExtra("ButtonToken",i);
              startActivityForResult(intent,REQUEST_DETAIL);
            }
        });
        gridView.setAdapter(gridView1Adapter);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager windowManager = getActivity().getWindowManager();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        Display d = windowManager.getDefaultDisplay();
        layoutParams.y = 20;
        layoutParams.height = (int) (d.getHeight() * 0.9);
        layoutParams.width = (int) (d.getWidth() * 0.9);
        window.setAttributes(layoutParams);
        dialog.show();
    }

    class GridviewAdapter extends BaseAdapter implements AbsListView.OnScrollListener {
        Context context;
        private int mFirstVisibleItem;
        private int mVisibleItemCount;

        public GridviewAdapter(Context context) {
            this.context = context;
            gridView.setOnScrollListener(this);
        }

        public int getCount() {
            return bijinToken.size();
        }

        @Override
        public Object getItem(int position) {
            return bijinToken.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            String tag = (String) getItem(position);
            MyHolder myHolder = null;
            if (convertView == null) {
                myHolder = new MyHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.like_list_image, null);
                myHolder.imageView = (ImageView) convertView.findViewById(R.id.like_image);
                convertView.setTag(myHolder);
            } else {
                myHolder = (MyHolder) convertView.getTag();
            }
            myHolder.imageView.setTag(tag);
            setImageForImageView(tag, myHolder.imageView);
            return convertView;
        }
        private void setImageForImageView(String index, ImageView imageView) {
            Bitmap bitmap = BijinImageCache.getValueFromCache(index);
            if (bitmap != null) {
                imageView.setImageBitmap(bitmap);
            } else {
                imageView.setImageResource(R.drawable.defaultuserimage);
            }
        }
        private void loadBitmaps(final int firstVisibleItem, final int visibleItemCount) {
            final RequestBody requestBody = null;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = firstVisibleItem; i < firstVisibleItem + visibleItemCount; i++) {
                        {
                            Bitmap bitmap = BijinImageCache.getValueFromCache(bijinToken.get(i));
                            if (bitmap == null)
                                UseGetImage1(okHttpClient, "http://192.168.0.103/BijinTemp/index.php/api/bijin/image?token=" + bijinToken.get(i) + "&size=small", "X-BijinScience",
                                        "Bearer Mn6t5Dhfqz6hf4LtKToS19igKgeHDff0sCJNqQT6pzEvT0EEtT7L2FSnMWUzbaQuC9hSzbzF0eau4FYN859bl1pXxkxzknJNMRGmSgRtkSDF7C3gicht3wqQ7DqHRZ4EQkQJqIc1AGghs9n0CvKfIbWpEmW6l1kcCaLTJOut411NbFoDaYIJZFYERVldwvgZwSSfGnzl", requestBody, i);
                        }

                    }
                }

            }).start();
        }

        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            if (scrollState == SCROLL_STATE_IDLE)
                loadBitmaps(mFirstVisibleItem, mVisibleItemCount);
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            mFirstVisibleItem = firstVisibleItem;
            mVisibleItemCount = visibleItemCount;
            loadBitmaps(firstVisibleItem, visibleItemCount);
        }
    }
    public void UseGetImage1(OkHttpClient okHttpClient, String url, String headerKey, String headerVal, RequestBody body, final int position) {
        Request request = new Request.Builder().url(url)
                .header(headerKey, headerVal)
                .method("GET", body)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            public void onFailure(Call call, IOException e) {
            }
            public void onResponse(Call call, Response response) throws IOException {
                final byte[] b = response.body().bytes();
                final Bitmap bitmap = ToolsBitmap.getInstance().compressBitmap(b);
                if (bitmap != null) {
                    BijinImageCache.getInstance().put(bijinToken.get(position),bitmap);
                }
                if (RankListFragment.this.getActivity() != null) {
                    RankListFragment.this.getActivity().runOnUiThread(new Runnable() {
                        public void run() {
                            ImageView imageView = (ImageView) gridView.findViewWithTag(bijinToken.get(position));
                            if(bitmap!=null&&imageView!=null)
                            imageView.setImageBitmap(bitmap);
                        }
                    });
                }
            }
        });
    }
    class MyHolder {
        ImageView imageView;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("jiayuan", "sdsdsdsdsd");
        System.gc();
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
            if(resultCode==1) {
                Img_choice1.setImageBitmap(BijinImageCache.getValueFromCache(data.getStringExtra("ImageToken")));
                bestBijinToken.put(0,data.getStringExtra("ImageToken"));
                Tx_best_name1.setText(data.getStringExtra("bijinname"));
                ClickCount++;
            }
            if(resultCode==2)
                if(requestCode == REQUEST_DETAIL) {
                    Img_choice2.setImageBitmap(BijinImageCache.getValueFromCache(data.getStringExtra("ImageToken")));
                    Tx_best_name2.setText(data.getStringExtra("bijinname"));
                    bestBijinToken.put(1,data.getStringExtra("ImageToken"));
                    ClickCount++;
                }
            if(resultCode==3)
                if(requestCode == REQUEST_DETAIL) {
                    Img_choice3.setImageBitmap(BijinImageCache.getValueFromCache(data.getStringExtra("ImageToken")));
                    Tx_best_name3.setText(data.getStringExtra("bijinname"));
                    bestBijinToken.put(2,data.getStringExtra("ImageToken"));
                    ClickCount++;
                }

    }
    public void sendLikeBest(){
       final FormBody formBody=new FormBody.Builder()
                .add("user_token", UserTokenCache.getInstance().getUserToken(getActivity()))
                .add("ranking[0][bijin_token]",bestBijinToken.get(0))
                .add("ranking[1][bijin_token]",bestBijinToken.get(1))
                .add("ranking[2][bijin_token]",bestBijinToken.get(2))
                .build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                result= OkhttpGet.UsePost(okHttpClient,"http://192.168.0.103/BijinTemp/index.php/api/usersbest","X-BijinScience","Bearer Mn6t5Dhfqz6hf4LtKToS19igKgeHDff0sCJNqQT6pzEvT0EEtT7L2FSnMWUzbaQuC9hSzbzF0eau4FYN859bl1pXxkxzknJNMRGmSgRtkSDF7C3gicht3wqQ7DqHRZ4EQkQJqIc1AGghs9n0CvKfIbWpEmW6l1kcCaLTJOut411NbFoDaYIJZFYERVldwvgZwSSfGnzl", formBody);
                Message message=new Message();
                message.arg1=1;
                myhandler.sendMessage(message);
            }
        }).start();
    }
    public void getBestName(){
        final RequestBody body=null;
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkhttpGet.UseGetString(okHttpClient,"http://192.168.0.103/BijinTemp/index.php/api/usersbest?user_token="+UserTokenCache.getInstance().getUserToken(getActivity()),"X-BijinScience",
                        "Bearer Mn6t5Dhfqz6hf4LtKToS19igKgeHDff0sCJNqQT6pzEvT0EEtT7L2FSnMWUzbaQuC9hSzbzF0eau4FYN859bl1pXxkxzknJNMRGmSgRtkSDF7C3gicht3wqQ7DqHRZ4EQkQJqIc1AGghs9n0CvKfIbWpEmW6l1kcCaLTJOut411NbFoDaYIJZFYERVldwvgZwSSfGnzl",body,myhandler,3);
            }
        }).start();

    }

    public void getBestBijin(OkHttpClient okHttpClient, String url, String headerKey, String headerVal, RequestBody body, final ImageView imageView) {
        Request request = new Request.Builder().url(url)
                .header(headerKey, headerVal)
                .method("GET", body)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            public void onFailure(Call call, IOException e) {
            }
            public void onResponse(Call call, Response response) throws IOException {
                final byte[] b = response.body().bytes();
                final Bitmap bitmap = ToolsBitmap.getInstance().getScaledBitmap(b,200);
                if (RankListFragment.this.getActivity() != null) {
                    RankListFragment.this.getActivity().runOnUiThread(new Runnable() {
                        public void run() {
                            if(bitmap!=null&&imageView!=null)
                                imageView.setImageBitmap(bitmap);
                            else{
                                imageView.setImageResource(R.drawable.defaultuserimage);
                            }
                        }
                    });
                }
            }
        });
    }


}


