package com.example.themesample;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FirstActivity extends BaseActivity {

	private Button mChooseTheme;
	private Button mToSecond;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_first);

		mChooseTheme = (Button) findViewById(R.id.btn_choose_theme);

		mChooseTheme.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				try {
					Intent intent = new Intent(FirstActivity.this,
							ThemeSelectorActivity.class);
					startActivity(intent);
					Log.i("ThemeSample", "mChooseTheme click");
				} catch (Exception ex) {
					Log.d("ThemeSample", "ERROR", ex);
				}
			}
		});

		mToSecond = (Button) findViewById(R.id.btn_toSecond);
		mToSecond.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(FirstActivity.this,
						SecondActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.i("ThemeSample", "onPause()");
	}

	private long exitTime = 0;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			if ((System.currentTimeMillis() - exitTime) > 2000) {
				Toast.makeText(getApplicationContext(), "再按一次退出程序",
						Toast.LENGTH_SHORT).show();
				exitTime = System.currentTimeMillis();
			} else {
				Intent intent = new Intent(Intent.ACTION_MAIN);
				intent.addCategory(Intent.CATEGORY_HOME);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				System.exit(0);
			}
			return false;
		}

		return super.onKeyDown(keyCode, event);
	}

}
