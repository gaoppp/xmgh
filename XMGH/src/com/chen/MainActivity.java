package com.chen;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;

import com.gaop.CommActivity;

public class MainActivity extends TabActivity {
	private RadioGroup main_radio;
	private RadioButton tab_home, tab_comm, tab_tool, tab_special, tab_user;
	private TabHost mHost;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_main);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.title);
		initTabHost();
	}

	private void initTabHost() {
		mHost = this.getTabHost();   
		// 添加N个tab选项卡，定义他们的tab名，指示名，目标屏对应的类。
		mHost.addTab(mHost.newTabSpec("one").setIndicator("1")
				.setContent(new Intent(this, HomeActivity.class)));
		mHost.addTab(mHost.newTabSpec("two").setIndicator("2")
				.setContent(new Intent(this, CommActivity.class)));
		mHost.addTab(mHost.newTabSpec("three").setIndicator("3")
				.setContent(new Intent(this, ToolActivity.class)));
		mHost.addTab(mHost.newTabSpec("four").setIndicator("4")
				.setContent(new Intent(this, SpecialActivity.class)));
		mHost.addTab(mHost.newTabSpec("five").setIndicator("5")
				.setContent(new Intent(this, UserActivity.class)));
		main_radio = (RadioGroup) findViewById(R.id.main_radio);
		tab_home = (RadioButton) findViewById(R.id.radio_button0);
		tab_comm = (RadioButton) findViewById(R.id.radio_button1);
		tab_tool = (RadioButton) findViewById(R.id.radio_button2);
		tab_special = (RadioButton) findViewById(R.id.radio_button3);
		tab_user = (RadioButton) findViewById(R.id.radio_button4);
		tab_home.setChecked(true);
		main_radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
					public void onCheckedChanged(RadioGroup group, int id) {
						switch (id) {
						case R.id.radio_button0:
							mHost.setCurrentTabByTag("one");
							break;
						case R.id.radio_button1:
							mHost.setCurrentTabByTag("two");
							break;
						case R.id.radio_button2:
							mHost.setCurrentTabByTag("three");
							break;
						case R.id.radio_button3:
							mHost.setCurrentTabByTag("four");
							break;
						case R.id.radio_button4:
							mHost.setCurrentTabByTag("five");
							break;
						}
					}
				});
	}

}
