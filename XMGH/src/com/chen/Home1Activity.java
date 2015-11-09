package com.chen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;

public class Home1Activity extends Activity {
	private ImageView iv_back;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_home1);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.title_back);
		iv_back=(ImageView)findViewById(R.id.iv_back);
		iv_back.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
	}
}
