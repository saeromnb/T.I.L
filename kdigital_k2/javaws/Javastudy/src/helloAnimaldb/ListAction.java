package helloAnimaldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dbtest.JDBCUtil;

public class ListAction implements Action{

	@Override
	public void execute(Scanner sc) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<AnimalVO> animals = new ArrayList<AnimalVO>();
		
		try {
			JDBCUtil.getMySqlConnection();
			AnimalDAO dao = new AnimalDAO();
			animals = dao.getAnimalList();
			
			System.out.println("등록된 동물 수 : "+ animals.size()+"\n");
			for(AnimalVO a:animals) {
				 System.out.print("Kind: " + a.getKind() +" / ");
		            System.out.print("Name: " + a.getName() +" / ");
		            System.out.print("Age: " + a.getAge() +" / ");
		            System.out.print("Sound: " + a.getSound() );
		            System.out.println();
		}

		}catch(Exception e) {
			e.getStackTrace();
		}finally {
			JDBCUtil.close(rs,pstmt, conn);
		}



}


}
