package data.ui;

import java.util.Iterator;

public class TestClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String txt=",,,,x";
		String[] arr= txt.split(",");
		System.out.println(arr.length);
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}

	}

}
