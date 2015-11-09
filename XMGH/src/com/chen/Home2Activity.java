package com.chen;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class Home2Activity extends Activity {
	private ImageView iv_back;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_home2);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.title_back);
		iv_back=(ImageView)findViewById(R.id.iv_back);
		iv_back.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
	}

}
