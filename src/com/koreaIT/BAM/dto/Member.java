package com.koreaIT.BAM.dto;

public class Member {
	private int id;
	private String date;
	private String loginId;
	private String loginPw;
	private String name;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getRegDate() {
		return date;
	}
	
	public void setRegDate(String regDate) {
		this.date = regDate;
	}
	
	public String getLoginId() {
		return loginId;
	}
	
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	
	public String getLoginPw() {
		return loginPw;
	}
	
	public void setLoginPw(String loginPw) {
		this.loginPw = loginPw;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Member(int id, String date, String loginId, String loginPw, String name) {
		this.id = id;
		this.date = date;
		this.loginId = loginId;
		this.loginPw = loginPw;
		this.name = name;
	}


	
}
