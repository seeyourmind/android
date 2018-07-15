package com.example.bb_test1;


public class DynamicInformation {
	
	private String username,time,content_text,content_image;
	private int praiseNumber;
	
	public DynamicInformation(){}
	public DynamicInformation(String username, String time){
		this.username = username;
		this.time = time;
	}

	//getter方法
	public String getUsername() {
		return username;
	}
	public String getTime(){
		return time;
	}
	public String getContentText(){
		return content_text;
	}
	public String getContentImage(){
		return content_image;
	}
	public int getPraiseNumber(){
		return praiseNumber;
	}
	//setter方法
	public void setUsername(String username){
		this.username = username;
	}
	public void setTime(String time){
		this.time = time;
	}
	public void setContentText(String content_text){
		this.content_text = content_text;
	}
	public void setContentImage(String content_image){
		this.content_image = content_image;
	}
	public void setPraiseNumber(int praiseNumber){
		this.praiseNumber = praiseNumber;
	}
}
