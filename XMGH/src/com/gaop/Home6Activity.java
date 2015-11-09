package com.gaop;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.chen.MainActivity;
import com.chen.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

public class Home6Activity extends Activity {
	private ImageView iv_back;
	TextView tv_title,tv_time,tv_author;
	WebView wv_content;
	String title,content,author,time;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_home6);
		tv_title=(TextView) findViewById(R.id.tv_title);
		tv_author=(TextView) findViewById(R.id.tv_author);
		tv_time=(TextView) findViewById(R.id.tv_time);
		wv_content=(WebView) findViewById(R.id.wv_content);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.title_back);
		iv_back=(ImageView)findViewById(R.id.iv_back);
		getnews();
		iv_back.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
	}

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 112:
				String goodsData = (String) msg.obj;
				try {
						JSONObject jsonobt =new JSONObject(goodsData);
						Log.e("tag", goodsData);
						tv_title.setText(jsonobt.getString("title"));
						tv_author.setText(jsonobt.getString("author"));
						tv_time.setText(jsonobt.getString("time"));
						WebSettings wSet = wv_content.getSettings();
						wSet.setJavaScriptEnabled(true);
						wv_content.loadDataWithBaseURL(null,jsonobt.getString("content"), "text/html", "utf-8", null);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			}
		}
	};
		
	// 获取新闻列表数据
		private void getnews() {
			new Thread(new Runnable() {
				public void run() {
					try {
						HttpClient httpClient = new DefaultHttpClient();
						HttpGet httpPost = new HttpGet(
								"http://www.hut.edu.cn:8080/article/findByID?id=5");
						// 执行请求
						HttpResponse httpResponse = httpClient.execute(httpPost);
						//httpResponse.setHeader("Content-Type","application/json;charset=UTF-8");
						
						if (httpResponse.getStatusLine().getStatusCode() == 200) {
							HttpEntity entity = httpResponse.getEntity();
							String response = EntityUtils.toString(entity,"utf-8");
							Message msg = new Message();
							msg.what = 112;
							msg.obj = response.toString();Log.e("tag", (String) msg.obj);
							handler.sendMessage(msg);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
}
