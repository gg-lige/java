package com.lg.java.collection;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ListTest {
	/**
	 * 可变参数，形参长度可变的参数。...位于变量类型和变量名之间
	 */
	public void test(String... args) {
		System.out.println(args.length);
	}

	@Test
	public void testArraysAsList() {
		test("aa", "bb", "cc");
		/**
		 * 1.Arrays.asList返回的是一个List对象，但不是ArrayList,也不是Vector
		 * 
		 * 2.Arrays.asList返回的List是一个只读的List
		 */
		List list = Arrays.asList("aa", "bb", "cc");
		System.out.println(list);
	}
}
