package com.example.vollerydemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * 
 * @������: MainActivity
 * @������: TODO
 * @�����ˣ�yzk
 * @����ʱ�䣺2014��10��29�� ����11:03:03
 * @��ע��������
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
