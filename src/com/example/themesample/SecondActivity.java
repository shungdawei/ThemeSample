package com.example.themesample;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends BaseActivity {

	private Button mChooseTheme;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);

		mChooseTheme = (Button) findViewById(R.id.btn_choose_theme);

		mChooseTheme.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				try {
					Intent intent = new Intent(SecondActivity.this,
							ThemeSelectorActivity.class);
					startActivity(intent);
					Log.i("ThemeSample", "mChooseTheme click");
				} catch (Exception ex) {
					Log.d("ThemeSample", "ERROR", ex);
				}
			}
		});
	}

}
