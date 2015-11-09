package com.gaop;

import java.util.ArrayList;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

public class MainService extends Service implements Runnable{
	  public static boolean isrun = false;
	   private static ArrayList<Task> allTask = new ArrayList<Task>();
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}


    // 添加窗口到集合中
    public static void addActivity(commonActivity activity) {
       // AppManager.getAppManager().addActivity(ia);
    }

    public static void removeActivity(commonActivity activity) {
      // AppManager.getAppManager().finishActivity(ia);
    }

    // 添加任务
    public static void newTask(Task ts) {
       // allTask.add(ts);
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        isrun = false;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        // TODO Auto-generated method stub
        super.onStart(intent, startId);
        isrun = true;
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        while (isrun) {
            if (allTask.size() > 0) {
                doTask(allTask.get(0));
            } else {
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {

                }
            }
        }
    }

    private void doTask(Task ts) {
        Message message = hand.obtainMessage();
        message.what = ts.getTaskID();
        switch (ts.getTaskID()) {
          
        }
        allTask.remove(ts);
        hand.sendMessage(message);
    }

    private final Handler hand = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                
            }
        };
    };
    //检测网络
    public static boolean isNetworkable(Context context)
    {
    	ConnectivityManager connectivity=(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    	if(connectivity!=null)
    	{
    		NetworkInfo info=connectivity.getActiveNetworkInfo();
    		if(info!=null&&info.isConnected()){
    			if(info.getState()==NetworkInfo.State.CONNECTED){
    				return  true;
    			}
    		}
    	}
    	return false;
    }

}
