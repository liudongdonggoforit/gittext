package com.example.vollerydemo.custom;

import java.util.List;
import java.util.Map;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.vollerydemo.custom.jsonutils.JsonParser;
import com.example.vollerydemo.custom.jsonutils.ObjectListBean;

/**
 * 
 * @类名称: MyGsonListBeanRequest
 * @类描述: TODO
 * @创建人：yzk
 * @创建时间：2014年10月30日 下午12:36:36
 * @备注：
 */
public class MyGsonListBeanRequest<T> {
	private StringRequest stringRequest;

	/**
	 * 
	 * @param clazz解析的List
	 *            <T>的泛型
	 * @param url请求的url
	 * @param listBeanListener请求结果的监听
	 */
	public MyGsonListBeanRequest(final Class<T> clazz, String url,
			final ListBeanListener<T> listBeanListener) {
		this(Method.GET, null, clazz, url, listBeanListener);
	}

	/**
	 * 
	 * @param method请求方式
	 * @param params请求翻出
	 *            ,为Map<String,String>
	 * @param clazz解析的List
	 *            <T>的泛型
	 * @param url请求的地址
	 * @param listBeanListener请求结果的监听
	 */
	public MyGsonListBeanRequest(int method, final Map<String, String> params,
			final Class<T> clazz, String url,
			final ListBeanListener<T> listBeanListener) {
		stringRequest = new StringRequest(method, url,
				new Response.Listener<String>() {

					@Override
					public void onResponse(String response) {
						JsonParser jsonParser = new JsonParser();
						ObjectListBean<T> json2List = jsonParser.json2List(
								response, clazz);
						int result = json2List.getResult();
						List<T> data = json2List.getData();
						if (listBeanListener != null)
							listBeanListener.onsuccess(result, data);
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						if (listBeanListener != null)
							listBeanListener.onFilure(error);
					}
				}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				if (params != null) {
					return params;
				}
				return super.getParams();
			}
		};

	}

	public void addRequestQueue(RequestQueue requestQueue) {
		if (requestQueue != null && stringRequest != null)
			requestQueue.add(stringRequest);
	}

	public interface ListBeanListener<T> {
		void onsuccess(int result, List<T> data);

		void onFilure(VolleyError error);
	}
}
