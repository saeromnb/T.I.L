package helloMemberdao_T;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Scanner;

import dbtest.JDBCUtil;

public class SearchAction implements Action{

	@Override
	public void execute(Scanner sc) {
		Connection conn = null;
		PreparedStatement stmt = null;  
		System.out.println("SearchAction");
		System.out.println("검색할 Name : ");
		String name = sc.next();
		MemberVO member = null;
		try {
			conn = JDBCUtil.getMySqlConnection();
			MemberDAO dao = new MemberDAO();
			member = dao.getSearchMember(name);
			if(member != null) {
				System.out.println(member.toString());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {

		}

	}

}
