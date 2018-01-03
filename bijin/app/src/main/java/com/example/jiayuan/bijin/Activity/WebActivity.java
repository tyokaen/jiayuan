package com.example.jiayuan.bijin.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.jiayuan.bijin.R;

public class WebActivity extends AppCompatActivity {
WebView webView=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        webView=(WebView)findViewById(R.id.web1);
        String url=getIntent().getStringExtra("URL");
        if(url.length()>60)
            webView.loadDataWithBaseURL(null,url,"text/html","utf-8",null);
        else
        webView.loadUrl(url);
    }
}
