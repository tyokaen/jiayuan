package com.example.jiayuan.bijin.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jiayuan.bijin.Okhttp.OkhttpGet;
import com.example.jiayuan.bijin.R;
import com.example.jiayuan.bijin.Tools.StringToJson;
import com.example.jiayuan.bijin.diy_view.BigRoundProgressbar;
import com.example.jiayuan.bijin.diy_view.RoundProgressbar;
import com.example.jiayuan.bijin.diy_view.SyncroShowView;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

/**
 * Created by jiayuan on 2017/08/18.
 */

public class opinionFragment extends Fragment {
    SyncroShowView syn_total,syn_age,syn_local,syn_sex;
TextView Tx_syn_title1,Tx_syn_value1,
    Tx_syn_title2,Tx_syn_value2,
            Tx_syn_title3,Tx_syn_value3,
    Tx_syn_title4,Tx_syn_value4;
RoundProgressbar R_syn_value1, R_syn_value2, R_syn_value3, R_syn_value4;
BigRoundProgressbar big_R_syncro_value=null;
OkHttpClient okHttpClient=new OkHttpClient();
    TextView textView=null;
    View view;
MyHandler  myhandler=new MyHandler();
class MyHandler extends Handler{
    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        if(msg.arg1==1){
            R_syn_value1.setProgress(changeDouble(StringToJson.JsonToString((String)msg.obj,"synchro")));
        }
        else if(msg.arg1==2){
              Tx_syn_title2.setText(StringToJson.JsonToString((String)msg.obj,"label"));
            R_syn_value2.setProgress(changeDouble(StringToJson.JsonToString((String)msg.obj,"synchro")));

        }
            else if(msg.arg1==3){
            Tx_syn_title3.setText(StringToJson.JsonToString((String)msg.obj,"label"));
            R_syn_value3.setProgress(changeDouble(StringToJson.JsonToString((String)msg.obj,"synchro")));

        }
        else if(msg.arg1==4){
            Tx_syn_title4.setText(StringToJson.JsonToString((String)msg.obj,"label"));
            R_syn_value4.setProgress(changeDouble(StringToJson.JsonToString((String)msg.obj,"synchro")));
        }

    }
}
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.public_opinion,null);
        initView();
        getResult();
        return view;
    }
    public  void initView(){
        R_syn_value1=(RoundProgressbar)view.findViewById(R.id.R_syncro_value);
        Tx_syn_title2=(TextView)view.findViewById(R.id.syncro_age_title);
        R_syn_value2=(RoundProgressbar)view.findViewById(R.id.R_syncro_age);
        Tx_syn_title3=(TextView)view.findViewById(R.id.syncro_local_title);
        R_syn_value3=(RoundProgressbar)view.findViewById(R.id.R_syncro_local);
        Tx_syn_title4=(TextView)view.findViewById(R.id.syncro_sex_title);
        R_syn_value4=(RoundProgressbar)view.findViewById(R.id.R_syncro_sex);
        //big_R_syncro_value=(BigRoundProgressbar)view.findViewById(R.id.R_syncro_total);
        //Tx_syn_title=(TextView)
    }
    public void getResult(){
       final RequestBody requestBody=null;
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkhttpGet.UseGetString(okHttpClient, "http://192.168.0.118/BijinTemp/index.php/api/user/synchro?user_token=4cc2dd5dd4d3e24738606d97aac890b0&age_group=null", "X-BijinScience",
                        "Bearer Mn6t5Dhfqz6hf4LtKToS19igKgeHDff0sCJNqQT6pzEvT0EEtT7L2FSnMWUzbaQuC9hSzbzF0eau4FYN859bl1pXxkxzknJNMRGmSgRtkSDF7C3gicht3wqQ7DqHRZ4EQkQJqIc1AGghs9n0CvKfIbWpEmW6l1kcCaLTJOut411NbFoDaYIJZFYERVldwvgZwSSfGnzl", requestBody,myhandler,1);
                OkhttpGet.UseGetString(okHttpClient, "http://192.168.0.118/BijinTemp/index.php/api/user/synchro?user_token=4cc2dd5dd4d3e24738606d97aac890b0&age_group="+20, "X-BijinScience",
                        "Bearer Mn6t5Dhfqz6hf4LtKToS19igKgeHDff0sCJNqQT6pzEvT0EEtT7L2FSnMWUzbaQuC9hSzbzF0eau4FYN859bl1pXxkxzknJNMRGmSgRtkSDF7C3gicht3wqQ7DqHRZ4EQkQJqIc1AGghs9n0CvKfIbWpEmW6l1kcCaLTJOut411NbFoDaYIJZFYERVldwvgZwSSfGnzl", requestBody,myhandler,2);
                OkhttpGet.UseGetString(okHttpClient, "http://192.168.0.118/BijinTemp/index.php/api/user/synchro?user_token=4cc2dd5dd4d3e24738606d97aac890b0&gender=man", "X-BijinScience",
                        "Bearer Mn6t5Dhfqz6hf4LtKToS19igKgeHDff0sCJNqQT6pzEvT0EEtT7L2FSnMWUzbaQuC9hSzbzF0eau4FYN859bl1pXxkxzknJNMRGmSgRtkSDF7C3gicht3wqQ7DqHRZ4EQkQJqIc1AGghs9n0CvKfIbWpEmW6l1kcCaLTJOut411NbFoDaYIJZFYERVldwvgZwSSfGnzl", requestBody,myhandler,3);
                OkhttpGet.UseGetString(okHttpClient, "http://192.168.0.118/BijinTemp/index.php/api/user/synchro?user_token=4cc2dd5dd4d3e24738606d97aac890b0&area=茨城県", "X-BijinScience",
                        "Bearer Mn6t5Dhfqz6hf4LtKToS19igKgeHDff0sCJNqQT6pzEvT0EEtT7L2FSnMWUzbaQuC9hSzbzF0eau4FYN859bl1pXxkxzknJNMRGmSgRtkSDF7C3gicht3wqQ7DqHRZ4EQkQJqIc1AGghs9n0CvKfIbWpEmW6l1kcCaLTJOut411NbFoDaYIJZFYERVldwvgZwSSfGnzl", requestBody,myhandler,4);
            }
        }).start();


    }
    public int changeDouble(String str){
        int result;
        result=Integer.parseInt(str.substring(0,2));
        return result;
    }
}
