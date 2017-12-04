package com.example.jiayuan.bijin.Tools;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

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
    public Bitmap getScaledBitmap(byte[] bytes){
      Bitmap bitmap=compressBitmap(bytes);
      int bitmapWidth=bitmap.getWidth();
      int bitmapHeight=bitmap.getHeight();
        Matrix matrix=new Matrix();
        if(bitmapHeight>bitmapWidth){
            matrix.postScale((float) bitmapHeight/bitmapWidth,1.0f);
        }
        else if(bitmapHeight<bitmapWidth){
            matrix.postScale(1.0f,(float)bitmapWidth/bitmapHeight);
        }
        Bitmap bm=Bitmap.createBitmap(bitmap,0,0,bitmapWidth,bitmapHeight, matrix,false);
        return  bm;
    }

}
