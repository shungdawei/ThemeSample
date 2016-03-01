package com.example.themesample;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class BaseActivity extends Activity {

	private static int color = 0;
	private String globalTheme = "blue";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SharedPreferences themeSharedPreferences = getSharedPreferences(
				Const.THEME_SAVE_KEY, Activity.MODE_PRIVATE);
		String theme = themeSharedPreferences.getString("THEME", "blue");
		globalTheme = theme;
		if ("blue".equals(theme)) {
			setTheme(R.style.Theme_Blue);
			color = R.color.theme_blue;
		} else if ("green".equals(theme)) {
			setTheme(R.style.Theme_Green);
			color = R.color.theme_green;
		}
		initSystemBar(this);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		Log.i("ThemeSample", "globalTheme=" + globalTheme);

		Log.i("ThemeSample", "onResume()");

		SharedPreferences themeSharedPreferences = getSharedPreferences(
				Const.THEME_SAVE_KEY, Activity.MODE_PRIVATE);
		boolean themeChanged = themeSharedPreferences.getBoolean(
				"THEME_CHANGED", false);

		String theme = themeSharedPreferences.getString("THEME", "blue");

		Log.i("ThemeSample", "theme=" + theme);
		Log.i("ThemeSample", "themeChanged=" + themeChanged);

		if (themeChanged || !globalTheme.equals(theme)) {
			Log.i("ThemeSample", "do reStart");
			SharedPreferences.Editor editor = themeSharedPreferences.edit();
			editor.putBoolean("THEME_CHANGED", false);
			editor.commit();
			this.finish();
			startActivity(new Intent(this, this.getClass()));
			overridePendingTransition(0, 0);
		}

	}

	public static void initSystemBar(Activity activity) {

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

			setTranslucentStatus(activity, true);

		}

		SystemBarTintManager tintManager = new SystemBarTintManager(activity);

		tintManager.setStatusBarTintEnabled(true);
		// 使用颜色资源
		tintManager.setStatusBarTintResource(color);
		// tintManager.setStatusBarTintResource(0);//状态栏无背景

	}

	@TargetApi(19)
	private static void setTranslucentStatus(Activity activity, boolean on) {

		Window win = activity.getWindow();

		WindowManager.LayoutParams winParams = win.getAttributes();

		final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;

		if (on) {

			winParams.flags |= bits;

		} else {

			winParams.flags &= ~bits;

		}

		win.setAttributes(winParams);

	}
}
