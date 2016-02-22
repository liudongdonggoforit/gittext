package com.example.vollerydemo.custom.jsonutils.exampledomain;

import java.io.Serializable;

public class MyCompany implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String company_name;
	private String industry;
	private String nature;
	private String scale;
	private String company_address;
	private String company_des;
	private String license;
	private String organize_photo;
	private String uid;
	private String company_province;// 公司所在地

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public String getCompany_address() {
		return company_address;
	}

	public void setCompany_address(String company_address) {
		this.company_address = company_address;
	}

	public String getCompany_des() {
		return company_des;
	}

	public void setCompany_des(String company_des) {
		this.company_des = company_des;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getOrganize_photo() {
		return organize_photo;
	}

	public void setOrganize_photo(String organize_photo) {
		this.organize_photo = organize_photo;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getCompany_province() {
		return company_province;
	}

	public void setCompany_province(String company_province) {
		this.company_province = company_province;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "MyCompany [id=" + id + ", company_name=" + company_name
				+ ", industry=" + industry + ", nature=" + nature + ", scale="
				+ scale + ", company_address=" + company_address
				+ ", company_des=" + company_des + ", license=" + license
				+ ", organize_photo=" + organize_photo + ", uid=" + uid
				+ ", company_province=" + company_province + "]";
	}

}
