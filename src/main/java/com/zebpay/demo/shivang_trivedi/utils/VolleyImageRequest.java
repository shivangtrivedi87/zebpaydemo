package com.zebpay.demo.shivang_trivedi.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;


/**
 * Created by vcollabera-st on 10-07-2017.
 */



public class VolleyImageRequest {

    private static VolleyImageRequest customVolleyRequest;
    private static Activity context;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;


    private VolleyImageRequest(Activity context) {
        this.context = context;
        this.requestQueue = getRequestQueue();

        imageLoader = new ImageLoader(requestQueue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap>
                            cache = new LruCache<String, Bitmap>(20);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });
    }

    public static synchronized VolleyImageRequest getInstance(Activity context) {
        if (customVolleyRequest == null) {
            customVolleyRequest = new VolleyImageRequest(context);
        }
        return customVolleyRequest;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            Cache cache = new DiskBasedCache(context.getCacheDir(), 10 * 1024 * 1024);
            Network network = new BasicNetwork(new HurlStack());
            //requestQueue = new RequestQueue(cache, network);
            requestQueue =  Volley.newRequestQueue(context);
            requestQueue.start();
        }
        return requestQueue;
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }

}