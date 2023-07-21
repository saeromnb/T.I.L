package helloMemberdao_T;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;

import dbtest.JDBCUtil;

public class DeleteAction implements Action{

	@Override
	public void execute(Scanner sc) {
		Connection conn = null;
		PreparedStatement stmt = null;  
		System.out.println("UpdateAction");
		System.out.println("삭제할 ID : ");
		String id = sc.next();

		try {
			conn = JDBCUtil.getMySqlConnection();
			MemberDAO dao = new MemberDAO();
			MemberVO member = dao.getMember(id);
			if(member != null) {
				int rs = dao.DeleteMember(id);
				
			}else {
				System.out.println("삭제할 ID가 없습니다.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
				JDBCUtil.close(stmt, conn);
		}
		
	}
}
