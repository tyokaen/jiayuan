package com.example.jiayuan.bijin.diy_view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by jiayuan on 2017/11/22.
 */

public class MyScrollView extends ScrollView {

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }
    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context) {
        super(context);
    }

    private ScrollViewListener scrollViewListener = null;

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }


    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        //super.onScrollChanged(x, y, oldx, oldy);
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged1(x, y, oldx, oldy);
        }
    }

    public interface ScrollViewListener {
        void onScrollChanged1(int x, int y, int oldx,int oldy);
    }

}
