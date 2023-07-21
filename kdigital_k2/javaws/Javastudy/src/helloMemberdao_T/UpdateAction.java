package helloMemberdao_T;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dbtest.JDBCUtil;

public class UpdateAction implements Action{

	@Override
	public void execute(Scanner sc) {
		Connection conn = null;
		PreparedStatement stmt = null;  
		ResultSet rs = null;
		System.out.println("UpdateAction");
		System.out.println("수정할 ID :");
		String id = sc.next();
		MemberVO member = null;
		try {

			conn = JDBCUtil.getMySqlConnection();

			MemberDAO dao = new MemberDAO();
			member = dao.getMember(id);
			if(member != null) {
				System.out.println("기존 PW: "+member.getPw());
				System.out.print("수정 PW: ");
				String pw = sc.next();
				System.out.println("기존 Name : "+member.getName());
				System.out.print("수정 Name : ");
				String name = sc.next();
				System.out.println("기존 Age : "+ member.getAge());
				System.out.print("수정 Age : ");
				int age = sc.nextInt();
				member = new MemberVO(id, pw, name,age);
				dao.UpdateMember(member);
			}else {
				System.out.println("수정할 ID가 없습니다.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
				JDBCUtil.close(rs, stmt, conn);
		}

	}
}