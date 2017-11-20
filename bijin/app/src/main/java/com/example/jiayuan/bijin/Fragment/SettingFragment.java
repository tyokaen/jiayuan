package com.example.jiayuan.bijin.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.jiayuan.bijin.R;
import com.example.jiayuan.bijin.diy_view.MysettingView;

/**
 * Created by jiayuan on 2017/08/05.
 */

public class SettingFragment extends android.support.v4.app.Fragment implements View.OnClickListener {
    Dialog dialog=null,dialog1=null;
    TextView Tx_Lout_true,Tx_Lout_false;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.setting,container,false);
        MysettingView mysettingView_login=(MysettingView) root.findViewById(R.id.logout_setting);
        MysettingView mysettingView_user=(MysettingView) root.findViewById(R.id.user_setting);
        mysettingView_user.setOnClickListener(this);
        mysettingView_login.setOnClickListener(this);
        return root;
    }
    @Override
    public void onClick(View v) {
     switch(v.getId()){
         case R.id.logout_setting:
             create_Logout_Dia();
             break;
         case R.id.user_setting:
             create_Profile_Dia();
             break;
         case R.id.rule_setting:
             break;
         case R.id.priv_setting:
             break;
     }
    }
    public void create_Logout_Dia(){
       dialog=new Dialog(getActivity(),R.style.ActionSheetDialogStyle);
       View view=LayoutInflater.from(getContext()).inflate(R.layout.bottom_dialog,null);
       Tx_Lout_true=(TextView)view.findViewById(R.id.logout_true);
       Tx_Lout_false=(TextView) view.findViewById(R.id.logout_cancel);
       dialog.setContentView(view);
        Window window=dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager windowManager=getActivity().getWindowManager();
        WindowManager.LayoutParams layoutParams=window.getAttributes();
        Display d=windowManager.getDefaultDisplay();
        layoutParams.y=20;
        layoutParams.height=(int)(d.getHeight()*0.1);
        layoutParams.width=(int)(d.getWidth()*1.0);
        window.setAttributes(layoutParams);
        dialog.show();
    }
    public void create_Profile_Dia(){
        dialog1=new Dialog(getActivity());
        View view=LayoutInflater.from(getContext()).inflate(R.layout.profile_setting,null);
        dialog1.setContentView(view);
        dialog1.show();

    }

}
