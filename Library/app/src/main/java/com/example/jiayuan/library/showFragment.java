package com.example.jiayuan.library;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import User.Comment;
import User.CommentDao;

/**
 * Created by jiayuan on 2016/07/27.
 */
public class showFragment extends Fragment {
    List<Map<String,Object>> list;
    Map<String,Object> map;
    SimpleAdapter adapter;
    ArrayAdapter adapter1;
    ArrayList<String> arrayList;
    ListView L1;
    static int i;
    float f;
    CommentDao dao=new CommentDao(getActivity());
    AutoCompleteTextView autoCompleteTextView;
    String[] name = {"java核心技术", "head fist in java", "java语言程序设计", "java开发实战1200例", "java编程思想",
            "深入分析java web", "Effective java", "深入分析java虚拟机", "java并发实战", "java从入门到精通"
    };
    static int [] price={14,29,32,21,42,30,28,21,19,22};
    static String[] writer={"张三","张三","张三","张三","张三","张三","张三","张三","张三","张三"};
    static int[] image = {R.drawable.a2, R.drawable.a3, R.drawable.a4, R.drawable.a5, R.drawable.a6,
            R.drawable.a7, R.drawable.a8, R.drawable.a9, R.drawable.a10, R.drawable.a11};
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_bookshow, container, false);
        View view1=inflater.inflate(R.layout.autotext,container,false);
        L1=(ListView)view.findViewById(R.id.ListView1);
        autoCompleteTextView=(AutoCompleteTextView)view1.findViewById(R.id.Auto1);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                builder.setTitle("提示信息");
                builder.setMessage("您点击的是:"+name[position]);

                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setPositiveButton("去瞧瞧", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                             Intent intent=new Intent(getActivity(),book_info.class);
                             startActivity(intent);
                    }
                });
                final AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }

        });
        dao=new CommentDao(getActivity());
        arrayList=new ArrayList<String>();
        for(int i=0;i<name.length;i++){
            arrayList.add(name[i]);
        }
        adapter1=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,arrayList);
        autoCompleteTextView.setAdapter(adapter1);
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
        L1.addHeaderView(view1);
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
                .setPositiveButton("仔细瞅瞅", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getActivity(), book_info.class);
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
                .setNeutralButton("去评价", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                          LayoutInflater inflater=(LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                         final  View layout=inflater.inflate(R.layout.rating,(ViewGroup)getActivity().findViewById(R.id.LinearLayout5));
                          AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                          builder.setTitle("进行评级");
                          builder.setView(layout);
                          final AlertDialog alertDialog=builder.create();
                          alertDialog.show();
                        final RatingBar ratingBar=(RatingBar)layout.findViewById(R.id.Ratingbar);
                        final TextView t=(TextView) layout.findViewById(R.id.TextView15);
                        final Button b=(Button)layout.findViewById(R.id.Button13);
                        final Button b1=(Button)layout.findViewById(R.id.Button14);
                        b1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                alertDialog.cancel();
                            }
                        });
                        b.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                              LayoutInflater inflater1=(LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                final View layout=inflater1.inflate(R.layout.comment,(ViewGroup)getActivity().findViewById(R.id.LinearLayout6));
                                final Button b=(Button) layout.findViewById(R.id.Button15);
                                final EditText e=(EditText)layout.findViewById(R.id.EditText7);
                                b.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        String str=e.getText().toString();
                                        dao.add(new Comment(str));
                                        Intent intent=new Intent(getActivity(),showComment.class);
                                        intent.putExtra("name",name[position]);
                                        intent.putExtra("Rating",f);
                                        intent.putExtra("content",str);
                                        startActivity(intent);
                                    }
                                });
                                AlertDialog.Builder builder1=new AlertDialog.Builder(getActivity());
                                builder1.setTitle("说点什么");
                                builder1.setView(layout);
                                final AlertDialog alertDialog=builder1.create();
                                alertDialog.show();
                            }
                        });
                        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                            @Override
                            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                                f=rating;
                                if(rating==1) {
                                    t.setText("很差");
                                }
                                else if(rating==2){
                                    t.setText("差");
                                }
                                else if(rating==3){
                                    t.setText("一般");
                                }
                                else if(rating==4){
                                    t.setText("良好");
                                }
                                else if(rating==5){
                                    t.setText("很好");
                                }
                            }

                        });

                    }
                })
                .show();
    }
    }

