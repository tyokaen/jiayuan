package com.example.jiayuan.library;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Provider.ProviderDao;
import Provider.provider;

public class Login_provider extends Fragment {
Button btn_p_login,btn_p_Reg;
String str,str1;
EditText ed_p_login,ed_p_Reg;
ProviderDao providerDao;
Bitmap map;
ImageView imageView;
private static  String Pro_name=null;

File file=new File(Environment.getExternalStorageDirectory(),"provider_info.csv");
    ArrayList<provider> list;
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.activity_login_provider,container,false);
        btn_p_login=(Button)view.findViewById(R.id.p_login);
        ed_p_login=(EditText)view.findViewById(R.id.provider_name);
        ed_p_Reg=(EditText)view.findViewById(R.id.provider_pwd);
        providerDao=new ProviderDao(getActivity());
   list =providerDao.find();
        btn_p_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<list.size();i++){
                    String string=ed_p_login.getText().toString();
                    String string1=ed_p_Reg.getText().toString();
                    if(string.equals(list.get(i).getID())&&string1.equals(list.get(i).getPassword1())){
                        Intent intent=new Intent(getActivity(),Info_Register.class);
                        intent.putExtra("name",Pro_name);
                        startActivity(intent);
                        break;
                    }
                    else if(i==list.size()){
                        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                        builder.setTitle("提示信息");
                        builder.setMessage("您输入的信息有误");
                        AlertDialog alertDialog=builder.create();
                        alertDialog.show();
                    }
                }
            }
        });
        btn_p_Reg=(Button)view.findViewById(R.id.p_register);
        btn_p_Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRegister();
            }
        });
        return view;
    }
    public void showRegister(){
        final EditText e1,e2,e3;
        LayoutInflater layoutInflater=(LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View layout=layoutInflater.inflate(R.layout.provider_register,(ViewGroup)getActivity().findViewById(R.id.LinearLayout_register));
        e1=(EditText) layout.findViewById(R.id.ed_p_ID);
        e2=(EditText)layout.findViewById(R.id.ed_p_Pwd);
        e3=(EditText) layout.findViewById(R.id.ed_p_mail);
        imageView=(ImageView)layout.findViewById(R.id.ed_p_Image);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                /* 开启Pictures画面Type设定为image */
                intent.setType("image/*");
                /* 使用Intent.ACTION_GET_CONTENT这个Action */
                intent.setAction(Intent.ACTION_GET_CONTENT);
                /* 取得相片后返回本画面 */
                startActivityForResult(intent, 1);
            }
        });
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setTitle("注册信息");
        builder.setView(layout);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                     String ID=e1.getText().toString();
                     String Password=e2.getText().toString();
                     String mail=e3.getText().toString();
                     byte[] image=Imageconvert();
                    providerDao.add(new provider(ID,Password,mail,image));
                    Pro_name=ID;
                   list=providerDao.find();
                   saveToCard1(list);
            }
        });
        AlertDialog dialog=builder.create();
        dialog.show();
    }
    public byte[] Imageconvert(){
        ByteArrayOutputStream out=new ByteArrayOutputStream();
        map.compress(Bitmap.CompressFormat.PNG,100,out);
        return out.toByteArray();
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == getActivity().RESULT_OK) {
            final Uri uri = data.getData();
            Log.e("uri", uri.toString());
            ContentResolver cr = getActivity().getContentResolver();
            try {
                map = BitmapFactory.decodeStream(cr.openInputStream(uri));
                imageView.setImageBitmap(map);
            } catch (FileNotFoundException e) {
                Log.e("Exception", e.getMessage(), e);
            }
        }
            super.onActivityResult(requestCode, resultCode, data);
        }
    public void saveToCard1(ArrayList<?> arrayList){
        try {
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(new FileOutputStream(file,false));
            objectOutputStream.writeObject(arrayList);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
