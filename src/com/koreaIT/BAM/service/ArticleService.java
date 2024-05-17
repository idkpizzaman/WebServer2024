package com.koreaIT.BAM.service;

import java.util.List;

import com.koreaIT.BAM.container.Container;
import com.koreaIT.BAM.dao.ArticleDao;
import com.koreaIT.BAM.dto.Article;

public class ArticleService {

	private ArticleDao articleDao;
	
	public ArticleService() {
		this.articleDao = Container.articleDao;
	}
	
	public int getLastId() {
		return articleDao.getLastId();
	}

	public void writeArticle(int memberId, String title, String content, int viewCnt) {
		articleDao.wrtieArticle(memberId, title, content, viewCnt);
	}
	
	public List<Article> getPrintArticles(String searchTitle) {
		return articleDao.getPrintArticles(searchTitle);
	}

	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
	}

	public void increaseViewCnt(Article foundArticle) {
		articleDao.increaseViewCnt(foundArticle);
	}
	
	public void modifyArticle(Article foundArticle, String title, String content) {
		articleDao.modifyArticle(foundArticle, title, content);
	}

	public void deleteArticle(Article foundArticle) {
		articleDao.deleteArticle(foundArticle);
	}
}