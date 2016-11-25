package com.fong2.utils;

import java.io.Serializable;

public class JsonResult<T> implements Serializable{

	private static final long serialVersionUID = -2054345556464221147L;
	public static final int SUCCESS=0;
	public static final int ERROR=1;
	private int state;
	private T data;
	private String message;
	public JsonResult(){
		
	}
	public JsonResult(int state,T t,String message){
		this.state=state;
		data=t;
		this.message=message;
	}
	
	public JsonResult(T t){
		state=SUCCESS;
		data=t;
		message="SUCCESS";
	}
	public JsonResult(T t, String message){
		state=SUCCESS;
		data=t;
		this.message=message;
	}
	public JsonResult(Throwable e){
		state=ERROR;
		data=null;
		message=e.getMessage();
	}
	public JsonResult(int state,Throwable e){
		this.state=state;
		data=null;
		message=e.getMessage();
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
