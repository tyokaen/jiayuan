package com.example.jiayuan.bijin.cache;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

/**
 * Created by jiayuan on 2017/11/29.
 */

public class BijinImageCache {
   private static  LruCache<String, Bitmap> lruCache=null;
    private static Bitmap bitmap;

    private BijinImageCache(){};
    public static LruCache<String, Bitmap> getInstance(){
        if(lruCache==null){
            lruCache=new LruCache<String, Bitmap>(1024*1024*70);
        }
        else return lruCache;
        return lruCache;
    }
    public static Bitmap getValueFromCache(String key){
        return getInstance().get(key);
    }
    public  static  void setValueToCache(String key,  Bitmap bitmap){
        getInstance().put(key,bitmap);
    }
}
