package com.example.themesample;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class SplashActivity extends BaseActivity {

	private Handler mHandler;
	private LinearLayout mSplashContainer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_splash);

		mHandler = new Handler() {

			@SuppressLint("NewApi")
			@Override
			public void handleMessage(Message msg) {
				if (msg.what == 100) {

					// SplashActivity.this.finish();

					// overridePendingTransition(0, 0);
					
					Intent intent = new Intent(SplashActivity.this,
							FirstActivity.class);
					startActivity(intent);
				} else if (msg.what == 101) {
					 mSplashContainer.setBackgroundResource(0);
					 mSplashContainer.setBackground(null);
				}
				
				//super.handleMessage(msg);
			}

		};

		mHandler.sendEmptyMessageDelayed(100, 1000);
		//mHandler.sendEmptyMessageDelayed(101, 2000);

	}

}
