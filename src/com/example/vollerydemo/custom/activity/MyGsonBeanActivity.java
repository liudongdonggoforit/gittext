package com.example.vollerydemo.custom.activity;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.vollerydemo.R;
import com.example.vollerydemo.custom.MyGsonBeanRequest;
import com.example.vollerydemo.custom.jsonutils.exampledomain.JobDetail;
import com.example.vollerydemo.utils.LogUtils;

/**
 * 
 * @类名称: MyGsonBeanActivity
 * @类描述: TODO
 * @创建人：yzk
 * @创建时间：2014年10月30日 下午12:41:30
 * @备注：
 */
public class MyGsonBeanActivity extends Activity implements OnClickListener {
	private RequestQueue queue;
	private JobDetail info;
	private StringBuffer sb = new StringBuffer();
	private Handler handle = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 1) {
				if (info != null) {
					sb.append(info.toString());
					if (info.getCompany_res() != null) {
						sb.append("\n\n       　　　　　　　　");
						sb.append(info.getCompany_res().toString());
					}
					tv_show.setText(sb.toString());
				} else {
					tv_show.setText("null");
				}
			}
		};
	};

	private TextView tv_show;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gson_request);
		initView();
		initData();
	}

	private void initData() {
		queue = Volley.newRequestQueue(MyGsonBeanActivity.this);
	}

	private void initView() {
		findViewById(R.id.button).setOnClickListener(this);
		tv_show = (TextView) findViewById(R.id.tv_show);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button:
			// getGson();
			getGg();
			break;

		default:
			break;
		}
	}

	private String UU = "XXXXXXXXXXXXXXX";

	// query=job_details&data={"uid":"93","rid":"25"}
	private void getGg() {
		MyGsonBeanRequest<JobDetail> gsonRequest = new MyGsonBeanRequest<JobDetail>(
				Method.POST, UU, JobDetail.class,
				new Response.Listener<JobDetail>() {

					@Override
					public void onResponse(JobDetail response) {
						LogUtils.d_url(response.toString());
						info = response;
						Message message = new Message();
						message.what = 1;
						handle.sendMessage(message);
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						LogUtils.d_url("我的gsonbean失败");
					}
				}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> map = new HashMap<String, String>();
				map.put("query", "job_details");
				JSONObject ob = new JSONObject();
				try {
					ob.put("uid", "1000000000");
					ob.put("rid", "2500000000000000");
				} catch (JSONException e) {
					e.printStackTrace();
				}
				map.put("data", ob.toString());
				return map;
			}
		};
		queue.add(gsonRequest);
	}
}
