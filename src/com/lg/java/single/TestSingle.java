package com.lg.java.single;

public class TestSingle {
	public static void main(String[] args) {
//		Single single1= new Single();
//		Single single2= new Single();
		
		Single single1= Single.getInstance();
		Single single2= Single.getInstance();
		
		System.out.println(single1==single2);
	}
}
