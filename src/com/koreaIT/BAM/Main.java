package com.koreaIT.BAM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.koreaIT.BAM.dto.Article;
import com.koreaIT.BAM.util.Util;

public class Main {
	
	static List<Article> articles;
	static int number;
	
	static {
		articles = new ArrayList<>();
		number = 1;
	}
	
	public static void main(String[] args) {

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

			} else if (cmd.equals("article list")) {
				if (articles.size() == 0) {
					System.out.println("존재하는 게시글이 없습니다");
					continue;
				}

				System.out.println("번호	|	제목	|		날짜		|	조회수");

				for (int i = articles.size() - 1; i >= 0; i--) {
					Article article = articles.get(i);
					System.out.printf("%d 번	|	%s	|	%s	|	%d\n 회", article.getNumber(), article.getTitle(), article.getDate(), article.getViewCnt());
				}
				
			} else if (cmd.startsWith("article detail ")) {
				String[] cmdBits = cmd.split(" ");

				int id = 0;
				
				try {
					id = Integer.parseInt(cmdBits[2]);
				} catch (NumberFormatException e) {
					System.out.println("명령어가 올바르지 않습니다");
					continue;
				}
				
				Article foundArticle = null;
				
				for (Article article : articles) {
					if (article.getNumber() == id) {
						foundArticle = article;
						break;
					}
				}
				
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
				String[] cmdBits = cmd.split(" ");

				int id = 0;
				
				try {
					id = Integer.parseInt(cmdBits[2]);
				} catch (NumberFormatException e) {
					System.out.println("명령어가 올바르지 않습니다");
					continue;
				}
				
				Article foundArticle = null;
				
				for (Article article : articles) {
					if (article.getNumber() == id) {
						foundArticle = article;
						break;
					}
				}
				
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
			
				System.out.println(number + "번 게시물이 수정되었습니다");
				
			} else if (cmd.startsWith("article delete ")) {
				String[] cmdBits = cmd.split(" ");

				int id = 0;
				
				try {
					id = Integer.parseInt(cmdBits[2]);
				} catch (NumberFormatException e) {
					System.out.println("명령어가 올바르지 않습니다");
					continue;
				}
				
				Article foundArticle = null;
				
				for (Article article : articles) {
					if (article.getNumber() == id) {
						foundArticle = article;
						break;
					}
				}
				
				if (foundArticle == null) {
					System.out.println(number + "번 게시물이 존재하지 않습니다");
					continue;
				}
				
				articles.remove(foundArticle);
				
				System.out.println(number + "번 게시물이 삭제되었습니다");
				
			} else {
				System.out.println("존재하지 않는 명령어 입니다");
			}

		}

		sc.close();

		System.out.println("== 프로그램 끝 ==");
	}

	private static void makeTestData() {
		System.out.println("테스트용 게시글 데이터를 5개 생성했습니다");
		
		for (int i = 1; i <= 5; i++) {
			articles.add(new Article(number++, Util.getDateStr(), "제목" + i, "내용" + i, i * 10));
		}
	}
	
}