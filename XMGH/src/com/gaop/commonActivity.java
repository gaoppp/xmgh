package com.gaop;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

public abstract class commonActivity extends Activity {
	 private static String Tag = "CommActivity";
		
		 @Override
		 protected void onCreate(Bundle savedInstanceState) {
		 // TODO Auto-generated method stub
		 super.onCreate(savedInstanceState);
		 Log.i(Tag, "启动create");
		 this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		 MainService.addActivity(this);
		 }
		
		 public abstract void init();
		
		 public abstract void refresh(Object... param);
		
		 @Override
		 protected void onDestroy() {
		 // TODO Auto-generated method stub
		 super.onDestroy();
		 Log.i(Tag, "启动destroy");
		 MainService.removeActivity(this);
		 }
		
		 @Override
		 protected void onStart() {
		 // TODO Auto-generated method stub
		 super.onStart();
		 Log.i(Tag, "启动start");
		 init();
		 }
		
		 @Override
		 protected void onRestart() {
		 // TODO Auto-generated method stub
		 super.onRestart();
		 Log.i(Tag, "启动ReStart");
		 }
		
		 @Override
		 protected void onResume() {
		 // TODO Auto-generated method stub
		 super.onResume();
		 Log.i(Tag, "启动resume");
		 }
		
		 @Override
		 protected void onPause() {
		 // TODO Auto-generated method stub
		 super.onPause();
		 Log.i(Tag, "启动pause");
		 }
		
		 @Override
		 protected void onStop() {
		 // TODO Auto-generated method stub
		 super.onStop();
		 Log.i(Tag, "启动stop");
		 }
}
