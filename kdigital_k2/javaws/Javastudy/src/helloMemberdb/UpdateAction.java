package helloMemberdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UpdateAction implements Action{

	@Override
	public void execute(Scanner sc) {
		Connection conn = null;
		PreparedStatement stmt = null;  
		ResultSet rs = null;
		System.out.println("UpdateAction");
		System.out.println("수정할 ID :");
		String id = sc.next();

		String driver = "com.mysql.cj.jdbc.Driver";
		try {
			//1. driver loading
			Class.forName(driver);
			//2. Connection
			String url = "jdbc:mysql://localhost:3306/mysqlhello";
			url += "?ServerTimezone=UTC";
			String user = "root";
			String password = "rpass";

			conn = DriverManager.getConnection(url, user, password);
			System.out.println("ok!!");

			String sql = "select * from member where id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			rs = stmt.executeQuery();

			if(rs.next()) {
				String pw = rs.getString("pw");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				MemberVO member = new MemberVO(id, pw, name, age);
				System.out.println("기존 PW: "+member.getPw());
				System.out.print("수정 PW: ");
				pw = sc.next();
				System.out.println("기존 Name : "+member.getName());
				System.out.print("수정 Name : ");
				name = sc.next();
				System.out.println("기존 Age : "+ member.getAge());
				System.out.print("수정 Age : ");
				age = sc.nextInt();

				sql = "update member set pw =?, name=?, age = ? where id=?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1,pw);
				stmt.setString(2,name);
				stmt.setInt(3,age);
				stmt.setString(4,id);
				stmt.executeUpdate();
			}else {
				System.out.println("수정할 ID가 없습니다.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs !=null) rs.close();
				if(stmt !=null) stmt.close();
				if(conn !=null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}





	}
}