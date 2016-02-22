package com.example.vollerydemo.custom.activity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.vollerydemo.R;
import com.example.vollerydemo.custom.GsonRequest;
import com.example.vollerydemo.domain.JsonData;
import com.example.vollerydemo.domain.JobType;
import com.example.vollerydemo.domain.Weather;
import com.example.vollerydemo.domain.WeatherInfo;
import com.example.vollerydemo.utils.LogUtils;
import com.google.gson.JsonObject;

/**
 * 
 * @类名称: GsonRequestActivity
 * @类描述: TODO
 * @创建人：杨增坤
 * @创建时间：2014年10月29日 下午6:18:36
 * @备注：解析Gson
 */
public class GsonRequestActivity extends Activity implements OnClickListener {
	private RequestQueue queue;
	private WeatherInfo info;
	private String key = "44a581bd755b5c2b137d88c7ce9fc58c";
	private final String URL = "http://v.juhe.cn/weather/index?format=2&cityname=%E8%8B%8F%E5%B7%9E&key=44a581bd755b5c2b137d88c7ce9fc58c";
	private Handler handle = new Handler() {
		@Override
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 1) {
				if (info != null) {
					tv_show.setText(info.toString());
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
		queue = Volley.newRequestQueue(GsonRequestActivity.this);
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

	/*
	 * {"weatherinfo":{"city":"北京","cityid":"101010100","temp":"19","WD":"南风","WS"
	 * :"2级","SD":"43%","WSE":"2","time":"19:45","isRadar":"1","Radar":
	 * "JC_RADAR_AZ9010_JB"}}
	 */
	private void getGson() {
		GsonRequest<Weather> xmlRequest = new GsonRequest<Weather>(URL + key,
				Weather.class, new Response.Listener<Weather>() {

					@Override
					public void onResponse(Weather response) {
						info = response.getWeatherinfo();
						Message message = new Message();
						message.what = 1;
						handle.sendMessage(message);
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						Toast.makeText(GsonRequestActivity.this, "请求失败", 0)
								.show();

					}
				});
		queue.add(xmlRequest);
	}

	private String UU = "xxxxxxxxxxxxxxxxx";
	

	// query=show_job_type&data={"type":"1"}
	private void getGg() {
		
		/*GsonRequest<JsonData>gson2 = new GsonRequest<JsonData>(Method.POST, URL, JsonData.class, new Response.Listener<JsonData>() {

			@Override
			public void onResponse(JsonData response) {
				// TODO Auto-generated method stub
				
			}
		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				
			}
		}){
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String,String> map = new HashMap<String, String>();
				map.put("quesy", "quesy");
				JSONObject obj = new JSONObject();
				try {
					obj.put("Key", "1");
				} catch (Exception e) {
					e.printStackTrace();
				}
				map.put("data", obj.toString());
				return map;
			}
		};*/
		GsonRequest<JsonData> gson1 = new GsonRequest<JsonData>(Method.POST,
				UU, JsonData.class, new Response.Listener<JsonData>() {
					@Override
					public void onResponse(JsonData response) {
						LogUtils.d_url(response.getResult() + "");
						List<JobType> data = response.getData();
						for (JobType t : data) {
							LogUtils.d_url(t.toString());
						}
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						LogUtils.d_url("失败");

					}
				}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> map = new HashMap<String, String>();
				map.put("query", "show_job_type");
				// JsonObject ob = new JsonObject();
				// ob.addProperty("type", "1");
				JSONObject ob = new JSONObject();
				try {
					ob.put("type", "1");
				} catch (JSONException e) {
					e.printStackTrace();
				}
				map.put("data", ob.toString());
				return map;
			}
		};
		queue.add(gson1);
	}
}
