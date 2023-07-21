package helloAnimaldb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Scanner;

import dbtest.JDBCUtil;

public class SearchAction implements Action{

	@Override
	public void execute(Scanner sc) {
		Connection conn = null;
		PreparedStatement pstmt = null;
     	AnimalVO vo = null;
		AnimalDAO dao = new AnimalDAO();
		List<AnimalVO> animal = null;
		System.out.println("SearchAction");
		System.out.println("찾을 동물 이름 : ");
		String name = sc.next();


		try {
			conn = JDBCUtil.getMySqlConnection();
			vo = dao.getAnimal(name);
			if(vo != null) {
				animal = dao.SearchAnimal(name);
				for(AnimalVO a:animal) {
					 System.out.print("Kind: " + a.getKind() +" / ");
			            System.out.print("Name: " + a.getName() +" / ");
			            System.out.print("Age: " + a.getAge() +" / ");
			            System.out.print("Sound: " + a.getSound() );
			            System.out.println();
			}
				
			}else {
				System.out.println(name+" 동물이 없습니다.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(pstmt, conn);
		}



	}

}
