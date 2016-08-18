package com.example.jiayuan.library;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jiayuan on 2016/07/27.
 */
public class showFragment extends Fragment {
    List<Map<String,Object>> list;
    Map<String,Object> map;
    SimpleAdapter adapter;
    ListView L1;
    static int i;
    static int [] price={14,29,32,21,42,30,28,21,19,22};
    static String[] writer={"张三","张三","张三","张三","张三","张三","张三","张三","张三","张三"};
    static int[] image = {R.drawable.a2, R.drawable.a3, R.drawable.a4, R.drawable.a5, R.drawable.a6,
            R.drawable.a7, R.drawable.a8, R.drawable.a9, R.drawable.a10, R.drawable.a11};
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_bookshow, container, false);
        L1=(ListView)view.findViewById(R.id.ListView1);
        list = new ArrayList<Map<String, Object>>();
        String[] name = {"java核心技术", "head fist in java", "java语言程序设计", "java开发实战1200例", "java编程思想",
                "深入分析java web", "Effective java", "深入分析java虚拟机", "java并发实战", "java从入门到精通"
        };

        for (int i = 0; i < name.length; i++) {
            map = new HashMap<String, Object>();
            map.put("Image", image[i]);
            map.put("Title", name[i]);
            map.put("comment","非常好的一本java书");
            list.add(map);
        }
        String[] from={"Image","Title","comment"};
        int[] to={R.id.ImageView1,R.id.TextView12,R.id.TextView13};
        adapter=new SimpleAdapter(getActivity(),list,R.layout.simple_adapter_item,from,to);
        L1.setAdapter(adapter);
        L1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 showInfo(position);
            }
        });
        return view;
    }
    public void showInfo(final int position){
        ImageView img=new ImageView(getActivity());
        img.setImageResource(image[position]);
        new AlertDialog.Builder(getActivity()).setView(img)
                .setTitle("详情" + position)
                .setMessage("作者：" + writer[position] + "价格:" + price[position]+"元")
                .setPositiveButton("借阅", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getActivity(), borrowbook.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("购物车", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getActivity(), buybook.class);
                        startActivity(intent);
                        i = position;
                    }
                })
                .show();
    }
    }

