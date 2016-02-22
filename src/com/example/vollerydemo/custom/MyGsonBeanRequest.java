package com.example.vollerydemo.custom;

import java.io.UnsupportedEncodingException;

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.example.vollerydemo.custom.jsonutils.JsonParser;
import com.example.vollerydemo.custom.jsonutils.ObjectBean;

/**
 * 
 * @类名称: MyGsonRequest
 * @类描述: TODO
 * @创建人：yzk
 * @创建时间：2014年10月30日 下午12:03:28
 * @备注：
 */
public class MyGsonBeanRequest<T> extends Request<T> {
	private Response.Listener<T> mListener;
	private Class<T> mClazz;
	public MyGsonBeanRequest(String url, Class<T> claszz,
			Response.Listener<T> listener, ErrorListener errorListener) {
		this(Method.GET, url, claszz, listener, errorListener);
	}
	public MyGsonBeanRequest(int method, String url, Class<T> claszz,
			Response.Listener<T> listener, ErrorListener errorListener) {
		super(method, url, errorListener);
		this.mListener = listener;
		this.mClazz = claszz;
	}
	@Override
	protected Response<T> parseNetworkResponse(NetworkResponse response) {
		try {
			String jsonString = new String(response.data,
					HttpHeaderParser.parseCharset(response.headers));
			JsonParser jsonParser = new JsonParser();
			Log.d("MyGsonBeanRequest", jsonString);
			ObjectBean<T> json2Bean = jsonParser.json2Bean(jsonString, mClazz);
			return Response.success(json2Bean.getData(),
					HttpHeaderParser.parseCacheHeaders(response));
		} catch (UnsupportedEncodingException e) {
			return Response.error(new ParseError(e));
		}
	}

	@Override
	protected void deliverResponse(T response) {
		mListener.onResponse(response);
	}

}
