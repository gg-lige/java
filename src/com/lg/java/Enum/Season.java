package com.lg.java.Enum;
/**
 * Season 类只有4个对象，且对象的属性是固定的
 *
 */
public class Season {
	//属性是固定的，说明属性是常量
	private final String SEASON_NAME;
	private final String SEASON_DESC;
	
	//在类的外部不能创建多个对象，所以私有化构造器
	private  Season(String seasonName ,String seasonDesc) {
		this.SEASON_NAME=seasonName;
		this.SEASON_DESC=seasonDesc;
	}
	
	//在类的内部常见4个对象，且这4个对象不能被修改
	public final static Season SPRING =new Season("spring","warm");
	public final static Season SUMMER =new Season("SUMMER","HOT");
	public final static Season AUTUMN =new Season("AUTUMN","COOL");
	public final static Season WINTER =new Season("WINTER","COLD");

	public String getSEASON_NAME() {
		return SEASON_NAME;
	}
	public String getSEASON_DESC() {
		return SEASON_DESC;
	}
	@Override
	public String toString() {
		return "Season [SEASON_NAME=" + SEASON_NAME + ", SEASON_DESC=" + SEASON_DESC + "]";
	}
	
	
	
}
