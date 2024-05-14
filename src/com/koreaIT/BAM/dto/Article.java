package com.koreaIT.BAM.dto;

public class Article {
	
	private int id;
	private int memberId;
	private String date;
	private String title;
	private String content;
	private int viewCnt;

	public int getNumber() {
		return id;
	}

	public void setNumber(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getViewCnt() {
		return viewCnt;
	}

	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public Article(int id, String date, int memberId, String title, String content, int viewCnt) {
		this.id = id;
		this.date = date;
		this.title = title;
		this.content = content;
		this.viewCnt = viewCnt;
		this.memberId = memberId;
	}
	
	public void increaseViewCnt() {
		this.viewCnt++;
	}

}