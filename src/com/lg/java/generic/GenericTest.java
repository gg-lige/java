package com.lg.java.generic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

public class GenericTest {

	public int[] twoSum(int[] nums, int target) {
		int[] a=new int[2];
		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length / 2; j++) {
				if (nums[i] + nums[j] == target) {
					a[0]=i;a[1]=j;
				}
			}
		}
		return a;
	}

	public void testGenericMethod() {
		Dao<Person> dao = new Dao<>();

		Person person = dao.get(1);
		String name = person.getName();
		// E为String类型，根据这边的String来确定
		String name2 = dao.getProperty(1);
		// E为int类型。
		int age = dao.getProperty(2);
	}

	@Test
	public void testGenericAndExtends() {
		Object[] objects = new Object[10];
		Object[] persons = new Person[10];
		// Object是Person的父类，则Object的数组也是Person数组的父类
		objects = persons;

		List<Object> objlist = new ArrayList<>();
		List<Person> personList = new ArrayList<>();

		// objlist=personList;

		printPersons(personList);
		List<student> stuList = new ArrayList<>();
		// 不能传入stulist,
		// printPersons(stuList);
		printPersons2(stuList);
	}

	/**
	 * 1.他的元素类型可以匹配任何类型 2.将任意元素加入其中不是类型安全的，null除外
	 * 
	 * @param coll
	 */
	public void printCollection(Collection<?> coll) {
		coll.add(null);
		// coll.add("");
	}

	/**
	 * 带通配符的集合声明 只要存在通配符，写入就是非法的
	 * 
	 * @param persons
	 */
	public void printPersons2(List<? extends Person> persons) {

	}

	public void printPersons(List<Person> persons) {

	}

	@Test
	public void testGenericClass() {
		Dao<Person> dao = new Dao<>();
		Person entity = new Person("dg", 34);
		dao.save(entity);
	}

	@Test
	public void test() {
		Set<Person> persons = new TreeSet<>(new Comparator<Person>() {

			@Override
			public int compare(Person o1, Person o2) {
				return o1.getAge() - o2.getAge();
			}
		});

		persons.add(new Person("a", 12));
		persons.add(new Person("a", 19));
		persons.add(new Person("a", 10));

		Iterator<Person> iterator = persons.iterator();
		while (iterator.hasNext()) {
			Person person = (Person) iterator.next();

			System.out.println(person);
		}
	}

}
