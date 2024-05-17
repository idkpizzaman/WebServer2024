package com.koreaIT.BAM.controller;

import java.util.List;
import java.util.Scanner;

import com.koreaIT.BAM.container.Container;
import com.koreaIT.BAM.dto.Article;
import com.koreaIT.BAM.service.ArticleService;
import com.koreaIT.BAM.service.MemberService;


public class ArticleController extends Controller {
	
	private MemberService memberService;
	private ArticleService articleService;
	
	public ArticleController(Scanner sc) {
		this.sc = sc;
		this.articleService = Container.articleService;
		this.memberService = Container.memberService;
	}
	
	@Override
	public void doAction(String cmd, String methodName) {
		this.cmd = cmd;
		
		switch(methodName) {
		case "write":
			doWrite();
			break;
		case "list":
			showList();
			break;
		case "detail":
			showDetail();
			break;
		case "modify":
			doModify();
			break;
		case "delete":
			doDelete();
			break;
		default :
			System.out.println("존재하지 않는 명령어 입니다.");
		}
	}
	
	private void doWrite() {

		System.out.printf("제목 : ");
		String title = sc.nextLine().trim();
		System.out.printf("내용 : ");
		String content = sc.nextLine().trim();

		int id = articleService.getLastId();
		articleService.writeArticle(loginedMember.getId(), title, content, 0);
		System.out.println(id + "번 글이 생성되었습니다.");
	}
	
	public void showList() {
		
		String searchTitle = cmd.substring("articel list".length()).trim();
		
		List<Article> printArticles = articleService.getPrintArticles(searchTitle);
		// 검색어가 입력되지 않으면 원래 있던 요소를 그대로 가지고 있고,
		
		if (printArticles.size() == 0) {
			System.out.println("존재하는 게시글이 없습니다");
			return;
		}
		
		// 검색어가 입력되지 않았을 때에 실행 ↓
		System.out.println("	번호	|	제목	  |		날짜		|	조회수	|	작성자	 |");

		for (int i = printArticles.size() - 1; i >= 0; i--) {
			Article article = printArticles.get(i);
			String writerLoingId = memberService.getLoginIdByMemberId(article.getMemberId());
			System.out.printf("	%d번	|	%s	  |	%s	|	%d회	|	%s	 |\n", article.getNumber(), article.getTitle(), article.getDate(), article.getViewCnt(), writerLoingId);
		}
	}
	
	public void showDetail() {
		int id = getCmdNum(cmd);
		
		if (id == 0) {
			System.out.println("존재하지 않는 명령어입니다.");
			return;
		}
		
		Article foundArticle = articleService.getArticleById(id);
		
		if (foundArticle == null) {
			System.out.println(id + "번 게시물이 존재하지 않습니다");
			return;
		}
		
		articleService.increaseViewCnt(foundArticle);
		
		String writerLoingId = memberService.getLoginIdByMemberId(foundArticle.getMemberId());
		
		System.out.println("번호 : " + foundArticle.getNumber());
		System.out.println("날짜 : " + foundArticle.getDate());
		System.out.println("작성자 : " + writerLoingId);
		System.out.println("제목 : " + foundArticle.getTitle());
		System.out.println("내용 : " + foundArticle.getContent());
		System.out.println("조회수 : " + foundArticle.getViewCnt());
	}
	
	public void doModify() {
		
		int id = getCmdNum(cmd);
		
		if (id == 0) {
			System.out.println("명령어가 올바르지 않습니다.");
			return;
		}
		
		Article foundArticle = articleService.getArticleById(id);
		
		if (foundArticle == null) {
			System.out.println(id + "번 게시물이 존재하지 않습니다");
			return;
		}
		
		if (foundArticle.getMemberId() != loginedMember.getId()) {
			System.out.println("해당 게시물에 권한이 없습니다.");
			return;
		}
		
		System.out.printf("수정할 제목 : ");
		String title = sc.nextLine().trim();
		System.out.printf("수정할 내용 : ");
		String content = sc.nextLine().trim();
		
		articleService.modifyArticle(foundArticle, title, content);
	
		System.out.println(id + "번 게시물이 수정되었습니다");
	}
	
	public void doDelete() {
		
		int id = getCmdNum(cmd);
		
		if (id == 0) {
			System.out.println("명령어가 올바르지 않습니다.");
			return;
		}
		
		Article foundArticle = articleService.getArticleById(id);
		
		if (foundArticle == null) {
			System.out.println(id + "번 게시물이 존재하지 않습니다");
			return;
		}
		
		if (foundArticle.getMemberId() != loginedMember.getId()) {
			System.out.println("해당 게시물에 권한이 없습니다.");
			return;
		}
		
		articleService.deleteArticle(foundArticle);
		
		System.out.println(id + "번 게시물이 삭제되었습니다");
	}
	
	
	private int getCmdNum(String cmd) {
		String[] cmdBits = cmd.split(" ");
		
		try {
			int id = Integer.parseInt(cmdBits[2]);
			return id;
		} catch (NumberFormatException e) {
			return 0;
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}
	
	@Override
	public void makeTestData() {
		System.out.println("테스트용 게시글 데이터를 5개 생성했습니다");
		for (int i = 1; i <= 5; i++) {
			articleService.writeArticle((int)(Math.random() * 3) + 1, "제목" + i, "내용" + i, i*10);
		}
	}

}
