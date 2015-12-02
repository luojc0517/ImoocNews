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
    private ImageView mImageView;
    private String mUrl;
    private Bitmap mBitmap;

    public ImageLoader(LruCache<String, Bitmap> LruCache) {
        bitmapLruCache = LruCache;
    }

    public void loadImage(ImageView imageView, String Url) {
        mImageView = imageView;
        mUrl = Url;
        mBitmap = getBitmapFromCache(mUrl);
        if (mBitmap == null) {
            Log.d("jackie", "从网络获取图片" + mUrl);
            AsyncHttpClient asyncHttpClient = new AsyncHttpClient(mUrl);
            asyncHttpClient.get(new BitmapResponseHandler() {
                @Override
                public void onSuccess() {
                    mBitmap = getContent();
                }

                @Override
                public void onFinish() {
                    if (mImageView.getTag().equals(mUrl)) {
                        mImageView.setImageBitmap(mBitmap);
                    }
                    addBitmapToCache(mUrl, mBitmap);

                }
            });
        } else {
            Log.d("jackie", "从缓存获取图片" + mUrl);

            if (mImageView.getTag().equals(mUrl)) {
                mImageView.setImageBitmap(mBitmap);
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
