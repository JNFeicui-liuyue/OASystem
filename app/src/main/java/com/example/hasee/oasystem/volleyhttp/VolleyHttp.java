package com.example.hasee.oasystem.volleyhttp;

import android.content.Context;
import android.widget.ImageView;

import com.example.hasee.oasystem.R;
import com.example.hasee.oasystem.volley.RequestQueue;
import com.example.hasee.oasystem.volley.Response.ErrorListener;
import com.example.hasee.oasystem.volley.Response.Listener;
import com.example.hasee.oasystem.volley.toolbox.ImageLoader;
import com.example.hasee.oasystem.volley.toolbox.ImageLoader.ImageCache;
import com.example.hasee.oasystem.volley.toolbox.ImageLoader.ImageListener;
import com.example.hasee.oasystem.volley.toolbox.MultiPosttRequest;
import com.example.hasee.oasystem.volley.toolbox.StringRequest;
import com.example.hasee.oasystem.volley.toolbox.Volley;

import java.io.File;

public class VolleyHttp {

	public static RequestQueue mQueue;
	Context context;

	public VolleyHttp(Context context) {
		if (mQueue == null) {
			mQueue = Volley.newRequestQueue(context);
		}
		this.context = context;
	}

	public void getJSONObject(String url, Listener<String> listener,
							  ErrorListener errorListener) {
		StringRequest request = new StringRequest(url, listener, errorListener);
		mQueue.add(request);
	}

	public void addImage(String url, ImageCache imageCache, ImageView iv) {
		ImageLoader mImageLoader = new ImageLoader(mQueue, imageCache);
		ImageListener listener = ImageLoader.getImageListener(iv,
				R.drawable.tuijian, android.R.drawable.ic_delete);
		mImageLoader.get(url, listener);
	}

	

	public void upLoadImage(String url, File file, Listener<String> listener,
							ErrorListener errorListener) {
		MultiPosttRequest request = new MultiPosttRequest(url, listener,
				errorListener);
		request.buildMultipartEntity("portrait", file);
		mQueue.add(request);
	}

	public void addUserString(String url, String token, String imei,
							  Listener<String> listener, ErrorListener errorListener) {
		MultiPosttRequest request = new MultiPosttRequest(url, listener,
				errorListener);
		request.buildMultipartEntity("token", token);
		request.buildMultipartEntity("imei", imei);
		request.buildMultipartEntity("ver", 1 + "");
		mQueue.add(request);
	}

}
