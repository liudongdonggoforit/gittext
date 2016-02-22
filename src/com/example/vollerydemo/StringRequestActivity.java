package com.example.vollerydemo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.vollerydemo.utils.LogUtils;

/**
 * 
 * @类名称: Base_Post_Activity
 * @类描述: TODO
 * @创建人：yzk
 * @创建时间：2014年10月29日 上午11:02:21
 * @备注：基本请求展示,StringRequest的使用
 */
public class StringRequestActivity extends Activity implements OnClickListener {
	private EditText et_text_get;
	private EditText et_text_post;
	private Button post;
	private Button get;
	private TextView tv_show;
	private RequestQueue mQueue = null;
	private final String URL_POST = "http://www.baidu.com/s";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_string_request);
		initView();
		addListener();
		initData();
	}

	private void initData() {
		mQueue = Volley.newRequestQueue(StringRequestActivity.this);// 请求队列
		et_text_get.setText("http://www.baidu.com");
	}

	private void addListener() {
		post.setOnClickListener(this);
		get.setOnClickListener(this);
	}

	private void initView() {
		et_text_get = (EditText) findViewById(R.id.et_text_get);
		et_text_post = (EditText) findViewById(R.id.et_text_post);
		post = (Button) findViewById(R.id.post);
		get = (Button) findViewById(R.id.get);
		tv_show = (TextView) findViewById(R.id.tv_show);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.get:
			get();
			break;
		case R.id.post:
			post();
			break;

		default:
			break;
		}

	}

	public void post() {
		// post请求:传入参数,需要重写StringRequest的getParams方法
		// 访问http://www.baidu.com/s?wd=输入的内容
		final String keywords = et_text_post.getText().toString().trim();
		StringRequest stringRequest = new StringRequest(URL_POST,
				new Response.Listener<String>() {

					@Override
					public void onResponse(String response) {
						tv_show.setText(response);
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						tv_show.setText(error.getMessage());
					}
				}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> map = new HashMap<String, String>();
				map.put("wd", keywords);
				return map;
			}
		};
		LogUtils.d_url("post=" + stringRequest.getUrl());
		try {
			Map<String, String> headers = stringRequest.getHeaders();
			Iterator<String> iterator = headers.keySet().iterator();
			while (iterator.hasNext()) {
				String key = iterator.next();
				LogUtils.d_url(key + ":" + headers.get(key));
			}

		} catch (AuthFailureError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mQueue.add(stringRequest);
	}

	private void get() {
		String url = null;
		url = et_text_get.getText().toString().trim();
		// 第一个参数:请求的url,第二个参数:请求成功的监听,第三个参数:请求失败的监听
		StringRequest stringRequest = new StringRequest(url,
				new Response.Listener<String>() {

					@Override
					public void onResponse(String response) {
						tv_show.setText(response);
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						tv_show.setText(error.getMessage());
					}
				});
		mQueue.add(stringRequest);
	}

}
