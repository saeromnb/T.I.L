package helloAnimaldb;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dbtest.JDBCUtil;

public class DeleteAction implements Action{

	@Override
	public void execute(Scanner sc) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		AnimalVO animal = null;
		AnimalDAO dao = new AnimalDAO();
		System.out.println("DeleteAction");
		System.out.println("삭제할 동물 이름 : ");
		String name = sc.next();
		
		
		try {
			conn = JDBCUtil.getMySqlConnection();
			animal = dao.getAnimal(name);
			if(animal != null) {
				
				int rs = dao.DeleteAnimal(name);
			}else {
				System.out.println("삭제할 동물이 없습니다.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(pstmt, conn);
		}

		
	}
	

}
