package com.gaop;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class myAdapter<T> extends BaseAdapter {

	protected Context context;
	protected List<T> data;
	protected int layoutId;

	public myAdapter(Context context, List<T> data,int layoutId) {
		this.context = context;
		this.data = data == null ? new ArrayList<T>() : data;
		this.layoutId=layoutId;
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		if (position >= data.size()) {
			return null;
		}
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	/**
     * 使用该getItemView方法替换原来的getView方法，需要子类实现
     * 
     * @param position
     * @param convertView
     * @param parent
     * @param holder
     * @return
     */
    public abstract View getItemView(int position, View convertView, ViewHolder holder);
    @Override
    public View getView(int position,View convertView,ViewGroup parent){
    	ViewHolder holder;
    	if(null==convertView)
    	{
    		convertView=View.inflate(context, layoutId, null);
    		holder=new ViewHolder(convertView);
    		convertView.setTag(holder);
    	}
    	else{
    		holder=(ViewHolder) convertView.getTag();
    	}
    	return getItemView(position, convertView, holder);
    }
    
    
    
    
    
	public class ViewHolder {
		private SparseArray<View> views = new SparseArray<View>();
		private View convertView;

		public ViewHolder(View convertView) {
			this.convertView = convertView;
		}

		public <T extends View> T getView(int resId) {
			View v = views.get(resId);
			if (null == v) {
				v = convertView.findViewById(resId);
				views.put(resId, v);
			}
			return (T) v;

		}
	}
	 public void addAll(List<T> elem) {
	        data.addAll(elem);
	        notifyDataSetChanged();
	    }
		
	    public void remove(T elem) {
	        data.remove(elem);
	        notifyDataSetChanged();
	    }

	    public void remove(int index) {
	        data.remove(index);
	        notifyDataSetChanged();
	    }

	    public void replaceAll(List<T> elem) {
	        data.clear();
	        data.addAll(elem);
	        notifyDataSetChanged();
	    }
}
