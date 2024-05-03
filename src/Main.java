import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("== 프로그램 시작 ==");
		
		Scanner sc = new Scanner(System.in);
		int listnumber = 0;
		
		while(true) {			
			System.out.println("명령어를 입력하세요: ");
			String cmd = sc.nextLine();
			
			if (cmd.equals("article write")) {
				System.out.println("제목: ");
				String title = sc.nextLine();
				System.out.println("내용: ");
				String content = sc.nextLine();
				
				listnumber += 1;
				System.out.println(listnumber + "번 글이 생성되었습니다.");
			}
			
			if (cmd.equals("article list")) {
				System.out.println("게시글이 없습니다.");
			}
			
			if (cmd.equals("exit")) {
				System.out.println("프로그램을 종료합니다.");
				break;
			}
			
		}
		
		sc.close();
		System.out.println("== 프로그램 끝 ==");
	}
}

class ArticleListUpload{
	void listupload() {
		
	}
}