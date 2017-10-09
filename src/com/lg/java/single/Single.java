package com.lg.java.single;
/**
 * 类的单态设计模式，就是采取一定的方法保证在整个的软件系统中，对某个类只能存在一个对象实例
 * @author lg
 *
 */
public class Single {
	//1.在类的外部不能通过new构造器的方式创建实例。
	//把构造器隐藏起来，即构造器私有化
	private Single(){}
	
	//2.因为在类的外部不能创建类的实例，只能在类的内部创建
	//3.为了让类的外部可以直接使用该实例，使用static修饰。
	//4.不能在类的外部修改该属性：私有化该属性，同时提供公有的get方法来访问。
	private static Single instance = new Single();

	public static Single getInstance() {
		return instance;
	}

	public static void setInstance(Single instance) {
		Single.instance = instance;
	}
	
	
}
