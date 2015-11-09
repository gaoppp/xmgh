package com.chen;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.gaop.CommonAdapter;
import com.gaop.ViewHolder;
import com.gaop.cases;
import com.gaop.commonActivity;

public class HomeActivity extends commonActivity{
	private boolean isContinue = true;
	List<View> lists;
	private ImageView[] image = null;
	private ImageView imageview;
	private ViewPager viewpager;
	private AtomicInteger what = new AtomicInteger(0);
	private int[] pics = { R.drawable.viewpager_1, R.drawable.viewpager_2,
			R.drawable.viewpager_3, R.drawable.viewpager_4,
			R.drawable.viewpager_5 };
	private ListView lv_zuitoutiao;
	private GridView gv_daxuepaihang;
	private List<cases> data_zuitoutiao;
	List<String> data_daxuepaihang;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		data_zuitoutiao = new ArrayList<cases>();
		getnews();
		initData();
		initView();
	
	}
	private void initView() {
		initViewPager();
		lv_zuitoutiao = (ListView) findViewById(R.id.lv_zuitoutiao);
		lv_zuitoutiao.setAdapter(new CommonAdapter<cases>(HomeActivity.this,
				data_zuitoutiao,
				R.layout.caseitem) {

			@Override
			public void convert(ViewHolder holder, cases t) {
				holder.setText(R.id.tv_case_title, t.getTitle()).setText(
						R.id.tv_case_from, t.getAuthor()+"   "+t.getTime());
			}
		});
		setListViewHeight(lv_zuitoutiao, 0);
		lv_zuitoutiao.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(HomeActivity.this,
						WVBrowseActivity.class);
				intent.putExtra("content", data_zuitoutiao.get(position).getContent());
				startActivity(intent);
			}
		});
		gv_daxuepaihang = (GridView) findViewById(R.id.gv_daxuepaihang);
		gv_daxuepaihang.setAdapter(new CommonAdapter<String>(HomeActivity.this,
				data_daxuepaihang, R.layout.caseitem) {

			@Override
			public void convert(ViewHolder holder, String t) {
				holder.setText(R.id.tv_case_title, t);
			}
		});
		setListViewHeight(gv_daxuepaihang, 2);

	}

	private void initData() {
//		data_zuitoutiao = new ArrayList<cases>();
//		cases cs;
//		cs = new cases("杜伦大学2015年入学住宿申请及押金截止日期", "2015-05");
//		data_zuitoutiao.add(cs);
//		cs = new cases("南安普顿大学发布管理学院暑假 学校报名通知", "2015-05");
//		data_zuitoutiao.add(cs);
//		cs = new cases("谢菲尔德大学仍接受申请的管理和经济类专业", "2015-05");
//		data_zuitoutiao.add(cs);
		data_daxuepaihang = new ArrayList<String>();
		data_daxuepaihang.add("[1] 基尔大学");
		data_daxuepaihang.add("[2] 谢菲尔德大学");
		data_daxuepaihang.add("[3] 曼彻斯曼大学");
		data_daxuepaihang.add("[4] 伦敦大学学院");
		data_daxuepaihang.add("[5] 利兹大学");
		data_daxuepaihang.add("[6] 卡迪夫大学");
		data_daxuepaihang.add("[7] 纽卡斯尔大学");
		data_daxuepaihang.add("[8] 华威大学");
		data_daxuepaihang.add("[9] 南安普顿大学");
		data_daxuepaihang.add("[10] 伯明翰大学");
	}

	private void initViewPager() {
		viewpager = (ViewPager) findViewById(R.id.viewpager);
		lists = new ArrayList<View>();
		for (int i = 0; i < pics.length; i++) {
			View view = LayoutInflater.from(getApplicationContext()).inflate(
					R.layout.viewpager_image, null);
			imageview = (ImageView) view.findViewById(R.id.iv_viewpager);
			imageview.setBackgroundResource(pics[i]);
			lists.add(view);
		}

		viewpager.setAdapter(new ViewPagerAdapter(lists));
		viewpager.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
				case MotionEvent.ACTION_MOVE:
					isContinue = false;
					break;
				case MotionEvent.ACTION_UP:
					isContinue = true;
					break;
				default:
					isContinue = true;
					break;
				}
				return false;
			}
		});

		new Thread(new Runnable() {

			public void run() {
				while (true) {
					if (isContinue) {
						viewHandler.sendEmptyMessage(what.get());
						whatOption();
					}
				}
			}

		}).start();
	}

	private void whatOption() {
		what.incrementAndGet();
		if (what.get() > lists.size() - 1) {
			what.getAndAdd(-4);
		}
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {

		}
	}

	private final Handler viewHandler = new Handler() {
		public void handleMessage(Message msg) {
			viewpager.setCurrentItem(msg.what);
			super.handleMessage(msg);
		}

	};

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 112:
				String goodsData = (String) msg.obj;
				try {
					JSONObject obt=new JSONObject(goodsData);
					String list=obt.getString("list");
					try {
						JSONArray goods_array = new JSONArray(list);
						for (int i = 0; i < goods_array.length(); i++) {
							JSONObject jsonobt = goods_array.getJSONObject(i);
							String content = jsonobt.getString("content");
							Log.e("content", content);
							String title = jsonobt.getString("title");
							Log.e("title", title);
							String author = jsonobt.getString("author");
							// String id = jsonobt.getString("id");
							String time = jsonobt.getString("time");
							Log.e("time", time);
							cases casa = new cases(title, author,time,content);
							data_zuitoutiao.add(casa);
						}
						initView();

					} catch (Exception e) {
						e.printStackTrace();
					}
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				//data_zuitoutiao = new ArrayList<cases>();
				
				break;
			}
		}

	};

//	private void setnewsview() {
//		TextView newstitle4 = (TextView) findViewById(R.id.tv_dynamic_title4);
//		newstitle4.setText(news[4].getTitle());
//		WebView wv4 = (WebView) findViewById(R.id.tv_dynamic_content4);
//		WebSettings wSet = wv4.getSettings();
//		wSet.setJavaScriptEnabled(true);
//		wv4.loadDataWithBaseURL(null, news[4].getContent(), "text/html",
//				"utf-8", null);
//		TextView newstitle5 = (TextView) findViewById(R.id.tv_dynamic_title5);
//		newstitle5.setText(news[5].getTitle());
//		WebView wv5 = (WebView) findViewById(R.id.tv_dynamic_content5);
//		wv5.loadDataWithBaseURL(null, news[5].getContent(), "text/html",
//				"utf-8", null);
//	}

	private void getnews() {
		new Thread(new Runnable() {
			public void run() {
				try {
					Log.e("tag", "BEGIN");
					HttpClient httpClient = new DefaultHttpClient();
					HttpGet httpget = new HttpGet(
							"http://www.hut.edu.cn:8080/xmgh/article/getArticlesPaginate");
					HttpResponse httpResponse = httpClient.execute(httpget);
					Log.e("tag", "BEGINOK");
					if (httpResponse.getStatusLine().getStatusCode() == 200) {
						Log.e("tag", "httpok");
						HttpEntity entity = httpResponse.getEntity();
						String response = EntityUtils.toString(entity, "utf-8");
						Message msg = new Message();
						msg.what = 112;
						msg.obj = response.toString();
						Log.e("tag", (String) msg.obj);
						handler.sendMessage(msg);
					}
				} catch (Exception e) {
					e.printStackTrace();
					Log.e("tag", "ERROR");
				}
			}
		}).start();
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void refresh(Object... param) {
		// TODO Auto-generated method stub

	}

	/**
	 * 自定义ListView和GridView的显示高度
	 * 
	 * @param abslistView
	 * @param viewid
	 *            0为ListView 其他为GridView的列数
	 */
	public void setListViewHeight(AbsListView abslistView, int viewid) {
		ListAdapter listAdapter = abslistView.getAdapter();
		if (listAdapter == null) {
			return;
		}
		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, abslistView);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}
		ViewGroup.LayoutParams params = abslistView.getLayoutParams();
		if (viewid == 0) {
			params.height = totalHeight
					+ (((ListView) abslistView).getDividerHeight() * (listAdapter
							.getCount() - 1));
		} else {
			params.height = totalHeight / viewid;
		}
		// ((MarginLayoutParams) params).setMargins(10, 10, 10, 10);
		abslistView.setLayoutParams(params);
	}

}
