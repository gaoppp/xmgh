package com.chen;

import java.util.ArrayList;
import java.util.List;

import com.gaop.*;
import com.gaop.ReFlashListView.IReflashListener;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ToolActivity extends Activity implements IReflashListener {
	private ReFlashListView mListView;
	private List<cases> mDatas;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tool);
		initDatas();
		initView();
	}

	private void initView() {
		mListView = (ReFlashListView) findViewById(R.id.lv_case);
		mListView.setInterface(this);
		mListView.setAdapter(new CommonAdapter<cases>(ToolActivity.this,
				mDatas, R.layout.caseitem) {
			@Override
			public void convert(ViewHolder holder, cases t) {
				holder.setText(R.id.tv_case_title, t.getTitle()).setText(
						R.id.tv_case_from, t.getAuthor()+"   "+t.getTitle());
			}
		});
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent=new Intent(ToolActivity.this,WVBrowseActivity.class);
				startActivity(intent);
			}
		});

	}

	private void initDatas() {
		mDatas = new ArrayList<cases>();
		cases casa;
		casa = new cases("小马捷报：牛津大学心理学录取", "新闻网" ,"2015-02-01","kdsjfksafjs");
		mDatas.add(casa);
		casa = new cases("小马捷报：牛津大学心理学录取", "新闻网", "2015-02-01","kdsfksjsd");

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tool, menu);
		return true;
	}

	@Override
	public void onReflash() {
		//获取最新数据
		//通知界面显示
		//通知ListView数据完成\
		Toast.makeText(this, "刷新数据", Toast.LENGTH_LONG).show();
		mListView.reflashComplete();
		
	}

}
