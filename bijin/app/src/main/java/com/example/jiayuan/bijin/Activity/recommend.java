package com.example.jiayuan.bijin.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jiayuan.bijin.Okhttp.OkhttpGet;
import com.example.jiayuan.bijin.R;
import com.example.jiayuan.bijin.Tools.StringToJson;
import com.example.jiayuan.bijin.Tools.ToolsBitmap;
import com.example.jiayuan.bijin.cache.UserTokenCache;

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

import static com.example.jiayuan.bijin.cache.UserTokenCache.userTokenCache;

public class recommend extends AppCompatActivity {
ArrayList<String> bijinToken=new ArrayList<String>();
GridView Rec_gridView=null;
TextView Tx_Choice_Count=null,Tx_refresh;
HashMap<Integer,View>hashMap=new HashMap<Integer, View>();
ArrayList<String> BIjinTokenList=new ArrayList<String>();
int ChoiceCount=0;
OkHttpClient okHttpClient=new OkHttpClient();
SwipeRefreshLayout swipeRefreshLayout=null;
Myhandler myHandler=new Myhandler();
int arraySize=0;
String result="";
    FragmentManager fragmentManager=getSupportFragmentManager();
class Myhandler extends Handler {
    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        if(msg.arg1==1){
            GridviewAdapter gridviewAdapter=new GridviewAdapter(recommend.this);
          Rec_gridView.setAdapter(gridviewAdapter);
        }
        else if(msg.arg1==4){
            if(StringToJson.JsonToString(result,"result").equals("true")){
                 makeDialog1();
            }
        }
    }
}
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);
        Rec_gridView=(GridView) this.findViewById(R.id.Grid_Rec);
        swipeRefreshLayout=(SwipeRefreshLayout)this.findViewById(R.id.swip_Rec);
        Tx_Choice_Count=(TextView)this.findViewById(R.id.Tx_Count_Choose_Rec);
        Tx_refresh=(TextView)this.findViewById(R.id.Tx_refresh_Rec);
        getBijnToken();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(ChoiceCount<3){
                    Toast.makeText(recommend.this,ChoiceCount+"枚の画像もう保存しました！！",Toast.LENGTH_LONG).show();
                    getBijnToken();
                }
                clearList(bijinToken);
                hashMap.clear();
                recommend.this.runOnUiThread(new Runnable() {
                    public void run() {
                        Tx_refresh.setText("リレッシュしてください↓↓↓");
                    }
                });
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void clearList(ArrayList<String> bijinToken) {
        bijinToken.clear();
    }

    class GridviewAdapter extends BaseAdapter implements AbsListView.OnScrollListener {
        Context context;
        private int mFirstVisibleItem;
        private int mVisibleItemCount;
        public GridviewAdapter(Context context) {
            this.context = context;
            Rec_gridView.setOnScrollListener(this);
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
        public View getView(final int position, View convertView, ViewGroup parent) {
            String tag = (String) getItem(position);
            MyHolder myHolder = null;
            if (hashMap.get(position)==null||!hashMap.containsKey(position)) {
                myHolder = new MyHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.recommend_image, null);
                myHolder.imageView = (ImageView) convertView.findViewById(R.id.Img_recommend);
                myHolder.checkBox=(CheckBox) convertView.findViewById(R.id.check_Rec);
                myHolder.checkBox.setChecked(false);
                myHolder.ImageView_back=(ImageView)convertView.findViewById(R.id.Img_check_background);
                convertView.setTag(myHolder);
                hashMap.put(position,convertView);
            } else {
                convertView=hashMap.get(position);
                myHolder = (MyHolder) convertView.getTag();
            }
            final MyHolder finalMyHolder = myHolder;
            final MyHolder finalMyHolder1 = myHolder;
            myHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if(isChecked) {
                        if (ChoiceCount==3) {
                            finalMyHolder.checkBox.setChecked(false);
                        }
                        else{
                            finalMyHolder.ImageView_back.setVisibility(View.VISIBLE);
                           BIjinTokenList.add(bijinToken.get(position));
                            ChoiceCount++;
                            Tx_Choice_Count.setText("後" + (3 - ChoiceCount) + "枚選んでください");
                        }
                    }
                    else {
                        finalMyHolder.ImageView_back.setVisibility(View.INVISIBLE);
                        BIjinTokenList.remove(ChoiceCount - 1);
                        ChoiceCount--;
                        Tx_Choice_Count.setText("後" + (3 - ChoiceCount) + "枚選んでください");
                    }
                    if(ChoiceCount==3)
                        makeDialog();
                }
            });
            myHolder.imageView.setTag(tag);
            return convertView;
        }
        private void loadBitmaps(final int firstVisibleItem, final int visibleItemCount) {
            final RequestBody requestBody = null;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = firstVisibleItem; i < firstVisibleItem + visibleItemCount; i++) {
                        {
                            UseGetImage1(okHttpClient, "http://192.168.0.118/BijinTemp/index.php/api/bijin/image?token=" + bijinToken.get(i) + "&size=small", "X-BijinScience",
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
                if (recommend.this!= null&&bijinToken.size()>0) {
                    recommend.this.runOnUiThread(new Runnable() {
                        public void run() {
                            ImageView imageView = (ImageView) Rec_gridView.findViewWithTag(bijinToken.get(position));
                            if(bitmap!=null&&imageView!=null)
                                imageView.setImageBitmap(bitmap);
                            else imageView.setImageResource(R.drawable.defaultuserimage);
                        }
                    });
                }
            }
        });
    }
    class MyHolder {
        ImageView imageView,ImageView_back;
        CheckBox checkBox;
    }
   public void getBijnToken(){
       final RequestBody requestBody=null;
       new Thread(new Runnable() {
           @Override
           public void run() {
            result=OkhttpGet.UseGetList(okHttpClient, "http://192.168.0.118/BijinScience-Web/index.php/api/recommend/result?"+"\n"+"user_token="+userTokenCache.getInstance().getUserToken(recommend.this)+"&bijin_token_one="+getIntent().getStringExtra("bijin_token_one")+"&bijin_token_two="+getIntent().getStringExtra("bijin_token_two")+"&bijin_token_three="+getIntent().getStringExtra("bijin_token_three")+"&tag_id="+ getIntent().getIntExtra("tag_id",100),
                       "X-BijinScience",
                       "Bearer Mn6t5Dhfqz6hf4LtKToS19igKgeHDff0sCJNqQT6pzEvT0EEtT7L2FSnMWUzbaQuC9hSzbzF0eau4FYN859bl1pXxkxzknJNMRGmSgRtkSDF7C3gicht3wqQ7DqHRZ4EQkQJqIc1AGghs9n0CvKfIbWpEmW6l1kcCaLTJOut411NbFoDaYIJZFYERVldwvgZwSSfGnzl", requestBody,bijinToken,"recommend_token_list");
                     Message message=new Message();
                     message.arg1=1;
               myHandler.sendMessage(message);
           }
       }).start();
   }
    public void postPreRecommend() {
        final FormBody formbody = new FormBody.Builder()
                .add("user_token", UserTokenCache.getInstance().getUserToken(recommend.this))
                .add("bijin_token_one", BIjinTokenList.get(0))
                .add("bijin_token_two", BIjinTokenList.get(1))
                .add("bijin_token_three", BIjinTokenList.get(2))
                .add("tag_id", "" + getIntent().getIntExtra("TagId", 100))
                .build();
        new Thread(new Runnable() {
            public void run() {
                result = OkhttpGet.UsePost(okHttpClient, "http://192.168.0.118/BijinScience-Web/index.php/api/tag/vote", "X-BijinScience", "Bearer Mn6t5Dhfqz6hf4LtKToS19igKgeHDff0sCJNqQT6pzEvT0EEtT7L2FSnMWUzbaQuC9hSzbzF0eau4FYN859bl1pXxkxzknJNMRGmSgRtkSDF7C3gicht3wqQ7DqHRZ4EQkQJqIc1AGghs9n0CvKfIbWpEmW6l1kcCaLTJOut411NbFoDaYIJZFYERVldwvgZwSSfGnzl", formbody);
                Message message = new Message();
                message.arg1 = 4;
                myHandler.sendMessage(message);
            }
        }).start();
    }
    public void makeDialog(){
        AlertDialog.Builder alertDialog=new AlertDialog.Builder(recommend.this);
        alertDialog.setMessage("推薦結果を送りますか？");
        alertDialog.setNegativeButton("キャンセル", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alertDialog.setPositiveButton("submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                postPreRecommend();
            }
        });
        alertDialog.show();
    }
    public void makeDialog1(){
        AlertDialog.Builder alertDialog=new AlertDialog.Builder(recommend.this);
        alertDialog.setMessage("推薦完了しました");
        alertDialog.setNegativeButton("もう一回", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                   Intent intent=new Intent(recommend.this,user_main.class);
                   intent.putExtra("shift","ToTagChoice");
                   startActivity(intent);
            }
        });
        alertDialog.setPositiveButton("マイページへ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent=new Intent(recommend.this,user_main.class);
                startActivity(intent);

            }
        });
        alertDialog.show();
    }


}
