package com.example.jiayuan.bijin.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.example.jiayuan.bijin.R;

public class SplashScreen extends AppCompatActivity {
ImageView welcomeImg=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        welcomeImg=(ImageView)findViewById(R.id.Image_ani);
        AlphaAnimation anima = new AlphaAnimation(0.3f, 1.0f);
        anima.setDuration(2000);// 设置动画显示时间
        welcomeImg.startAnimation(anima);
        anima.setAnimationListener(new AnimationImpl());
    }
    private class AnimationImpl implements Animation.AnimationListener {
        @Override
        public void onAnimationStart(Animation animation) {
            welcomeImg.setBackgroundResource(R.drawable.launchimage);
        }
        @Override
        public void onAnimationEnd(Animation animation) {
            skip(); // 动画结束后跳转到别的页面
        }
        @Override
        public void onAnimationRepeat(Animation animation) {
        }
    }
    private void skip() {
        startActivity(new Intent(this, entrance.class));
        finish();
    }
    }

