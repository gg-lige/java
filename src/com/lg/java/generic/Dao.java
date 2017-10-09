package com.lg.java.generic;
/**
 * 泛型类的声明
 * @author lg
 *
 * @param <T>
 */
public class Dao<T> {
	//在泛型类中使用类声明的泛型
	public void save(T entity){
		
	}
	public T get(int id){
		T result=null; 
		return result; 
	}
/**
 * 在类中使用泛型方法：在方法的返回值前面使用<>声明泛型类型.则在方法的参数，方法体中均可使用该类型 
 * <E>声明类型， E返回类型
 * @param id
 * @return
 */
	public <E> E getProperty(int id) {
		return null;
	}
	
	public <E> void test(E entity){
		
	}
	
	
}
