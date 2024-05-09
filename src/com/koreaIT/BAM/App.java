package com.koreaIT.BAM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.koreaIT.BAM.dto.Article;
import com.koreaIT.BAM.util.Util;

public class App {
	
	List<Article> articles;
	int number;
	
	App() {
		articles = new ArrayList<>();
		number = 1;
	}
	
	void run() {
		System.out.println("== 프로그램 시작 ==");
		
		makeTestData();
		
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			System.out.printf("명령어를 입력하세요: ");
			String cmd = sc.nextLine().trim();

			if (cmd.equals("exit")) {
				break;
			}

			if (cmd.length() == 0) {
				System.out.println("명령어를 입력하세요: ");
				continue;
			}

			if (cmd.equals("article write")) {
				System.out.printf("제목 : ");
				String title = sc.nextLine().trim();
				System.out.printf("내용 : ");
				String content = sc.nextLine().trim();

				Article article = new Article(number, Util.getDateStr(), title, content, 0);
				articles.add(article);

				System.out.println(number + "번 글이 생성되었습니다");
				number++;

			} else if (cmd.startsWith("article list")) {
				if (articles.size() == 0) {
					System.out.println("존재하는 게시글이 없습니다");
					continue;
				}
				
				String searchTitle = cmd.substring("articel list".length()).trim();
				
				List<Article> printArticles = new ArrayList<>();
				// 검색어가 입력되지 않으면 원래 있던 요소를 그대로 가지고 있고,
				
				if (searchTitle.length() > 0) {
					System.out.println("검색어: " + searchTitle);
					
					printArticles = new ArrayList<>();
					// 검색어가 입력되었다면 새로운 리스트를 만들어서 검색어에 맞게 넣어줌
					
					for (Article article : articles) {
						if (article.getTitle().contains(searchTitle)) {
							printArticles.add(article);
						}
					}
					
					if (printArticles.size() == 0) {
						System.out.println("검색 결과가 없습니다.");
						continue;
					}
				}
				
				// 검색어가 입력되지 않았을 때에 실행 ↓
				System.out.println("번호	|	제목	|		날짜		|	조회수");

				for (int i = articles.size() - 1; i >= 0; i--) {
					Article article = articles.get(i);
					System.out.printf("%d번	|	%s	|	%s	|	%d회\n", article.getNumber(), article.getTitle(), article.getDate(), article.getViewCnt());
				}	
				
			} else if (cmd.startsWith("article detail ")) {
					
				int id = getCmdNum(cmd);
				
				if (id == 0) {
					System.out.println("존재하지 않는 명령어입니다.");
				}
				
				Article foundArticle = getArticleById(id);
				
				if (foundArticle == null) {
					System.out.println(id + "번 게시물이 존재하지 않습니다");
					continue;
				}
				
				foundArticle.increaseViewCnt();
				
				System.out.println("번호 : " + foundArticle.getNumber());
				System.out.println("날짜 : " + foundArticle.getDate());
				System.out.println("제목 : " + foundArticle.getTitle());
				System.out.println("내용 : " + foundArticle.getContent());
				System.out.println("조회수 : " + foundArticle.getViewCnt());
				
			} else if (cmd.startsWith("article modify ")) {
				int id = getCmdNum(cmd);
				
				if (id == 0) {
					System.out.println("존재하지 않는 명령어입니다.");
				}
				
				Article foundArticle = getArticleById(id);
				
				if (foundArticle == null) {
					System.out.println(id + "번 게시물이 존재하지 않습니다");
					continue;
				}
				
				System.out.printf("수정할 제목 : ");
				String title = sc.nextLine().trim();
				System.out.printf("수정할 내용 : ");
				String content = sc.nextLine().trim();
				
				foundArticle.setTitle(title);
				foundArticle.setContent(content);
			
				System.out.println(id + "번 게시물이 수정되었습니다");
				
			} else if (cmd.startsWith("article delete ")) {
				int id = getCmdNum(cmd);
				
				if (id == 0) {
					System.out.println("존재하지 않는 명령어입니다.");
				}
				
				Article foundArticle = getArticleById(id);
				
				if (foundArticle == null) {
					System.out.println(id + "번 게시물이 존재하지 않습니다");
					continue;
				}
				
				articles.remove(foundArticle);
				
				System.out.println(id + "번 게시물이 삭제되었습니다");
				
			} else {
				System.out.println("존재하지 않는 명령어 입니다");
			}

		}

		sc.close();

		System.out.println("== 프로그램 끝 ==");
		}
	
	private Article getArticleById(int id) {
		for (Article article : articles) {
			if (article.getNumber() == id) {
				return article;
			}
		} return null;
	}

	private int getCmdNum(String cmd) {
		String[] cmdBits = cmd.split(" ");
		
		try {
			int id = Integer.parseInt(cmdBits[2]);
			return id;
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	private void makeTestData() {
	// App 클래스 외부에 다른 클래스에서 호출된 적이 없기 때문에 private 를 사용해주어도 상관업삼
		System.out.println("테스트용 게시글 데이터를 5개 생성했습니다");
		
		for (int i = 1; i <= 5; i++) {
			articles.add(new Article(number++, Util.getDateStr(), "제목" + i, "내용" + i, i * 10));
		}
	}
}