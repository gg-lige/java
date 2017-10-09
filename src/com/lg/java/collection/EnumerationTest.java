package com.lg.java.collection;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.StringTokenizer;

import org.junit.Test;

public class EnumerationTest {
	/**
	 * Enumeration接口是Iterator迭代器的“古老版本”
	 */
	@Test
	public void testEnumeration(){
		Hashtable hashtable=new Hashtable();
		hashtable.put("a", "1");
		hashtable.put("b", "2");
		hashtable.put("c", "5");
		hashtable.put("d", "3");
		
		Enumeration enumeration=hashtable.elements();
		
		while(enumeration.hasMoreElements()){
			Object object=enumeration.nextElement();
			System.out.println(object);
		}
		
		
	
	}
}
