package com.example.vollerydemo.domain;

import java.util.List;

public class JsonData<T> {
	private int result;
	private List<JobType> data;

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public List<JobType> getData() {
		return data;
	}

	public void setData(List<JobType> data) {
		this.data = data;
	}

}
