package com.example.vollerydemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * 
 * @类名称: MainActivity
 * @类描述: TODO
 * @创建人：yzk
 * @创建时间：2014年10月29日 上午11:03:03
 * @备注：主界面
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.enter).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this,
						FunctionActivity.class));

			}
		});
	}

}
