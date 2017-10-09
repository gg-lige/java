package com.lg.java.generalClass;



import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
/**
 * Date封装了日期和时间
 * @author lg
 * DateFormat 吧日期对象格式化为一个字符串 & 把一个字符串转为一个Date对象
 * 1.抽象类获取对象的方式：
 * 1）.创建其子类对象
 * 2）.有的抽象类中提供了静态工厂方法来获取抽象类的实例
 * 
 */
public class DateFormatTest {
	@Test
	public void testSimpleDateFormat() throws ParseException{
		DateFormat dateFormat =new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		
		Date date=new Date();
		System.out.println(dateFormat.format(date));
		
		
		String dString="3553-2-13 12:45:54";
		System.out.println(dateFormat.parse(dString));
	}

	@Test
	public void testDateFormat() {
		DateFormat dFormat=DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.SHORT);
		Date date=new Date();
		String aString= dFormat.format(date);
		System.out.println(aString);
	}
	
	
	@Test
	public void testDate() {
		Date date=new Date();
		System.out.println(date);
	}

}
