package com.example.vollerydemo.image;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.vollerydemo.R;

/**
 * 
 * @类名称: ImageResuqestActivity
 * @类描述: TODO
 * @创建人：yzk
 * @创建时间：2014年10月29日 下午2:24:05
 * @备注：imageRequest的使用
 */
public class ImageResuqestActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_request);
		initView();
		addListener();
		initData();
	}

	private void initData() {
	}

	private void addListener() {
	}

	private void initView() {
		findViewById(R.id.request_image).setOnClickListener(this);
		findViewById(R.id.request_image_loader).setOnClickListener(this);
		findViewById(R.id.request_network_image).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.request_image:
			startActivity(new Intent(ImageResuqestActivity.this,
					RequestImageActivity.class));
			break;
		case R.id.request_image_loader:
			startActivity(new Intent(ImageResuqestActivity.this,
					RequestImageLoaderActivity.class));
			break;
		case R.id.request_network_image:
			startActivity(new Intent(ImageResuqestActivity.this,
					RequestNetImageActivity.class));
			break;
		default:
			break;
		}

	}

}
