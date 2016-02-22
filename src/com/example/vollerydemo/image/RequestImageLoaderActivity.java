package com.example.vollerydemo.image;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.util.LruCache;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.Volley;
import com.example.vollerydemo.R;
import com.example.vollerydemo.utils.LogUtils;

/**
 * 
 * @类名称: RequestImageLoaderActivity
 * @类描述: TODO
 * @创建人：yzk
 * @创建时间：2014年10月29日 下午3:15:41
 * @备注：ImageLoader
 */
public class RequestImageLoaderActivity extends Activity implements
		OnClickListener {
	private ImageView image1;
	private ImageView image2;
	private ImageView image3;
	private ImageView image4;
	private ImageView image5;
	private ImageView image6;
	private ImageView image7;
	private ImageView image8;
	private ImageView image9;
	private ImageView image10;
	private RequestQueue queue;
	private final String IMAGE_URL1 = "http://b.hiphotos.baidu.com/image/w%3D310/sign=132ace3b88d4b31cf03c92bab7d7276f/4e4a20a4462309f7c995786a710e0cf3d6cad6d4.jpg";
	private final String IMAGE_URL2 = "http://a.hiphotos.baidu.com/image/w%3D310/sign=d372b7e38544ebf86d71623ee9f8d736/30adcbef76094b3614bd950da1cc7cd98d109d27.jpg";
	private final String IMAGE_URL3 = "http://g.hiphotos.baidu.com/image/w%3D310/sign=44f6fcc0bb12c8fcb4f3f0cccc0292b4/5bafa40f4bfbfbed9d19b89f7af0f736aec31f89.jpg";
	private final String IMAGE_URL4 = "http://h.hiphotos.baidu.com/image/w%3D310/sign=e4992cfbd60735fa91f048b8ae500f9f/b21bb051f819861879fb616448ed2e738ad4e696.jpg";
	private final String IMAGE_URL5 = "http://g.hiphotos.baidu.com/image/w%3D310/sign=02ad3ef0f8edab6474724bc1c737af81/e824b899a9014c08c8a4689e097b02087bf4f46a.jpg";
	private final String IMAGE_URL6 = "http://e.hiphotos.baidu.com/image/w%3D310/sign=c2975a7cb5003af34dbada61052ac619/b8389b504fc2d56271716a98e41190ef76c66c4e.jpg";
	private ImageLoader imageLoader;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_loader);
		initView();
		initData();
	}

	private void initData() {
		queue = Volley.newRequestQueue(RequestImageLoaderActivity.this);
		imageLoader = new ImageLoader(queue, new BitMapCache());
	}

	private void initView() {
		image1 = (ImageView) findViewById(R.id.image1);
		image2 = (ImageView) findViewById(R.id.image2);
		image3 = (ImageView) findViewById(R.id.image3);
		image4 = (ImageView) findViewById(R.id.image4);
		image5 = (ImageView) findViewById(R.id.image5);
		image6 = (ImageView) findViewById(R.id.image6);
		image7 = (ImageView) findViewById(R.id.image7);
		image8 = (ImageView) findViewById(R.id.image8);
		image9 = (ImageView) findViewById(R.id.image9);
		image10 = (ImageView) findViewById(R.id.image10);
		Button get_imag = (Button) findViewById(R.id.get_image);
		get_imag.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.get_image:
			getImages();
			break;

		default:
			break;
		}
	}

	/*
	 * 1. 创建一个RequestQueue对象。
	 * 
	 * 2. 创建一个ImageLoader对象。
	 * 
	 * 3. 获取一个ImageListener对象。
	 * 
	 * 4. 调用ImageLoader的get()方法加载网络上的图片。
	 */
	private void getImages() {
		setImage(image1, IMAGE_URL1);
		setImage(image2, IMAGE_URL2);
		setImage(image3, IMAGE_URL3);
		setImage(image4, IMAGE_URL4);
		setImage(image5, IMAGE_URL5);
		setImage(image6, IMAGE_URL6);
		setImage(image7, IMAGE_URL1);
		setImage(image8, IMAGE_URL2);
		setImage(image9, IMAGE_URL3);
		setImage(image10, IMAGE_URL4);
	}

	public void setImage(ImageView image, String url) {
		ImageListener listener = ImageLoader.getImageListener(image,
				R.drawable.ic_launcher, R.drawable.ic_launcher);
		imageLoader.get(url, listener, 0, 0);
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
