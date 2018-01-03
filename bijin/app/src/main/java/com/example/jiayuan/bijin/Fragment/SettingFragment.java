package com.example.jiayuan.bijin.Fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.jiayuan.bijin.Activity.ProfileActivity;
import com.example.jiayuan.bijin.Activity.WebActivity;
import com.example.jiayuan.bijin.R;
import com.example.jiayuan.bijin.cache.UserTokenCache;
import com.example.jiayuan.bijin.diy_view.MysettingView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by jiayuan on 2017/08/05.
 */

public class SettingFragment extends android.support.v4.app.Fragment implements View.OnClickListener {
    Dialog dialog=null,dialog1=null;
    TextView Tx_Lout_true,Tx_Lout_false;
    MysettingView mysettingView_rule,mysettingView_policy;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.setting,container,false);
        MysettingView mysettingView_login=(MysettingView) root.findViewById(R.id.logout_setting);
        MysettingView mysettingView_user=(MysettingView) root.findViewById(R.id.user_setting);
        mysettingView_rule=(MysettingView)root.findViewById(R.id.rule_setting);
        mysettingView_policy=(MysettingView)root.findViewById(R.id.priv_setting);
        mysettingView_user.setOnClickListener(this);
        mysettingView_login.setOnClickListener(this);
        mysettingView_policy.setOnClickListener(this);
        mysettingView_rule.setOnClickListener(this);
        return root;
    }
    @Override
    public void onClick(View v) {
     switch(v.getId()){
         case R.id.logout_setting:
             create_Logout_Dia();
             break;
         case R.id.user_setting:
             Intent intent=new Intent(getActivity(),ProfileActivity.class);
             startActivity(intent);
             break;
         case R.id.rule_setting:
             lanuch(getFromAsset("terms.html"));
             break;
         case R.id.priv_setting:
             lanuch("http://www.bijin-co.jp/company/policy.html");
             break;
         case R.id.logout_cancel:
             dialog.cancel();
             break;
         case R.id.logout_true:
             UserTokenCache.getInstance().deldToken();
             getActivity().finish();
             break;

     }
    }
    public void create_Logout_Dia(){
       dialog=new Dialog(getActivity(),R.style.ActionSheetDialogStyle);
       View view=LayoutInflater.from(getContext()).inflate(R.layout.bottom_dialog,null);
       Tx_Lout_true=(TextView)view.findViewById(R.id.logout_true);
       Tx_Lout_false=(TextView) view.findViewById(R.id.logout_cancel);
       Tx_Lout_false.setOnClickListener(this);
       Tx_Lout_true.setOnClickListener(this);
       dialog.setContentView(view);
        Window window=dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager windowManager=getActivity().getWindowManager();
        WindowManager.LayoutParams layoutParams=window.getAttributes();
        Display d=windowManager.getDefaultDisplay();
        layoutParams.y=20;
        layoutParams.height=200;
        layoutParams.width=(int)(d.getWidth()*1.0);
        window.setAttributes(layoutParams);
        dialog.show();
    }
    public void lanuch(String url){
        Intent intent=new Intent(getActivity(), WebActivity.class);
        intent.putExtra("URL",url);
        startActivity(intent);
    }
    public String getFromAsset(String filename){
        try {
            InputStreamReader inputStreamReader=new InputStreamReader(getResources().getAssets().open(filename));
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            String line="";
            String Result="";
            while((line=bufferedReader.readLine())!=null){
                Result+=line;
            }
            return Result;
        } catch (IOException e) {
            e.printStackTrace();
        }
           return "";
    }

}
