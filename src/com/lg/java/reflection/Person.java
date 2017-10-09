package com.lg.java.reflection;

public class Person {
	String name;
	private int age;
	private void test(){}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setName(String name,Integer age) {
		System.out.println(name);
		System.out.println(age);
		
	}
	public int getAge() {
		return age;
	}
	
	@AgeValidator(min=18,max=30)
	public void setAge(int age) {
		this.age = age;
	}
	/**
	 * 必须构建无参构造器，供反射使用
	 */
	public Person() {
//		System.out.println("无参构造器");
	}
	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
		System.out.println("有参构造器");
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
	
	private String method2() {
		return "private String method2";
	}
	
	private Object method3(String name,Integer age){
		Person person=new Person(name, age);
		return person;
	}
	
	
}
