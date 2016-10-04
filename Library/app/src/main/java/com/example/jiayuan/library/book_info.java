package com.example.jiayuan.library;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;

public class book_info extends Activity {
    ViewPager pager = null;
    PagerTabStrip tabStrip = null;
    ArrayList<View> viewContainer = new ArrayList<View>();
    ArrayList<String> TitleContainer = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);
        pager = (ViewPager) this.findViewById(R.id.viewpager);
        tabStrip = (PagerTabStrip) this.findViewById(R.id.tabstrip);
        //取消tab下面的长横线
        tabStrip.setDrawFullUnderline(false);
        //设置tab的背景色
        tabStrip.setBackgroundColor(this.getResources().getColor(R.color.white));
        //设置当前tab页签的下划线颜色
        tabStrip.setTabIndicatorColor(this.getResources().getColor(R.color.red));
        tabStrip.setTextSpacing(200);
        View view = LayoutInflater.from(this).inflate(R.layout.tab1, null);
        View view1 = LayoutInflater.from(this).inflate(R.layout.tab2, null);
        View view2 = LayoutInflater.from(this).inflate(R.layout.tab3, null);
        viewContainer.add(view);
        viewContainer.add(view1);
        viewContainer.add(view2);
        TitleContainer.add("商品");
        TitleContainer.add("详情");
        TitleContainer.add("评论");
        pager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return viewContainer.size();
            }

            @Override
            public void startUpdate(View view) {

            }

            @Override
            public Object instantiateItem(View view, int i) {
                ((ViewPager) view).addView(viewContainer.get(i), 0);
                return viewContainer.get(i);

            }

            @Override
            public void destroyItem(View view, int i, Object o) {
                ((ViewPager) view).removeView(viewContainer.get(i));
            }

            @Override
            public void finishUpdate(View view) {

            }

            @Override
            public boolean isViewFromObject(View view, Object o) {
                return view==o;
            }

            @Override
            public Parcelable saveState() {
                return null;
            }

            @Override
            public void restoreState(Parcelable parcelable, ClassLoader classLoader) {

            }
            public CharSequence getPageTitle(int position) {
                return TitleContainer.get(position);
            }
        });

    }

}