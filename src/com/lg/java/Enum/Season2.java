package com.lg.java.Enum;

public enum Season2 {
	//枚举类的实例需要在枚举类的第一行列出
	SPRING("spring","warm"),
	SUMMER("SUMMER","HOT"),
	AUTUMN("AUTUMN","COOL"),
	WINTER("WINTER","COLD");
	
	//不希望修改属性时
	final String SEASON_NAME;
	final String SEASON_DESC;
	
	
	private Season2(String sEASON_NAME, String sEASON_DESC) {
		SEASON_NAME = sEASON_NAME;
		SEASON_DESC = sEASON_DESC;
	}
	
	
}
