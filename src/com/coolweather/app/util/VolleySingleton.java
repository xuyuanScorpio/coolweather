package com.coolweather.app.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Volley的单列工具类
 * 
 * @author xy
 * 
 */
public class VolleySingleton {

	private static VolleySingleton volleySingleton;
	private RequestQueue mRequstQueue;
	private ImageLoader mImageLoader;
	private Context mContext;

	public VolleySingleton(Context context) {
		this.mContext = context;
		mRequstQueue = getRequestQueue();
		mImageLoader = new ImageLoader(mRequstQueue,
				new ImageLoader.ImageCache() {

					private final LruCache<String, Bitmap> cache = new LruCache<String, Bitmap>(
							20);

					@Override
					public Bitmap getBitmap(String s) {
						return cache.get(s);
					}

					@Override
					public void putBitmap(String s, Bitmap bitmap) {
						cache.put(s, bitmap);
					}
				});
	}

	public static VolleySingleton getVolleySingleton(Context context) {
		if (volleySingleton == null) {
			synchronized (VolleySingleton.class) {
				if (volleySingleton == null) {
					volleySingleton = new VolleySingleton(context);
				}
			}
		}
		return volleySingleton;
	}

	public RequestQueue getRequestQueue() {
		if (mRequstQueue == null) {
			mRequstQueue = Volley.newRequestQueue(mContext
					.getApplicationContext());
		}

		return mRequstQueue;
	}

	public <T> void addToRequest(Request<T> req) {
		getRequestQueue().add(req);
	}

	public ImageLoader getmImageLoader() {
		return mImageLoader;
	}

}
