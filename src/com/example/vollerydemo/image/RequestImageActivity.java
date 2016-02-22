package com.example.vollerydemo.image;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.example.vollerydemo.R;

/**
 * 
 * @类名称: RequestImageActivity
 * @类描述: TODO
 * @创建人：yzk
 * @创建时间：2014年10月29日 下午2:50:39
 * @备注：ImageRequest的使用
 */
public class RequestImageActivity extends Activity implements OnClickListener {
	private ImageView image;
	private RequestQueue queue;
	private final String IMAGE_URL = "http://d.hiphotos.baidu.com/image/pic/item/b21bb051f81986189d6f84ff49ed2e738bd4e6a5.jpg";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image);
		initView();
		initData();
	}

	private void initData() {
		queue = Volley.newRequestQueue(RequestImageActivity.this);
	}

	private void initView() {
		image = (ImageView) findViewById(R.id.image);
		Button get_imag = (Button) findViewById(R.id.get_image);
		get_imag.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.get_image:
			getImage();
			break;

		default:
			break;
		}
	}

	private void getImage() {
		ImageRequest imageRquest = new ImageRequest(IMAGE_URL,
				new Response.Listener<Bitmap>() {

					@Override
					public void onResponse(Bitmap response) {
						image.setImageBitmap(response);

					}
				}, 0, 0, Config.RGB_565, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						image.setBackgroundResource(R.drawable.ic_launcher);
					}
				});
		queue.add(imageRquest);
	}
}
