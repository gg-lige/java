package com.lg.java.collection;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Test;

/**
 * Properties 对应 .properties 属性文件
 * .properties 中存放的是键值对，键值对都是String类型的
 * @author lg
 *
 */
public class PropertiesTest {
	@Test
	public void testProperties() throws IOException{
		//读取jdbc.properties
		//1.创建Properties对象
		Properties properties= new Properties();
		//2.调用Properties的 load()方法加载属性文件对应的输入流
		InputStream inStream =PropertiesTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
		properties.load(inStream);
		//3.调用个体Property（String key）方法获取属性值
		String user=properties.getProperty("user");
		System.out.println(user);
	}
}
