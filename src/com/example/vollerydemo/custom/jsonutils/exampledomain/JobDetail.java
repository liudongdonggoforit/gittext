package com.example.vollerydemo.custom.jsonutils.exampledomain;

import java.io.Serializable;

public class JobDetail implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String cid;
	private String job_name;
	private String tid;
	private String salary;
	private String pay_method;
	private String job_number;
	private String address;
	private String job_des;
	private String uptime;
	private String contacts;
	private String contact_phone;
	private String type_name;
	private String company_name;
	private String province;
	private String is_apply;
	private MyCompany company_res;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getJob_name() {
		return job_name;
	}

	public void setJob_name(String job_name) {
		this.job_name = job_name;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getPay_method() {
		return pay_method;
	}

	public void setPay_method(String pay_method) {
		this.pay_method = pay_method;
	}

	public String getJob_number() {
		return job_number;
	}

	public void setJob_number(String job_number) {
		this.job_number = job_number;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getJob_des() {
		return job_des;
	}

	public void setJob_des(String job_des) {
		this.job_des = job_des;
	}

	public String getUptime() {
		return uptime;
	}

	public void setUptime(String uptime) {
		this.uptime = uptime;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getContact_phone() {
		return contact_phone;
	}

	public void setContact_phone(String contact_phone) {
		this.contact_phone = contact_phone;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getIs_apply() {
		return is_apply;
	}

	public void setIs_apply(String is_apply) {
		this.is_apply = is_apply;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public MyCompany getCompany_res() {
		return company_res;
	}

	public void setCompany_res(MyCompany company_res) {
		this.company_res = company_res;
	}

	@Override
	public String toString() {
		return "JobDetail [id=" + id + ", cid=" + cid + ", job_name="
				+ job_name + ", tid=" + tid + ", salary=" + salary
				+ ", pay_method=" + pay_method + ", job_number=" + job_number
				+ ", address=" + address + ", job_des=" + job_des + ", uptime="
				+ uptime + ", contacts=" + contacts + ", contact_phone="
				+ contact_phone + ", type_name=" + type_name
				+ ", company_name=" + company_name + ", province=" + province
				+ ", is_apply=" + is_apply + ", company_res=" + company_res
				+ "]";
	}

}
