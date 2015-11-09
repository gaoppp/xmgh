package com.chen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.chen.R;

public class WVBrowseActivity extends Activity {
	private ImageView iv_back;
	TextView tv_title,tv_time,tv_author;
	WebView wv_content;
	String title,content,author,time;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_home6);
		Log.e("tag", "jksdlk");
		Intent intent=getIntent();
		content=intent.getStringExtra("content");
		tv_title=(TextView) findViewById(R.id.tv_title);
		tv_author=(TextView) findViewById(R.id.tv_author);
		tv_time=(TextView) findViewById(R.id.tv_time);
		wv_content=(WebView) findViewById(R.id.wv_content);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.title_back);
		iv_back=(ImageView)findViewById(R.id.iv_back);
		
		iv_back.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
		setView();
	}

	private void setView() {
	                    tv_title.setText("title");
						tv_author.setText("author");
						tv_time.setText("time");
						WebSettings wSet = wv_content.getSettings();
						wSet.setJavaScriptEnabled(true);
						wv_content.loadDataWithBaseURL(null, content, "text/html",
								"utf-8", null);
		}
	
}
