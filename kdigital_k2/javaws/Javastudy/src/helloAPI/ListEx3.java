package helloAPI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListEx3 {

	public static void main(String[] args) {
		Shape c1 = new Circle(5);
		Shape c2 = new Circle();
		Shape r1 = new Rectangle();
		Shape r2 = new Rectangle();
		Shape[] sArr = new Shape[4];
		ArrayList<Shape> list = new ArrayList<Shape>();
		list.add(c1);
		list.add(c2);
		list.add(r1);
		list.add(r2);
		for(int i=0;i<list.size(); i++) {
			sArr[i]=list.get(i);
		}
		System.out.println(sArr.length);
		System.out.println(list.size());
		double area1 = c1.area();
		List<Shape>list2 = new ArrayList<Shape>();
		
	}

}
