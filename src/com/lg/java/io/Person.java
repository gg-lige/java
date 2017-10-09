package com.lg.java.io;

import java.io.Serializable;

public class Person implements Serializable {
	/**
	 * 类的版本号：用于对象的序列化，具体用于读取对象时比对磁盘桑对象的版本和程序中对象的版本号一致，若不一致，则读取失败，并抛出异常
	 */
	private static final long serialVersionUID = 2L;
	private String name2;
	private int age;
	public String getname2() {
		return name2;
	}
	public void setname2(String name2) {
		this.name2 = name2;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Person(String name2, int age) {
		super();
		this.name2 = name2;
		this.age = age;
	}
	public Person() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Person [name2=" + name2 + ", age=" + age + "]";
	}
	
}
