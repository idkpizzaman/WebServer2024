package com.koreaIT.BAM.dao;

import java.util.ArrayList;
import java.util.List;

import com.koreaIT.BAM.dto.Article;
import com.koreaIT.BAM.util.Util;

public class ArticleDao {
	
	private List<Article> articles;
	private int lastId;
	
	public ArticleDao() {
		this.lastId = 1;
		this.articles = new ArrayList<>();
	}
	
	public int getLastId() {
		return lastId;
	}
	
	public void wrtieArticle(int memberId, String title, String content, int viewCnt) {
		articles.add(new Article(lastId, Util.getDateStr(), memberId, title, content, viewCnt));
		lastId++;
	}
	
	public List<Article> getPrintArticles(String searchTitle) {
		if (searchTitle.length() > 0) {
			System.out.println("검색어: " + searchTitle);
			
			List<Article> printArticles = new ArrayList<>();
			
			for (Article article : articles) {
				if (article.getTitle().contains(searchTitle)) {
					printArticles.add(article);
				}
			}
			return printArticles;
		}
		return articles;
	}
	
	public Article getArticleById(int id) {
		for(Article article : articles) {
			if(article.getMemberId() == id) {
				return article;
			}
		}
		return null;
	}
	
	public void increaseViewCnt(Article foundArticle) {
		foundArticle.increaseViewCnt();
	}

	public void modifyArticle(Article foundArticle, String title, String content) {
		foundArticle.setTitle(title);
		foundArticle.setContent(content);
	}

	public void deleteArticle(Article foundArticle) {
		articles.remove(foundArticle);
	}
	
	
}
