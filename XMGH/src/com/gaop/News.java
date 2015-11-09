package com.gaop;

import java.io.Serializable;

import android.util.Log;

public class News implements Serializable{
	String title,content,type;
	String id;
	private String time;
	public News(String title,String content,String type,String time,String id2)
	{
		Log.e("tag", "yes");
		this.title=title;
		this.content=content;
		this.type=type;
		this.id=id2;
		this.time=time;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
}
