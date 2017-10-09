package com.lg.java.Enum;

import org.junit.Test;

public class testEnum {
	
	@Test
	public void testEnumAndInterface() {
		System.out.println(Season3.AUTUMN.getInfo());
		
	}
	
	
	@Test
	public void testEnumMethod(){
		//1.遍历枚举类的方法:每个枚举类都有一个values方法
		Season2[] season2s=Season2.values();
		for(Season2 season:season2s){
			System.out.println(season);
		}
		
		//2.把一个字符串转为对应的枚举类对象
		String input ="SPRING";
		//第一个参数：枚举类的类型，第二个参数：对应的字符串
		Season2 S =Enum.valueOf(Season2.class, input);
		System.out.println(S);	
		
	}
	
	
	
	@Test
	public void testSeason(){
		Season season =Season.AUTUMN;
		System.out.println(season);
	}
}
