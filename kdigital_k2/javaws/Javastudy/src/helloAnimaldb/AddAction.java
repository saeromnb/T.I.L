package helloAnimaldb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import dbtest.JDBCUtil;

public class AddAction implements Action{

	@Override
	public void execute(Scanner sc) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rs = 0;
		System.out.println("AddAction");
		System.out.println("== 정보 입력 ==");
		
		System.out.println("동물의 종>>");
		String kind = sc.next();
		System.out.println("동물의 이름>>");
		String name = sc.next();
		System.out.println("동물의 나이>>");
		int age = sc.nextInt();
		System.out.println("동물의 울음소리>>");
		String sound = sc.next();
		
		
		
		try {
			AnimalDAO dao = new AnimalDAO();
			AnimalVO animal = new AnimalVO(kind, name, age, sound);
			JDBCUtil.getMySqlConnection();
			rs = dao.InsertAnimal(animal);
			System.out.println("rs :" + rs);
		} catch (Exception e) {
			e.getStackTrace();
		}finally {
			JDBCUtil.close(pstmt, conn);
		}
		
		System.out.printf("%s,%s,%s,%s\n",kind, name, age,sound);
		 
		
	}
	
}
