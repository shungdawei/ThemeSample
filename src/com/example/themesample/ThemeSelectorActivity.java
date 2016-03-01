package com.example.themesample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class ThemeSelectorActivity extends Activity {

	private TextView mClose;
	private String globalTheme = "blue";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {

			setContentView(R.layout.activity_theme_selector);

			mClose = (TextView) findViewById(R.id.tv_close);

			mClose.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					finish();
				}
			});

			SharedPreferences themeSharedPreferences = getSharedPreferences(
					Const.THEME_SAVE_KEY, Activity.MODE_PRIVATE);
			// Ĭ����ɫ����
			String theme = themeSharedPreferences.getString("THEME", "blue");
			globalTheme = theme;
			ListView listView = (ListView) findViewById(R.id.lv_theme_selector);

			final List<Map<String, Object>> data = new ArrayList<>();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("theme", "��ɫ����");
			map.put("checked", "blue".equals(theme));
			map.put("theme_val", "blue");
			data.add(map);
			map = new HashMap<String, Object>();
			map.put("theme", "��ɫ����");
			map.put("checked", "green".equals(theme));
			map.put("theme_val", "green");
			data.add(map);

			SimpleAdapter adapter = new SimpleAdapter(this, data,
					R.layout.item_theme_selector, new String[] { "theme",
							"checked" }, new int[] { R.id.tv_theme_name,
							R.id.rb_theme_radio });

			listView.setAdapter(adapter);

			listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					Map<String, Object> map = data.get(position);
					String selectedTheme = String.valueOf(map.get("theme_val"));
					if (!selectedTheme.equals(globalTheme)) {
						SharedPreferences themeSharedPreferences = getSharedPreferences(
								Const.THEME_SAVE_KEY, Activity.MODE_PRIVATE);
						// Toast.makeText(MainActivity.this,
						// "map=" + map.get("theme_val"),
						// Toast.LENGTH_SHORT).show();

						// ʵ����SharedPreferences.Editor���󣨵ڶ�����
						SharedPreferences.Editor editor = themeSharedPreferences
								.edit();
						// ��putString�ķ�����������
						editor.putString("THEME", selectedTheme);
						editor.putBoolean("THEME_CHANGED", true);
						// �ύ��ǰ����
						editor.commit();

						// onResume();
						// Activity activity = MainActivity.this;
						// activity.setTheme(R.style.Theme_Green);
						// activity.finish();
						// activity.startActivity(new Intent(activity, activity
						// .getClass()));
					}
					finish();
				}
			});
			
//			WindowManager m = getWindowManager();    
//		       Display d = m.getDefaultDisplay();  //Ϊ��ȡ��Ļ����    
//		           
//		       android.view.WindowManager.LayoutParams p = getWindow().getAttributes();  //��ȡ�Ի���ǰ�Ĳ���ֵ    
//		       //p.height = (int) (d.getHeight() * 0.6);   //�߶�����Ϊ��Ļ��1.0   
//		       p.width = (int) (d.getWidth() * 0.8);    //�������Ϊ��Ļ��0.8   
//		       p.alpha = 1.0f;      //���ñ���͸����  
//		       p.dimAmount = 0.5f;      //���úڰ���  
//		           
//		       getWindow().setAttributes(p);     //������Ч  
//		       getWindow().setGravity(Gravity.CENTER);       //���þ��ж��� 
			
		} catch (Exception ex) {
			Log.d("ThemeSample", "ERROR", ex);
		}
	}

}
