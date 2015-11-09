package com.gaop;

public class cases {
	private String title;
	private String time;
	private String author;
	private String content;
	public cases(String title,String author,String time,String content) {
		this.time=time;
		this.author=author;
		this.title=title;
		this.content=content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
