package com.example.vollerydemo.custom.activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.vollerydemo.R;
import com.example.vollerydemo.custom.XMLRequest;
import com.example.vollerydemo.utils.LogUtils;

/**
 * 
 * @类名称: XmlRequestActivity
 * @类描述: TODO
 * @创建人：yzk
 * @创建时间：2014年10月29日 下午5:11:30
 * @备注：使用自定义的xmlrequest解析,xml数据,这里是获取中国所有的省份,这里把城市获取出来
 */
public class XmlRequestActivity extends Activity implements OnClickListener {
	private RequestQueue queue;
	private final String URL = "http://flash.weather.com.cn/wmaps/xml/china.xml";
	private List<String> citys;
	private ListView listview;
	private Handler handle = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 1) {
				listview.setAdapter(new ArrayAdapter<String>(
						XmlRequestActivity.this, R.layout.item_city, R.id.city,
						citys));
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_xml_request);
		initView();
		initData();
	}

	private void initData() {
		queue = Volley.newRequestQueue(XmlRequestActivity.this);
		citys = new ArrayList<String>();
	}

	private void initView() {
		findViewById(R.id.button).setOnClickListener(this);
		listview = (ListView) findViewById(R.id.listview);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button:
			getXml();
			break;

		default:
			break;
		}
	}

	private void getXml() {
		XMLRequest xmlRequest = new XMLRequest(URL,
				new Response.Listener<XmlPullParser>() {

					@Override
					public void onResponse(XmlPullParser response) {
						try {
							int eventType = response.getEventType();
							while (eventType != XmlPullParser.END_DOCUMENT) {
								switch (eventType) {
								case XmlPullParser.START_TAG:
									String name = response.getName();
									if ("city".equals(name)) {
										String quName = response
												.getAttributeValue(0);
										citys.add(quName);
										break;
									}
									break;

								}
								eventType = response.next();
							}
							Message message = new Message();
							message.what = 1;
							handle.sendMessage(message);
						} catch (XmlPullParserException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}

					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						if (!TextUtils.isEmpty(error.getMessage()))
							LogUtils.d_url(error.getMessage());
						Toast.makeText(getApplicationContext(), "获取失败", 0)
								.show();

					}
				});
		queue.add(xmlRequest);
	}
}
