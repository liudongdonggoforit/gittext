package com.example.vollerydemo.domain;

public class JobType {
	private String id;
	private String type_name;
	private String job_type_id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public String getJob_type_id() {
		return job_type_id;
	}

	public void setJob_type_id(String job_type_id) {
		this.job_type_id = job_type_id;
	}

	@Override
	public String toString() {
		return "Type [id=" + id + ", type_name=" + type_name + ", job_type_id="
				+ job_type_id + "]";
	}

}
