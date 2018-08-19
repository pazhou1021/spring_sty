package com.ca.entity;

import java.util.List;


public class UserInfo {
	private int id;
	private String name;
	private String pwd;
	private String loginTime;
	private List<Message> megs;

	public List<Message> getMegs() {
		return megs;
	}

	public void setMegs(List<Message> megs) {
		this.megs = megs;
	}

	public String getName() {
		return name;
	}
	
	


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPwd() {
		return pwd;
	}


	public void setPwd(String pwd) {
		this.pwd = pwd;
	}


	public String getLoginTime() {
		return loginTime;
	}


	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}


	public UserInfo(int id, String name, String pwd, String loginTime,
			 List<Message> mes) {
		super();
		this.id = id;
		this.name = name;
		this.pwd = pwd;
		this.loginTime = loginTime;
		this.megs = mes;
	}
	public UserInfo(int id,String name) {
		super();
		this.id=id;
		this.name = name;
	}

	public UserInfo() {
		super();
	}
	
}
