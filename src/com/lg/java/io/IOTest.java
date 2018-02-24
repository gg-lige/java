package com.lg.java.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.Writer;

import org.junit.Test;

public class IOTest {
	/**
	 * 第二行插入oh~tianna，其余下移
	 * @throws IOException
	 */
	@Test
	public void testRandomAccessFile1() throws IOException{
		RandomAccessFile access= new RandomAccessFile("hello.txt", "rw");
		//先读一行
		String line=access.readLine();
		//把第一行后面的内容先读到一个byte数组中
		byte[] buffer = new byte[(int) (access.length()-line.length())];
		access.read(buffer);
		
		//移动指针到第一行的后面
		access.seek(line.length());
		//写入要写的字符串
		access.writeBytes("\noh~tianna\n");
		//在写入先前的内容
		access.write(buffer);
		access.close();
	}
	
	
	
	
	@Test
	public void testRandomAccessFile() throws IOException {
		//1.创建RandomAccessFile对象
		RandomAccessFile access= new RandomAccessFile("hello.txt", "rw");
		//2.对文件进行读写操作
		String str=null;
	/*	while ((str=access.readLine())!=null) {
			System.out.println(str);			
		}*/
		
		//设置指针
		access.seek(20);
		//向文件结尾添加ohohoh~//向指定位置写入字符串，将原有内容覆盖掉了
		access.writeBytes("ohohoh");
		//3.关闭RandomAccessFile对象
		access.close();
	}
	
	
	@Test
	public void testObjectInputStream() throws IOException, Exception{
		InputStream in=new FileInputStream("e:\\b.txt");
		ObjectInputStream objectInputStream=new ObjectInputStream(in);
		Object obj=objectInputStream.readObject();
		
		System.out.println(obj);
		objectInputStream.close();
		in.close();
	}
	
	
	
	@Test
	public void testSerializable() throws IOException{
		Person person=new Person("aa",35);
		//使用ObjectOutputStream写入磁盘
		OutputStream out =new FileOutputStream("e:\\b.txt");
		ObjectOutputStream objectOutputStream=new ObjectOutputStream(out);
		
		objectOutputStream.writeObject(person);
		
		out.close();
		objectOutputStream.close();
	}	
	
	/**
	 * 先创建两个字节输入输出流，分别指向hello.txt和hello5.txt
	 * 然后再转为字符输入输出流
	 * 再转为带缓冲的字符输入输出流
	 * 
	 * 完成文件的复制
	 * @throws IOException 
	 */
	@Test
	public void testOutputStreamWriter() throws IOException{
		InputStream inputStream=new FileInputStream("hello.txt");
		Reader iReader=new InputStreamReader(inputStream);
		BufferedReader br=new BufferedReader(iReader);
		
		OutputStream outputStream=new FileOutputStream("hello5.txt");
		Writer oWriter =new OutputStreamWriter(outputStream);
		BufferedWriter bw=new BufferedWriter(oWriter);
		
		String str=null;
		int i=0;
		while ((str=br.readLine())!=null) {
			if(i!=0){
				bw.write("\n");
			}
			i++;
			bw.write(str);			
		}
		//输入流从里向外关
		inputStream.close();
		iReader.close();
		br.close();
		//输出流从外向里关
		bw.close();
		oWriter.close();
		outputStream.close();
	}
	
	
	
	
	@Test
	public void testInputStreamReader() throws IOException{
		//指向文档的字节流,字节流可读文本及二进制文件，字符流尽可读文本，效率较高
		InputStream inputStream=new FileInputStream("hello.txt");
		//把上面的字节流转为字符流。（只有字节流转字符流，无字符流转字节流）
		Reader iReader=new InputStreamReader(inputStream);
		//把字符流转为带缓冲的字符流
		BufferedReader bReader =new BufferedReader(iReader);
		String str=null;
		while((str=bReader.readLine())!=null){
			System.out.println(str);
		}
		
		//关闭
	}
	
	
	
	
	/**
	 * 缓冲流
	 *  利用BufferedInputStream和BufferedOutputStream来拷贝hello到hello4,
	 *  不用处理换行，因为会把换行读进去
	 * @throws IOException 
	 */
	@Test
	public void testBufferedInputStreamandBufferedOutputStream() throws IOException{
		InputStream in =new FileInputStream("hello.txt");
		BufferedInputStream bi=new BufferedInputStream(in ) ;
		
		OutputStream out =new FileOutputStream("hello4.txt");
		BufferedOutputStream bo =new BufferedOutputStream(out);
		
		byte[] buffer =new byte[10];
		int len =0;
		
		while ((len=bi.read(buffer))!=-1) {
			bo.write(buffer,0,len);
		}
		
		
		bi.close();
		bo.close();
		
		
	}
	
	
	/**
	 * 缓冲流
	 * 利用BufferedReaderandBufferedWriter来拷贝hello到hello3
	 * @throws IOException 
	 */
	@Test
	public void testBufferedReaderandBufferedWriter() throws IOException{
		//1.创建BufferedReader和BufferedWriter
		Reader in =new FileReader("hello.txt");
		BufferedReader br=new BufferedReader(in);
		
		Writer out =new FileWriter("hello3.txt");
		BufferedWriter bf =new BufferedWriter(out);
		//2.读写操作
		String str =null;
		int i=0;
		while ((str=br.readLine()) !=null) {
			if(i!=0){
				bf.write("\n");
			}
			i++;
			bf.write(str);
		}
		//3.关闭IO流资源,直接关闭包装流，内部会直接关闭节点流
		br.close();
		bf.close();
	}
	
	
	
	
	/**
	 * 
	 * 利用字符输入输出流完成hi文件拷贝
	 * @throws IOException 
	 */
	@Test
	public void testCopyReaderandWriter() throws IOException{
		//1.创建字符输入输出流
		Reader r=new FileReader("hi.txt");
		Writer w= new FileWriter("hi2.txt");
		//2.创建一个字符数组
		char[] buffer = new char[10];
		int len =0;
		//3.利用循环读取源文件，并向目标文件写入
		while ((len = r.read(buffer)) != -1) {
		//4.注意：写入方法为 ,不能直接使用
			w.write(buffer, 0, len);
			System.out.println(len);
		}
		//5.关闭流资源
		r.close();
		w.close();
	}
	
	
	
	

	//测试文件(各种文件)的拷贝
	@Test
	public void testCopyFile() throws IOException {
		//1.创建定位到hello.txt的文件的输入流
		InputStream in =new FileInputStream("hello.txt");
		//2.创建定位到hello2.txt的文件的输出流
		OutputStream out =new FileOutputStream("hello2.txt");
		//3.创建一个byte数组，用于读写文件
		byte[] buffer =new byte[1024*10];
		int len=0;
		
		//4.读写文件
		//in.read(buffer);out.write(buffer,0,len);
		while ((len=in.read(buffer))!=-1) {
			out.write(buffer, 0, len);
			
		}
				
		//5.关闭资源
	}
	
	
	// 测试字节输出流
	@Test
	public void testoutputStream() throws IOException {
		// 1.分次写
		OutputStream outputStream = new FileOutputStream("lg.txt");

		String content = "www.atguigu.com\n\rHello Java!";
	/*	int len = 10;

		byte[] contentBytes = content.getBytes();
		for (int i = 0; i < content.length() / 10; i++) {
			// 1.把String 拆分为多个buffer
			outputStream.write(contentBytes, i * 10, len);

		}
		if (content.length() % 10 != 0) {
			outputStream.write(contentBytes, 10 * (content.length() / 10), content.length()-(10 * (content.length() / 10)));
		}
		*/
		outputStream.write(content.getBytes());
	}

	// 测试字符输入流
	@Test
	public void testReader() throws IOException {
		// 1.利用字符输入流读取hello。txt文档内容，输出到控制台
		Reader reader = new FileReader("hello.txt");

		char[] buffer = new char[10];
		int len = 0;

		while ((len = reader.read(buffer)) != -1) {
			for (int i = 0; i < len; i++) {
				System.out.print(buffer[i]);
			}
		}

		reader.close();
	}

	@Test
	public void testInputStream() throws IOException {
		// 1。创建一个字节输入流
		InputStream inputStream = new FileInputStream("hello.txt");

		// 2.读取文件的内容
		// 2.1第一读取一个字节,效率较低 ，-1表示督导文件结尾
		/*
		 * int result = inputStream.read(); System.out.println((char)result);
		 * while (result !=-1) { System.out.println((char)result); result =
		 * inputStream.read(); }
		 */
		// 2.2一次读取一组：一组字符
		/*
		 * byte [] buffer = new byte[10]; int len = 0;
		 * //返回一次实际读取的字节数，若为-1表示读取到文件的结尾 while
		 * ((len=inputStream.read(buffer))!=-1) { // for (byte b : buffer) { //
		 * System.out.print((char)b); // }
		 * 
		 * for(int i=0;i<len;i++){ System.out.print((char)buffer[i]); }
		 * 
		 * }
		 */
		// 2.3 把内容读取到字节数组的部分连续的元素中
		byte[] result = new byte[1024 * 10];
		inputStream.read(result, 10, inputStream.available());

		// 3.关闭流资源
		inputStream.close();
	}

	/**
	 * File:代表物理意义的文件或目录
	 * 
	 * @throws IOException
	 */
	@Test
	public void testFile() throws IOException {
		// 1.创建File对象
		File file = new File("hello.txt");

		// 2.测试File对象的方法
		// 2.1文件名相关的方法
		String fileName = file.getName();
		System.out.println(fileName);

		// 2.2访问文件的绝对路径
		String path = file.getAbsolutePath();
		System.out.println(path);

		// 3.文件检测相关方法
		System.out.println(file.exists());
		File dir = new File("lg.txt");
		System.out.println(dir.isFile());

		// 4.获取文件常规信息
		System.out.println(file.length());

		// 5.文件操作相关 ??
		File file2 = new File("a.txt");
		file2.createNewFile();

	}
}
