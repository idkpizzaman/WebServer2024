package com.koreaIT.BAM.dto;

public class Article {
	
	private int number;
	private String date;
	private String title;
	private String content;
	private int viewCnt;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
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

	public Article(int number, String date, String title, String content, int viewCnt) {
		this.number = number;
		this.date = date;
		this.title = title;
		this.content = content;
		this.viewCnt = viewCnt;
	}
	
	public void increaseViewCnt() {
		this.viewCnt++;
	}
}