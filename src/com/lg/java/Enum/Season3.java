package com.lg.java.Enum;

public enum Season3 implements Info{
	//枚举类的实例需要在枚举类的第一行列出
	SPRING("spring","warm"){
		@Override
		public String getInfo() {
			return "A++";
		}
	},
	SUMMER("SUMMER","HOT"),
	AUTUMN("AUTUMN","COOL"),
	WINTER("WINTER","COLD");
	
	//不希望修改属性时
	final String SEASON_NAME;
	final String SEASON_DESC;
	
	
	private Season3(String sEASON_NAME, String sEASON_DESC) {
		SEASON_NAME = sEASON_NAME;
		SEASON_DESC = sEASON_DESC;
	}


	@Override
	public String getInfo() {
		switch (this) {
		case SPRING:
			return "A";
		case SUMMER:
			return "B";
		case AUTUMN:
			return "C";
		case WINTER:
			return "D";
		}
		return null;
	}
	
	
}
