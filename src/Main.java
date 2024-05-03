import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("== 프로그램 시작 ==");
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {			
			System.out.println("명령어를 입력하세요: ");
			String cmd = sc.nextLine();
		
			if (cmd.equals("종료")) {
				System.out.println("프로그램을 종료합니다.");
				System.out.println("== 프로그램 끝 ==");
				break;
			}
		}
		sc.close();
	}
}
