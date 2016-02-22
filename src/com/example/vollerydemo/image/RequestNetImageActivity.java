package com.example.vollerydemo.image;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.util.LruCache;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.example.vollerydemo.R;
import com.example.vollerydemo.utils.LogUtils;

/**
 * 
 * @类名称: RequestImageActivity
 * @类描述: TODO
 * @创建人：yzk
 * @创建时间：2014年10月29日 下午2:50:39
 * @备注：ImageRequest的使用
 */
public class RequestNetImageActivity extends Activity implements
		OnClickListener {
	private NetworkImageView image1;
	private NetworkImageView image2;
	private NetworkImageView image3;
	private NetworkImageView image4;
	private NetworkImageView image5;
	private ImageLoader imageLoader;
	private List<NetworkImageView> views;
	private int id = R.drawable.ic_launcher;
	private final String IMAGE_URL1 = "http://c.hiphotos.baidu.com/image/w%3D310/sign=60dce90012dfa9ecfd2e501652d0f754/6159252dd42a2834a694bd1758b5c9ea15cebff8.jpg";
	private final String IMAGE_URL2 = "http://e.hiphotos.baidu.com/image/w%3D310/sign=7c5075fa523d26972ed30e5c65fab24f/37d3d539b6003af3e63b9450362ac65c1038b679.jpg";
	private final String IMAGE_URL3 = "http://h.hiphotos.baidu.com/image/w%3D310/sign=ef85d31d0af79052ef1f413f3cf3d738/b3119313b07eca80aa1999e9922397dda14483a4.jpg";
	private final String IMAGE_URL4 = "http://e.hiphotos.baidu.com/image/w%3D310/sign=753a11aa5aee3d6d22c681ca73166d41/b7003af33a87e950112df16113385343fbf2b480.jpg";
	private final String IMAGE_URL5 = "http://d.hiphotos.baidu.com/image/w%3D310/sign=aa818ed6818ba61edfeece2e713597cc/50da81cb39dbb6fd49d26d3a0a24ab18972b3724.jpg";
	private String[] urls = new String[] { IMAGE_URL1, IMAGE_URL2, IMAGE_URL3,
			IMAGE_URL4, IMAGE_URL5 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_net);
		initView();
		initData();
	}

	private void initData() {
		RequestQueue queue = Volley
				.newRequestQueue(RequestNetImageActivity.this);
		imageLoader = new ImageLoader(queue, new BitMapCache());
		views = new ArrayList<NetworkImageView>();
		views.add(image1);
		views.add(image2);
		views.add(image3);
		views.add(image4);
		views.add(image5);
	}

	private void initView() {
		Button get_imag = (Button) findViewById(R.id.get_image);
		get_imag.setOnClickListener(this);
		image1 = (NetworkImageView) findViewById(R.id.image1);
		image2 = (NetworkImageView) findViewById(R.id.image2);
		image3 = (NetworkImageView) findViewById(R.id.image3);
		image4 = (NetworkImageView) findViewById(R.id.image4);
		image5 = (NetworkImageView) findViewById(R.id.image5);
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
		NetworkImageView view;
		for (int i = 0; i < urls.length; i++) {
			view = views.get(i);
			view.setDefaultImageResId(id);
			view.setErrorImageResId(id);
			view.setImageUrl(urls[i], imageLoader);
		}
	}

	public class BitMapCache implements ImageCache {
		private LruCache<String, Bitmap> lruCache;

		public BitMapCache() {
			int maxSize = 10 * 1024 * 1024;
			lruCache = new LruCache<String, Bitmap>(maxSize) {

				@Override
				protected int sizeOf(String key, Bitmap value) {
					return value.getRowBytes() * value.getHeight();
				}
			};
		}

		@Override
		public Bitmap getBitmap(String url) {
			LogUtils.d_url("从内存中获取");
			return lruCache.get(url);
		}

		@Override
		public void putBitmap(String url, Bitmap bitmap) {
			LogUtils.d_url("设置到内存中");
			lruCache.put(url, bitmap);
		}

	}
}
