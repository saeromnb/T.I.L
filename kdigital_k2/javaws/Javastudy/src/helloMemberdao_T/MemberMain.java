package helloMemberdao_T;

import java.util.Scanner;

public class MemberMain {
	
//	static MemberVO[] members = new MemberVO[0];
//	static List<MemberVO> members = new ArrayList<MemberVO>();
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.println("==메뉴 선택==");
			System.out.println("1. 회원가입");
			System.out.println("2. 회원 목록 보기");
			System.out.println("3. 회원 정보 수정");
			System.out.println("4. 회원 삭제");
			System.out.println("5. 회원 검색");
			System.out.println("q. 끝내기");
			
			String command = sc.next();
			MemberService ms = new MemberService();
			Action action =null;
			
			switch (command) {
			case "1":
				action = new AddAction();
				ms.process(action, sc);
				break;
			case "2":
				action = new ListAction();
				ms.process(action, sc);
				break;
			case "3":
				action = new UpdateAction();
				ms.process(action, sc);
				break;
			case "4":
				action = new DeleteAction();
				ms.process(action, sc);
				break;
			case "5":
				action = new SearchAction();
				ms.process(action, sc);
				break;
			
			}
			if(command.equals("q")) 
			
			break;
			
		}

	}

}
