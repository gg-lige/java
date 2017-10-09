package com.lg.java.collection;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

public class MapTest {
	@Test
	public void testTreeMap() {
		Map map = new LinkedHashMap();
		map.put( new Person("cc", 1),"cc");
		map.put( new Person("bb", 4),"bb");
		map.put( new Person("aa", 3),"aa");
		Iterator it = map.keySet().iterator();
		while (it.hasNext()) {
			Object key = it.next();
			Object val = map.get(key);
			System.out.println(key + ":" + val);

		}
		System.out.println();

		Comparator comparator = new Comparator() {
			@Override
			public int compare(Object arg0, Object arg1) {
				if (arg0 instanceof Person && arg1 instanceof Person) {
					Person p1 = (Person) arg0;
					Person p2 = (Person) arg1;

					return p1.getScore() - p2.getScore();
				}
				throw new ClassCastException("不能转类型");
			}
		};
		Map map2 = new TreeMap(comparator);
		map2.put( new Person("cc", 1),"cc");
		map2.put( new Person("bb", 4),"bb");
		map2.put( new Person("aa", 3),"aa");
		 it = map2.keySet().iterator();
		while (it.hasNext()) {
			Object key = it.next();
			Object val = map2.get(key);
			System.out.println(key + ":" + val);

		}

	}
	
	@Test
	public void testLinkedHshMap(){
		Map map = new LinkedHashMap();
		map.put( new Person("cc", 1),"cc");
		map.put( new Person("bb", 4),"bb");
		map.put( new Person("aa", 3),"aa");
		Iterator it = map.keySet().iterator();
		while (it.hasNext()) {
			Object key = it.next();
			Object val = map.get(key);
			System.out.println(key + ":" + val);

		}		
	}
}
