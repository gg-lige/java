package com.lg.java.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.Pack200;

import org.junit.Test;

public class CollectionsTest {
	@Test
	public void testCollections(){
		//2.排序的方法
		List list3=new ArrayList<>();
		list3.add(new Person("ss",22));
		list3.add(new Person("bb",23));
		list3.add(new Person("rr",56));
		list3.add(new Person("jj",2));
		for(Object obj:list3){
			System.out.println(obj);
		}
		System.out.println();
		
		//按年龄升序排
		Collections.sort(list3,new Comparator() {
			@Override
			public int compare(Object o1, Object o2) {
				if(o1 instanceof Person &&o2 instanceof Person){
					Person p1= (Person) o1;
					Person p2=(Person) o2;
					return p1.getScore()-p2.getScore();
				}
				throw new ClassCastException("不能转类型");
			}			
		});
		for(Object obj:list3){
			System.out.println(obj);
		}
		
		//3.获取List中最小的那个元素
		//要求集合中的元素都实现Comparable接口
		Set set=new HashSet<>();
		set.add(new Person("ss",22));
		set.add(new Person("bb",23));
		set.add(new Person("rr",56));
		set.add(new Person("jj",2));
		Object min=Collections.min(set);
		System.out.println(min);
		
		//1.获取线程安全的集合
		/**
		 * ArrayList\HashList\HashMap...都不是线程安全的
		 * 如何得到一个线程安全的集合对象呢
		 * 
		 * 调用Collections的synchronizedList的方法获取线程安全的集合对象
		 */
		List list =new ArrayList();
		List list2 = Collections.synchronizedList(list);
	}
}
