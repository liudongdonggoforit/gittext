package com.example.vollerydemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.vollerydemo.custom.activity.GsonRequestActivity;
import com.example.vollerydemo.custom.activity.MyGsonBeanActivity;
import com.example.vollerydemo.custom.activity.MyGsonListBeanActivity;
import com.example.vollerydemo.custom.activity.XmlRequestActivity;
import com.example.vollerydemo.image.ImageResuqestActivity;

/**
 * 
 * @类名称: FunctionActivity
 * @类描述: TODO
 * @创建人：yzk
 * @创建时间：2014年10月29日 上午11:02:54
 * @备注：功能列表
 */
public class FunctionActivity extends Activity implements OnClickListener {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_function);
		initView();
	}

	private void initView() {
		findViewById(R.id.base_post).setOnClickListener(this);
		findViewById(R.id.base_json).setOnClickListener(this);
		findViewById(R.id.base_image).setOnClickListener(this);
		findViewById(R.id.base_xml).setOnClickListener(this);
		findViewById(R.id.base_gson).setOnClickListener(this);
		findViewById(R.id.base_gson_mybean).setOnClickListener(this);
		findViewById(R.id.base_gson_mylistbean).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.base_post:
			startActivity(new Intent(FunctionActivity.this,
					StringRequestActivity.class));
			break;
		case R.id.base_json:
			startActivity(new Intent(FunctionActivity.this,
					JsonResuqestActivity.class));
			break;
		case R.id.base_image:
			startActivity(new Intent(FunctionActivity.this,
					ImageResuqestActivity.class));
			break;
		case R.id.base_xml:
			startActivity(new Intent(FunctionActivity.this,
					XmlRequestActivity.class));
			break;
		case R.id.base_gson:
			startActivity(new Intent(FunctionActivity.this,
					GsonRequestActivity.class));
		case R.id.base_gson_mybean:
			startActivity(new Intent(FunctionActivity.this,
					MyGsonBeanActivity.class));
		case R.id.base_gson_mylistbean:
			startActivity(new Intent(FunctionActivity.this,
					MyGsonListBeanActivity.class));
			break;

		default:
			break;
		}

	}
}
