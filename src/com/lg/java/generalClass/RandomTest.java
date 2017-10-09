package com.lg.java.generalClass;

import java.util.Random;

import org.junit.Test;
/**
 * Random 中封装了随机相关的类
 * Math中封装了常用的数学方法
 * import static java.lang.Math.*;
 * 导入指定类的静态属性和静态方法
 */
public class RandomTest {
	@Test
	public void testMath(){
		System.out.println(Math.sin(Math.PI));
	}
	
	@Test
	public void testRandom() {
		Random random=new Random();
		System.out.println(random.nextInt());
		System.out.println(random.nextInt(10));
		
	}

}
