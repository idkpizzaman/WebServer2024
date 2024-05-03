import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("== 프로그램 시작 ==");
		
		Scanner sc = new Scanner(System.in);
		int articlenumber = 0;
		
		while(true) {			
			System.out.println("명령어를 입력하세요: ");
			String cmd = sc.nextLine().trim();
			
			if (cmd.equals("exit")) {
				System.out.println("프로그램을 종료합니다.");
				break;
			}
		
			if (cmd.length() == 0) {
				System.out.println("아무것도 입력되지 않았습니다.");
				continue;
			}
			
			if (cmd.equals("article list")) {
				System.out.println("게시글이 없습니다.");
			} else if (cmd.equals("article write")) {
				System.out.printf("제목: ");
				String title = sc.nextLine().trim();
				System.out.printf("내용: ");
				String content = sc.nextLine().trim();
				
				articlenumber += 1;
				System.out.println(articlenumber + "번 글이 생성되었습니다.");
			} else {
				System.out.println("존재하지 않는 명령어입니다.");
			}		
		}
		
		sc.close();
		System.out.println("== 프로그램 끝 ==");
	}
}

class ArticleListUpload {
	void listupload() {
		
	}
}