package com.example.vollerydemo;

import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

/**
 * 
 * @类名称: JsonResuqestActivity
 * @类描述: TODO
 * @创建人：yzk
 * @创建时间：2014年10月29日 上午11:55:48
 * @备注：JsonResuqest的使用
 */
public class JsonResuqestActivity extends Activity implements OnClickListener {
	private Button json;
	private Button json_array;
	private TextView tv_show;
	private RequestQueue mQueue = null;
	private final String URL = "http://v.juhe.cn/weather/index?format=2&cityname=%E8%8B%8F%E5%B7%9E&key=44a581bd755b5c2b137d88c7ce9fc58c";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_json_request);
		initView();
		addListener();
		initData();
	}

	private void initData() {
		mQueue = Volley.newRequestQueue(JsonResuqestActivity.this);// 请求队列
	}

	private void addListener() {
		json.setOnClickListener(this);
		json_array.setOnClickListener(this);
	}

	private void initView() {
		json = (Button) findViewById(R.id.json);
		json_array = (Button) findViewById(R.id.json_array);
		tv_show = (TextView) findViewById(R.id.tv_show);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.json:
			json();
			break;
		case R.id.json_array:
			jsonarray();
			break;

		default:
			break;
		}

	}

	public void jsonarray() {

		// 第一个参数:请求的url,第二个参数:请求成功的监听,第三个参数:请求失败的监听
		JsonObjectRequest stringRequest = new JsonObjectRequest(URL, null,
				new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						tv_show.setText(response.toString());
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						tv_show.setText(error.getMessage());
					}
				});
		mQueue.add(stringRequest);

	}

	private void json() {
		// 第一个参数:请求的url,第二个参数:请求成功的监听,第三个参数:请求失败的监听
		JsonObjectRequest stringRequest = new JsonObjectRequest(URL, null,
				new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						tv_show.setText(response.toString());
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
