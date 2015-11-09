package com.gaop;



import com.chen.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class CommActivity extends Activity {
	private ImageView iv_back;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_comm);
		//getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.title_back);
	}

}

