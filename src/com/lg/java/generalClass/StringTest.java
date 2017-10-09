package com.lg.java.generalClass;

import java.util.Iterator;

import org.junit.Test;

import com.lg.java.io.Person;
/**
 * 1.String 是不可变字符串
 * 2.字符串缓冲池：直接通过=为字符串赋值，会先在缓冲池中查找有没有一样的字符串，
 *   若有，就把其引用付给字符串变量，否则，会创建一个新的字符串，并把对应的字符串放到缓冲池中
 * 3.字符串的常用方法
 * 3.1去除前后空格的trim()方法
 * 3.2求子字符串的方法subString()
 * subString(fromIndex):从fromIndex开始，包含frOmIndex,且字符索引从0开始;
 * subString(fromIndex，toIndex)：【fromIndex，toIndex)
 * 4.indexOf：求指定字符的索引
 * 5.split(String regex)：把字符串拆分为字符串数组
 * 6.比较字符串内容是否相等必须使用equals,不能使用==
 */

public class StringTest {
	@Test
	public void testsplit(){
		String aString="aaa-freg-g-afef";
		String[] value=aString.split("-");
		for (String b : value) {
			System.out.println(b);
			
		}
	}
	
	@Test
	public void testIndexOf(){
		String name="http://fhiegfer/ggd.com";
		int begin=name.indexOf("//")+2;
		int end =name.lastIndexOf('/');
		System.out.println(name.substring(begin, end));
		
	}
	
	
	@Test
	public void testSubstring(){
		String name="http://fhie/gfer/ggd.com";
		
		String subname=name.substring(5);
		System.out.println(subname);
		String subname2=name.substring(3, 6);
		System.out.println(subname2);
	}
	
	@Test
	public void testTrim(){
		String aString="  ga ga   ";
		System.out.println(aString.trim());
	}
	
	@Test
	public void testNewString(){
		String aString="hello";
		String bString="hello";
		
		System.out.println(aString==bString);//t
		System.out.println(aString.hashCode());
		System.out.println(bString.hashCode());
		
		String cString=new String("dafa");
		String dString=new String("dafa");
		System.out.println(cString==dString);//f	
	}
	
	@Test
	public void test() {
		String string="hello java!";
		String reString=string.replace('a', 'p');
		
		System.out.println(string);
		System.out.println(reString);
	}

	@Test
	public void testPassRef(){
		Person person=new Person("aa", 11);
		System.out.println(person);
		
		changePerson(person);
		System.out.println(person);
		
	}

	public void changePerson(Person person) {
		person.setname2("lg");
	}
	public void changeString(String str) {
		str.replace('a', 'b');
	}
}
