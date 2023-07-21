package helloAnimaldb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import dbtest.JDBCUtil;

public class UpdateAction implements Action{

	@Override
	public void execute(Scanner sc) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AnimalVO animal = null;
		AnimalDAO dao = new AnimalDAO();
		System.out.println("UpdateAction");
		System.out.println("수정할 동물 이름 : ");
		String name = sc.next();
		
		
		try {
			conn = JDBCUtil.getMySqlConnection();
			animal = dao.getAnimal(name);
			if(animal != null) {
				System.out.println("기존 Kind: "+animal.getKind());
				System.out.print("수정 Kind: ");
				String kind = sc.next();
				System.out.println("기존 Age : "+animal.getAge());
				System.out.print("수정 Age : ");
				int age = sc.nextInt();
				System.out.println("기존 Sound : "+ animal.getSound());
				System.out.print("수정 Sound : ");
				String sound = sc.next();
				name = animal.getName();
				animal = new AnimalVO(kind, name, age,sound);
				dao.UpdateAnimal(animal);
			}else {
				System.out.println("수정할 동물이 없습니다.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, pstmt, conn);
		}

	}

}
