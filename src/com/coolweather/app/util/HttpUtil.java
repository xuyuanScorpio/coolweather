package com.coolweather.app.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {
	
	public static void sendHttpRequest(final String address, final HttpCallbackListener listener){
		
		new Thread(new Runnable() {
			HttpURLConnection connection = null;
						
			@Override
			public void run() {
				
				try {
					URL url = new URL(address);
					connection = (HttpURLConnection) url.openConnection();
					connection.setRequestMethod("GET");
					connection.setConnectTimeout(8000);
					connection.setReadTimeout(8000);
					InputStream in = connection.getInputStream();
					BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
					StringBuilder response = new StringBuilder();
					String line;
					while((line = reader.readLine()) != null){
						response.append(line);
					}
					
					System.out.println("返回数据：" + response);
					
					if (listener != null) {
						// 回调onFinish()方法
						listener.onFinish(response.toString());
					}
					in.close();
					reader.close();
					
				} catch (Exception e) {
					if (listener != null) {
						// 回调onError()方法
						listener.onError(e);
					}
				}finally {
					
					if (connection != null) {
						connection.disconnect();
					}
				}
				
				
			}
		}).start();
		
	}

}
