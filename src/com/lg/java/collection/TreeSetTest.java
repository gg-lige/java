package com.lg.java.collection;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

import org.junit.Test;

public class TreeSetTest {

	@Test
	public void testTreeSetWithComparator() {
		Comparator comparator= new Comparator() {
			@Override
			public int compare(Object arg0, Object arg1) {
				if(arg0 instanceof Person && arg1 instanceof Person){
					Person p1=(Person) arg0;
					Person p2=(Person) arg1;
					
					return p1.getScore()-p2.getScore();
				}
				throw new ClassCastException("不能转类型");
			}
		};
		TreeSet set =new TreeSet(comparator);
		set.add(new Person("aa",123));
		set.add(new Person("cc",12));
		
		Iterator iterator =set.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
			
		}
	}

}
