package com.lg.java.generalClass;

import static org.junit.Assert.fail;

import org.junit.Test;
/**
 * Stringbuffer\Stringbuilder是可以被修改的字符序列
 * @author lg
 * append方法：把字符串加入到以后的字符序列的后面
 * 注意：append()方法的返回值还是当前StringBuilder对象，可以使用方法的连缀
 * 
 * StringBuilder:线程不安全，效率高，推荐使用
 * StringBuffer:线程不安全，效率不高，在多线程程序中使用。
 */

public class Stringbuffertest {
	@Test
	public void testappend(){
		StringBuilder sBuilder=new StringBuilder();
		sBuilder.append("<html>")
				.append("<body>")
				.append("</html>");
		System.out.println(sBuilder);
	}
	
	@Test
	public void testStringbuffer() {
		StringBuffer aBuffer=new StringBuffer("nfaejnvmv");
		aBuffer.replace(1,4, "fff");
		System.out.println(aBuffer);
		
	}

}
