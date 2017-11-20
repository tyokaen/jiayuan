package com.example.jiayuan.bijin.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.jiayuan.bijin.diy_view.GalleryFlow;

/**
 * Created by jiayuan on 2017/08/06.
 */

public class ImageAdapter extends BaseAdapter

{

    int mGalleryItemBackground;

    private Context mContext;

    private Integer[] mImageIds;

    private ImageView[] mImages;

    public ImageAdapter(Context c, Integer[] ImageIds) {

        mContext = c;

        mImageIds = ImageIds;

        mImages = new ImageView[mImageIds.length];

    }

    public boolean createReflectedImages() {
        final int reflectionGap = 4;
        int index = 0;
        for (int imageId : mImageIds) {

            Bitmap originalImage = BitmapFactory.decodeResource(mContext.getResources(), imageId);
            int width = originalImage.getWidth();
            int height = originalImage.getHeight();
            Matrix matrix = new Matrix();
            matrix.preScale(1, -1);
            Bitmap reflectionImage = Bitmap.createBitmap(originalImage, 0, height/2 , width, height/2 , matrix, false);
            Bitmap bitmapWithReflection = Bitmap.createBitmap(width, (height + height / 2), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmapWithReflection);
            canvas.drawBitmap(originalImage, 0, 0, null);

            Paint deafaultPaint = new Paint();
            canvas.drawRect(0, height, width, height + reflectionGap, deafaultPaint);
            canvas.drawBitmap(reflectionImage, 0, height + reflectionGap, null);
            Paint paint = new Paint();
            LinearGradient shader = new LinearGradient(0, originalImage.getHeight(), 0, bitmapWithReflection.getHeight() + reflectionGap, 0x70ffffff, 0x00ffffff, Shader.TileMode.CLAMP);

            paint.setShader(shader);

            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));

            canvas.drawRect(0, height, width, bitmapWithReflection.getHeight()

                    + reflectionGap, paint);

            ImageView imageView = new ImageView(mContext);

            imageView.setImageBitmap(bitmapWithReflection);

            imageView.setLayoutParams(new GalleryFlow.LayoutParams(600, 720));

            mImages[index++] = imageView;

        }
        return true;
    }
    public int getCount() {
        return mImageIds.length;
    }
    public Object getItem(int position) {
        return position;
    }
    public long getItemId(int position) {
        return position;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        return mImages[position];
    }
    public float getScale(boolean focused, int offset) {
        return Math.max(0, 1.0f / (float) Math.pow(2, Math.abs(offset)));
    }
}
