import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class Main {
	@SuppressWarnings("null")
	public static void main(String[] args) {
		System.out.println("== 프로그램 시작 ==");
		
		Scanner sc = new Scanner(System.in);
		
		List<Article> articles = new ArrayList<>();
		
		LocalDate now = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
		
		int number = 1;
		
		while(true) {			
			System.out.printf("명령어를 입력하세요: ");
			String cmd = sc.nextLine().trim();
			
			if (cmd.equals("exit")) {
				System.out.println("프로그램을 종료합니다.");
				break;
			}
		
			if (cmd.length() == 0) {
				System.out.println("아무것도 입력되지 않았습니다.");
				continue;
			}
			
			if (cmd.equals("article write")) {
				System.out.printf("제목: ");
				String title = sc.nextLine().trim();
				System.out.printf("내용: ");
				String content = sc.nextLine().trim();
				String date = now.format(formatter);
				
				Article article = new Article(number, title, content, date);
				articles.add(article);
				
				System.out.println(number + "번 글이 생성되었습니다.");
				number += 1;
			}
			
			if (cmd.equals("article list")) {
				if (articles.size() == 0) {
					System.out.println("존재하는 게시글이 없습니다.");
				} 
				if (articles.size() != 0) {
					System.out.println();
					for(int i = articles.size() - 1; i >= 0; i--) {		// 리스트 역순으로 출력
						Article article = articles.get(i);
						System.out.println(article.number + "번 게시물");
						System.out.println("제목: " + article.title);
						System.out.println("내용: " + article.content);
						System.out.println("=========================");
					}
				}
			}
			
			if (cmd.startsWith("article detail ")) {
				// 명령어가 article detail 로 시작하는 것 중에
				String[] cmdBits = cmd.split(" ");
				// 띄어쓰기를 기준으로 하나씩 나누어서 cmdBits 배열에 차례대로 저장
				Article foundArticle = null;
				// Article 객체를 하나 만들어서 null 값으로 설정
				
				int id = Integer.parseInt(cmdBits[2]);
				
				for(Article article : articles) {
					if(article.number == id) {
						foundArticle = article;
						// foundArticle을 article 로 만들어서 break
						break;
					} 
				}
				
				if(foundArticle == null) {
					System.out.println(id + "번 게시물이 존재하지 않습니다.");
					continue;
				}
				
				System.out.println("번호: " + foundArticle.number);
				System.out.println("제목: " + foundArticle.title);
				System.out.println("내용: " + foundArticle.content);
				System.out.println("날짜: " + foundArticle.date);
				// 객체를 article이 아닌 foundArticle로 받아서 출력
			} 
			
			if (cmd.startsWith("article delete ")) {
				String[] cmdBits = cmd.split(" ");
				Article foundArticle = null;
				
				int id = Integer.parseInt(cmdBits[2]);
				
				for(Article article : articles) {
					if(article.number == id) {
						foundArticle = article;
						// foundArticle을 article 로 만들어서 break
						break;
					} 
				}
				
//				int foundIndex = -1;
//				
//				for (int i = 0; i < articles.size(); i ++) {
//					Article article = articles.get(i);
//					if(article.number == id) {
//						foundIndex = i;
//						break;
//					}
//				}
				
				if(foundArticle == null) {
					System.out.println("삭제할 " + foundArticle.number + "번 게시물이 존재하지 않습니다.");
					continue;
				}
				
				articles.remove(foundArticle);
				System.out.println(foundArticle.number + "번 게시물이 삭제되었습니다.");
			}
			
			if (cmd.startsWith("article modify ")) {
				String[] cmdBits = cmd.split(" ");
				Article foundArticle = null;
				
				int id = Integer.parseInt(cmdBits[2]);
				
				try {
					id = Integer.parseInt(cmdBits[2]);
					// cmdBits[2]가 정수로 바뀔 수 없는 경우가 있을 수 있기 때문에 try-catch 사용
				} catch(NumberFormatException e) {
					System.out.println("명령어가 올바르지 않습니다.");
					continue;
				}
				
				for(Article article : articles) {
					if(article.number == id) {
						foundArticle = article;
						// foundArticle을 article 로 만들어서 break
						break;
					} 
				}
				
				if(foundArticle == null) {
					System.out.println("수정할 " + foundArticle.number + "번 게시물이 존재하지 않습니다.");
					continue;
				}
				
				System.out.println("수정할 제목: ");
				String title = sc.nextLine().trim();
				System.out.println("수정할 내용: ");
				String content = sc.nextLine().trim();
				
				foundArticle.title = title;
				foundArticle.content = content;
			
				System.out.println(foundArticle.number + "번 게시물이 수정되었습니다.");
			}
		}
		
		sc.close();
		System.out.println("== 프로그램 끝 ==");
	}
}

class Article{
	int number;
	String title;
	String content;
	String date;
	Article (int number, String title, String content, String date) {
		this.content = content;
		this.number = number;
		this.title = title;
		this.date = date;
	}
}