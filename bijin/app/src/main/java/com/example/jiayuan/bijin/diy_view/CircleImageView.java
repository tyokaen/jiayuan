package com.example.jiayuan.bijin.diy_view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by jiayuan on 2017/07/28.
 */

public class CircleImageView extends ImageView {
    private int mWidth;
    private int mHeight;
    public CircleImageView(Context context){
        super(context);

    }
    public CircleImageView(Context context,AttributeSet attributeSet){
        super(context,attributeSet,0);
    }
    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    protected void onMeasure(int widthMeasureSpec,int heightMeasureSpec){
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        mWidth=getMeasuredWidth();
        mHeight=getMeasuredHeight();
    }
    protected void onDraw(Canvas canvas){
        BitmapShader shader=new BitmapShader(getBitMapFromDrawable(getDrawable()), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        Paint paint=new Paint();
        paint.setShader(shader);
        // RectF rectF=new RectF(0,0,mWidth,mHeight );
        canvas.drawCircle(mWidth/2,mHeight/2,mWidth/3,paint);
    }
    private Bitmap getBitMapFromDrawable(Drawable drawable){
        Bitmap bitmap=Bitmap.createBitmap(drawable.getIntrinsicWidth(),drawable.getMinimumHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(bitmap);
        drawable.draw(canvas);
        return bitmap;
    }
}
