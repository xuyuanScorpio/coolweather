package com.coolweather.app;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.coolweather.app.util.VolleySingleton;

/**
 * application类，应用级别的操作都在这里
 * @author xy
 *
 */
public class CoolApplication extends Application{

	private static RequestQueue mQueue;  //Volley请求队列
	
	@Override
	public void onCreate() {
		super.onCreate();
		mQueue = VolleySingleton.getVolleySingleton(this.getApplicationContext()).getRequestQueue();
	}
	
    /**
     * 获取volley的请求队列
     * @return  queue
     */
    public static RequestQueue getHttpQueue()
    {
        return mQueue;
    }
}
