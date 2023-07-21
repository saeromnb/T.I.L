package helloAnimal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeleteAction implements Action{

	@Override
	public void execute(Scanner sc) {
		FileReader fr = null;
		BufferedReader br = null;
		String file = "./data/Animal.txt";
		List<String> sData = new ArrayList<String>();
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String str = null;
			while((str = br.readLine())!=null) {
				sData.add(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				fr.close();
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//String -> AnimalVO
		AnimalVO animal =null;
		List<AnimalVO> animals = new ArrayList<AnimalVO>();
		for(String s:sData) {
			String[] dArr = s.split(",");
			animal = new AnimalVO(dArr[0],dArr[1],Integer.parseInt(dArr[2]),dArr[3]);
			animals.add(animal);
		}
		
		//삭제할 동물 찾기
		System.out.println("삭제할 동물 이름:");
		String name = sc.next();
		int deleteindex = -1;
		for(int i=0;i<animals.size();i++) {
			if(animals.get(i).getName().equals(name)) {
				deleteindex = i;
				break;
			}
		}
		if(deleteindex!=-1) {
			animals.remove(deleteindex);
		}else System.out.println("일치하는 동물 이름이 없습니다.");
		
		//파일에 저장
		FileWriter fw = null;
		try {
			fw = new FileWriter(file);
			for(AnimalVO a: animals) {
				fw.write(a.toString()+"\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	

}
