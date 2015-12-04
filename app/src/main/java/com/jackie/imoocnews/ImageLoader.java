package com.jackie.imoocnews;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;

import net.callumtaylor.asynchttp.AsyncHttpClient;
import net.callumtaylor.asynchttp.response.BitmapResponseHandler;

/**
 * Created by Law on 2015/12/1.
 */
public class ImageLoader {
    private LruCache<String, Bitmap> bitmapLruCache;

    public ImageLoader() {
        //获取最大内存
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        //获取可用与图片缓存的内存
        int cacheSize = maxMemory / 4;
        bitmapLruCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount() / 1024;
            }
        };
    }

    public void loadImage(final ImageView imageView, final String Url) {
        Bitmap bitmap = getBitmapFromCache(Url);
        if (bitmap == null) {
            Log.d("jackie", "从网络获取图片" + Url);
            AsyncHttpClient asyncHttpClient = new AsyncHttpClient(Url);
            asyncHttpClient.get(new BitmapResponseHandler() {

                @Override
                public void onSuccess() {

                }

                @Override
                public void onFinish() {
                    if (imageView.getTag().equals(Url)) {
                        imageView.setImageBitmap(getContent());
                    }
                    addBitmapToCache(Url, getContent());

                }
            });
        } else {
            Log.d("jackie", "从缓存获取图片" + Url);

            if (imageView.getTag().equals(Url)) {
                imageView.setImageBitmap(bitmap);
            }
        }

    }


    /*
        向缓存中存入图片
     */
    public void addBitmapToCache(String Url, Bitmap bitmap) {
        if (getBitmapFromCache(Url) == null) {
            bitmapLruCache.put(Url, bitmap);
        }
    }

    /*
       从缓存中获取图片
    */
    public Bitmap getBitmapFromCache(String Url) {
        return bitmapLruCache.get(Url);
    }


}
