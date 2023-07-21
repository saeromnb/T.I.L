package helloMemberdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbtest.JDBCUtil;

public class MemberDAO {
	MemberVO getMember(String id){
		Connection conn = null;
		PreparedStatement stmt = null; 
		ResultSet rs = null;
		MemberVO member = null;


		try {

			conn = JDBCUtil.getMySqlConnection();

			String sql = "select * from member where id =?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			rs = stmt.executeQuery();

			while(rs.next()) {
				String pw = rs.getString("pw");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String email = rs.getString("email");
				String date = rs.getString("date");

				member = new MemberVO(id, pw, name, age, email, date);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs,stmt,conn);
		}
		return member;
	}

	int UpdateMember(MemberVO member) {
		Connection conn = null;
		PreparedStatement stmt = null; 
		int rs = 0;

		try {

			conn = JDBCUtil.getMySqlConnection();
			String sql = "update member set pw =?, name=?, age=?, email=?, date=? where id =?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getPw());
			stmt.setString(2, member.getName());
			stmt.setInt(3, member.getAge());
			stmt.setString(4, member.getEmail());
			stmt.setString(5, member.getDate());
			stmt.setString(6, member.getId());
			rs = stmt.executeUpdate(); //성공하면 1 출력
			System.out.println("rs : "+rs);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);

		}
		return rs;
	}
	
	int DeleteMember(String id) {
		Connection conn = null;
		PreparedStatement stmt = null;  //옛날 쿼리창
		int rs = 0;

		try {

			conn = JDBCUtil.getMySqlConnection();
			String sql = "delete from member where id =?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			rs = stmt.executeUpdate(); //성공하면 1 출력
			System.out.println("rs : "+rs);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);

		}
		return rs;
	}

	MemberVO getSearchMember(String name) {
		Connection conn = null;
		PreparedStatement stmt = null; 
		ResultSet rs = null;
		MemberVO member = null;


		try {

			conn = JDBCUtil.getMySqlConnection();

			String sql = "select * from member where name like ?";
//			String sql_oracle = "select * from member where name like '%'||?||'%'";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%"+name+"%");
//			stmt.setString(1, name);  orcle
			rs = stmt.executeQuery();

			while(rs.next()) {
				String id= rs.getString("id");
				String pw = rs.getString("pw");
				name = rs.getString("name");
				int age = rs.getInt("age");
				String email = rs.getString("email");
				String date = rs.getString("date");

				member = new MemberVO(id, pw, name, age,email, date);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt,conn);
		}
		return member;
	}
	
	int insertMember(MemberVO member) {
		Connection conn = null;
		PreparedStatement stmt = null;  //옛날 쿼리창
		int rs = 0;

		try {

			conn = JDBCUtil.getMySqlConnection();
			System.out.println("ok!!");

			String sql = "insert into member(id,pw,name,age, email, date) ";
			sql +="values(?,?,?,?,?,?)";
			//			sql문을 만들고 미리 넣어놔야함
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getId());
			stmt.setString(2, member.getPw());
			stmt.setString(3, member.getName());
			stmt.setInt(4, member.getAge());
			stmt.setString(5, member.getEmail());
			stmt.setString(6, member.getDate());
			rs = stmt.executeUpdate(); //성공하면 1 출력
			System.out.println("rs : "+rs);



		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);

		}
		return rs;
	}
	
	List<MemberVO> getMemberList(){
		Connection conn = null;
		PreparedStatement stmt = null; 
		ResultSet rs = null;
		List<MemberVO> members = new ArrayList<MemberVO>();


		try {

			conn = JDBCUtil.getMySqlConnection();

			String sql = "select * from member order by id";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while(rs.next()) {
				String id = rs.getString("id");
				String pw = rs.getString("pw");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String email = rs.getString("email");
				String date = rs.getString("date");

				MemberVO vo = new MemberVO(id, pw, name, age, email, date);
				members.add(vo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs,stmt,conn);
		}
		return members;
	}
}
