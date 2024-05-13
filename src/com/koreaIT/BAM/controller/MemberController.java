package com.koreaIT.BAM.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.koreaIT.BAM.dto.Member;
import com.koreaIT.BAM.util.Util;

public class MemberController extends Controller {

	private List<Member> members;
	
	public MemberController(Scanner sc) {
		this.sc = sc;
		this.members = new ArrayList<>();
		this.number = 1;
	}
	
	@Override
	public void doAction(String cmd, String methodName) {
		switch(methodName) {
		case "join" :
			doJoin();
			break;
		case "login" :
			doLogin();
			break;
		default :
			System.out.println("존재하지 않는 명령어 입니다.");
		}
	}
	
	public void doJoin() {
		String loginId = null;
		String loginPw = null;
		String loginPwChk = null;
		String name = null;
		
		
		while (true) {
			System.out.println("아이디: ");
			loginId = sc.nextLine().trim();
			
			if (loginId.length() == 0) {
				System.out.println("아이디는 필수 입력정보입니다.");
				continue;
			}
			if (loginIdDupChk(loginId) == false) {
				System.out.println("[" + loginId + "] 은(는) 이미 사용중인 아이디입니다.");
				continue;
			}
			System.out.println("[" + loginId + "] 은(는) 사용 가능한 아이디 입니다.");
			break;
		}
		
		while (true) {
			System.out.println("비밀번호: ");
			loginPw = sc.nextLine().trim();
			
			if (loginPw.length() == 0) {
				System.out.println("비밀번호는 필수 입력정보입니다.");
				continue;
			}
			System.out.printf("비밀번호 확인 : ");
			loginPwChk = sc.nextLine().trim();
			
			if (loginPw.equals(loginPwChk) == false) {
				System.out.println("비밀번호를 다시 입력해주세요.");
				continue;
			}
			break;
		}
		
		while (true) {
			System.out.printf("이름 : ");
			name = sc.nextLine().trim();
			
			if (name.length() == 0) {
				System.out.println("이름은 필수 입력정보입니다");
				continue;
			}
			break;
		}
		
		Member member = new Member(number, Util.getDateStr(), loginId, loginPw, name);
		members.add(member);
		
		System.out.println("[" + loginId + "] 회원님의 가입이 완료되었습니다");
		number++;
	}
	
	public void doLogin() {
		System.out.println("아이디: ");
		String loginId = sc.nextLine();
		System.out.println("비밀번호: ");
		String loginPw = sc.nextLine();
		
		Member foundMember = null;
		
		for (Member member : members) {
			if (member.getLoginId().equals(loginId)) {
				foundMember = member;
				break;
			}
		}
		
		if(foundMember == null) {
			System.out.println("존재하지 않는 아이디입니다.");
			return;
		}
		
		if (foundMember.getLoginPw().equals(loginPw) == false) {
			System.out.println("비밀번호를 확인해주세요.");
			return;
		}
		System.out.println("로그인 성공!");
	}
	
	private boolean loginIdDupChk(String loginId) {
		for (Member member : members) {
			if (member.getLoginId().equals(loginId)) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public void makeTestData() {
		System.out.println("테스트용 게시글 데이터를 3개 생성했습니다");
		for (int i = 1; i <= 3; i++) {
			members.add(new Member(number++, Util.getDateStr(), "User" + i, "User" + i, "유저" + i));
		}
	}
}
