package com.example.jiayuan.bijin.Tools;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;

/**
 * Created by jiayuan on 2017/11/30.
 */

public class ToolsBitmap {
    private static ToolsBitmap toolsBitmap=null;
    private ToolsBitmap(){};
    public static ToolsBitmap getInstance(){
        if(toolsBitmap==null){
            toolsBitmap=new ToolsBitmap();

        }
        else return toolsBitmap;
        return toolsBitmap;
    }
    public Bitmap compressBitmap(byte[] bytes){
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inJustDecodeBounds=true;
        BitmapFactory.decodeByteArray(bytes,0,bytes.length,options);
        int height=options.outHeight;
        int width=options.outWidth;
        int inSampleSize = 2;
        int minLen = Math.min(height, width);
        if(minLen > 100) {
            float ratio = (float)minLen / 100.0f;
            inSampleSize = (int)ratio;
        }
        options.inJustDecodeBounds = false;
        options.inSampleSize = inSampleSize;
        Bitmap bm = BitmapFactory.decodeByteArray(bytes, 0,bytes.length,options);
        return bm;
    }
    public Bitmap ImageCropFromPress(Bitmap bm,boolean isRecycled){
        int w=bm.getWidth();
        int h=bm.getHeight();
        int wh=w>h?h:w;
        int retX=w>h?(w-h)/2:0;
        int rexY=w>h?0:(h-w)/2;
        Bitmap bitmap=Bitmap.createBitmap(bm,retX,rexY,wh,wh,null,false);
        if (isRecycled && bitmap != null && !bm.equals(bitmap)
                && !bitmap.isRecycled())
        {
            bitmap.recycle();
            bitmap = null;
        }
        return bitmap;

    }


    public Bitmap ImageCrop(byte[] bytes, boolean isRecycled){
        Bitmap bitmap=BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        int w=bitmap.getWidth();
        int h=bitmap.getHeight();
        int wh=w>h?h:w;
        int retX=w>h?(w-h)/2:0;
        int rexY=w>h?0:(h-w)/2;
        Bitmap bm=Bitmap.createBitmap(bitmap,retX,rexY,wh,wh,null,false);
        if (isRecycled && bitmap != null && !bitmap.equals(bm)
                && !bitmap.isRecycled())
        {
            bitmap.recycle();
            bitmap = null;
        }
      return bm;

    }
    public Bitmap getScaledBitmap(byte[] bytes,int targetSize){
      Bitmap bitmap=ImageCrop(bytes,false);
        int height=bitmap.getHeight();
        if(targetSize<=height)
            return bitmap;
        Matrix matrix=new Matrix();
            matrix.postScale((float) targetSize/height,(float)targetSize/height);
        Bitmap bm=Bitmap.createBitmap(bitmap,0,0,height,height, matrix,false);
        System.out.print("sdsdffffffffff"+height);
        return  bm;
    }
    public Bitmap yuanjiao(Bitmap bitmap,int pixels){
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        Bitmap creBitmap = Bitmap.createBitmap(width, height,
                android.graphics.Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(creBitmap);

        Paint paint = new Paint();
        float roundPx = pixels;
        RectF rectF = new RectF(0, 0, bitmap.getWidth() - pixels,
                bitmap.getHeight() - pixels);
        paint.setAntiAlias(true);

        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        canvas.drawBitmap(bitmap, 0, 0, paint);
        if (!bitmap.isRecycled())
            bitmap.recycle();
        return creBitmap;

    }

}
