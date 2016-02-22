package com.example.vollerydemo.custom.jsonutils;

import java.util.List;

public class ObjectListBean<T> {
	private int result;
	private List<T> data;

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

}
