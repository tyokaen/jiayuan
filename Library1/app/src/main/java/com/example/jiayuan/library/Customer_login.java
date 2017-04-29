package com.example.jiayuan.library;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import User.Person;
import User.PersonDao;

/**
 * Created by jiayuan on 2016/11/01.
 */
public class Customer_login extends Fragment {
    EditText ed,ed1;
    int i=0;
    Button b,b1,b2,b5;
    static String str,str1,str2,str3;
    TextView t;
    public String str4;
    ImageView imageView,imageView2;
    Handler handler=new Handler();
    ArrayList<Person> list;
    PersonDao dao;
    static int[] image = {R.drawable.a2, R.drawable.a3, R.drawable.a4, R.drawable.a5, R.drawable.a6,
            R.drawable.a7, R.drawable.a8, R.drawable.a9, R.drawable.a10, R.drawable.a11};
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState){

        View layout=inflater.inflate(R.layout.customer_login,container,false);
        ed=(EditText)layout.findViewById(R.id.EditText1);
        ed1=(EditText)layout.findViewById(R.id.EditText2);
        b=(Button)layout.findViewById(R.id.button1);
        b1=(Button)layout.findViewById(R.id.button2);
        b2=(Button)layout.findViewById(R.id.button3);
        b5=(Button)layout.findViewById(R.id.button6);
        t=(TextView)layout.findViewById(R.id.TextView10);
        imageView=(ImageView)layout.findViewById(R.id.ImageView2);
        dao=new PersonDao(getActivity());
        list=dao.find();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed.setText(null);
                ed1.setText(null);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),login.class);
                startActivity(intent);
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                str=ed.getText().toString();
                str1=ed1.getText().toString();
                for(int i=0;i<list.size();i++){
                    if(str.equals(list.get(i).getZhanghao())&&str1.equals(list.get(i).getPassword())){
                        Intent intent=new Intent(getActivity(),Main.class);
                        startActivity(intent);
                        break;
                    }
                    else if (i==list.size()-1){
                        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                        builder.setTitle("提示信息");
                        builder.setMessage("您输入的信息有误");
                        AlertDialog alertDialog=builder.create();
                        alertDialog.show();
                    }
                }
                t.setText(str3);
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changepassword(getActivity());
            }
        });
        bofang();
        return layout;
    }
    public void bofang(){
        handler.postDelayed(runnable,1000);
    }
    public Runnable runnable = new Runnable(){
        public void run(){
            handler.postDelayed(this,1000);
            imageView.setImageResource(Customer_login.image[i]);
            i++;
            if(i==9){
                i=0;
            }
        }
    };
    public void changepassword(Context context){
        final EditText editText=new EditText(getActivity());
        AlertDialog.Builder builder1=new AlertDialog.Builder(context);
        builder1.setTitle("重新输入密码");
        //editText.setId(0);
        builder1.setView(editText);
        str4=editText.getText().toString();

        builder1.setPositiveButton("确认",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(),editText.getText(),Toast.LENGTH_SHORT).show();
            }
        });
        builder1.setNegativeButton("重新",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                editText.setText(null);
            }
        });
        AlertDialog alertDialog1=builder1.create();
        alertDialog1.show();



    }

}
