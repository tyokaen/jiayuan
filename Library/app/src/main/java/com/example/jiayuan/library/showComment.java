package com.example.jiayuan.library;

import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

import User.Comment;
import User.CommentDao;

public class showComment extends Activity {
RatingBar r1;
    TextView t,t1;
ListView L;
    CommentDao dao=new CommentDao(showComment.this);
    ArrayList<Comment> list;
    ArrayList<String> list1;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_comment);
        t=(TextView) findViewById(R.id.TextView18);
        L=(ListView)findViewById(R.id.ListView5);
        t.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        r1=(RatingBar)findViewById(R.id.Ratingbar2);
       // t1=(TextView)findViewById(R.id.TextView19);
        list1=new ArrayList<String>();
       Bundle bundle=getIntent().getExtras();
        String str=bundle.getString("name");
        t.setText(str);
        float f=bundle.getFloat("Rating");
       // String str1=bundle.getString("content");
        r1.setRating(f);
       // t1.setText(str1);
       list=dao.find();
        for(int i=0;i<list.size();i++){
           list1.add("网友"+list.get(i).getComment());
        }
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list1);
        L.setAdapter(adapter);
    }
}
