package com.example.vollerydemo.custom.jsonutils;

public class ObjectBean<T> {
	private int result;
	private T data;
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
}
