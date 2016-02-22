package com.example.vollerydemo.custom.activity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.vollerydemo.R;
import com.example.vollerydemo.custom.MyGsonListBeanRequest;
import com.example.vollerydemo.custom.MyGsonListBeanRequest.ListBeanListener;
import com.example.vollerydemo.domain.JobType;
import com.example.vollerydemo.utils.LogUtils;

/**
 * 
 * @类名称: MyGsonBeanActivity
 * @类描述: TODO
 * @创建人：yzk
 * @创建时间：2014年10月30日 下午12:41:30
 * @备注：
 */
public class MyGsonListBeanActivity extends Activity implements OnClickListener {
	private Handler handle = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 1) {
				// if (info != null) {
				// tv_show.setText(sb.toString());
				// } else {
				// tv_show.setText("null");
				// }
			}
		};
	};

	private TextView tv_show;
	private Context mContext;
	private RequestQueue queue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gson_request);
		initView();
		initData();
	}

	private void initData() {
		mContext = MyGsonListBeanActivity.this;
		queue = Volley.newRequestQueue(mContext);
	}

	private void initView() {
		findViewById(R.id.button).setOnClickListener(this);
		tv_show = (TextView) findViewById(R.id.tv_show);
	}

	/*
	 * url
	 * 
	 * query=show_job_type&data={"type":"1"}
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button:
			getMyListBean();
			break;

		default:
			break;
		}
	}

	private String URL = "xxxxxxxxxxxxxxxxxx";

	private void getMyListBean() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("query", "show_job_type");
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("type", "1");
			params.put("data", jsonObject.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		ListBeanListener<JobType> listBeanListener = new ListBeanListener<JobType>() {

			@Override
			public void onsuccess(int result, List<JobType> data) {
				if (200 == result) {
					LogUtils.d_url("成功的返回");
					if (data != null) {
						for (JobType type : data) {
							LogUtils.d_url(type.toString());
						}
					}
				}
			}

			@Override
			public void onFilure(VolleyError error) {

			}
		};
		MyGsonListBeanRequest<JobType> myGsonListBeanRequest = new MyGsonListBeanRequest<JobType>(
				Method.POST, params, JobType.class, URL, listBeanListener);
		myGsonListBeanRequest.addRequestQueue(queue);

	}

}
