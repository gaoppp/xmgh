package com.chen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;

public class SpecialActivity extends Activity {
	LinearLayout a;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_special);
		a = (LinearLayout) findViewById(R.id.lin1);
		a.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showPopWindow(v);
			}
		});
	}

	private void showPopWindow(View v) {
		View contentView = LayoutInflater.from(SpecialActivity.this).inflate(
				R.layout.pop_window, null);
		Button button = (Button) contentView.findViewById(R.id.btn_pop_cam);
		Button button1 = (Button) contentView.findViewById(R.id.btn_pop_photo);
		final PopupWindow popupWindow = new PopupWindow(contentView,
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
		popupWindow.setTouchable(true);
		// 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
		// 我觉得这里是API的一个bug
		popupWindow.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.action_item_selected));

		// 设置好参数之后再show
		// popupWindow.showAtLocation(a, Gravity.LEFT | Gravity.BOTTOM, 50,50);
		popupWindow.showAsDropDown(v, 200, 0);

	}

	/**
	 * 打开系统相册 
	 */
	private void systemPhoto() {
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		startActivityForResult(intent, 0XFF01);
	}

	/**
	 * 调用相机拍照
	 */
	private void cameraPhoto() {
		String sdStatus = Environment.getExternalStorageState();
		if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) {
			Toast.makeText(this, "SD卡不可用", Toast.LENGTH_SHORT).show();
			return;
		}
		Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
		startActivityForResult(intent, 0XFF02);
	}
}
