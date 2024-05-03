import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("== 프로그램 시작 ==");
		
		Scanner sc = new Scanner(System.in);
		
		List<Article> articles = new ArrayList<>();
		
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
				
				Article article = new Article(number, title, content);
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
					for(int i = 0; i < articles.size(); i++) {
						Article article = articles.get(i);
						System.out.println("번호: " + article.number);
						System.out.println("제목: " + article.title);
						System.out.println("내용: " + article.content);
						System.out.println("=========================");
					}
				}
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
	Article (int number, String title, String content) {
		this.content = content;
		this.number = number;
		this.title = title;
	}
}