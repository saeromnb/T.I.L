package helloAnimal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListAction implements Action{

	@Override
	public void execute(Scanner sc) {
		FileReader fr=null;
		BufferedReader br = null;
		FileWriter fw = null;
		String file = "./data/Animal.txt";
		String file1="./data/AnimalList.txt";
		List<String> animals = new ArrayList<String>();
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String str = null;
			while ((str=br.readLine())!=null) {
				animals.add(str);
			}
			fw = new FileWriter(file1);
			String num = "등록된 동물 수 : "+animals.size()+"\n";
			fw.write(num);
			for(String a:animals) {
				String[] dArr = a.split(",");
				fw.write("Kind : "+ dArr[0]+"Name : " +dArr[1]+"Age : "+dArr[2]+"Sound : " + dArr[3]+"\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				fr.close();
				br.close();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}





	}


}
