package com.lg.java.socket;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;

import org.junit.Test;

/**
 * 1.Ip和端口的具体意义
 * 1）ip定位网络中的一台主机
 * 2） 端口定义主机的一个网络程序
 * 
 * 2.InetAddress：对象表示网络中的一个地址
 * InetAddress address= InetAddress.getByName("127.0.0.1");
 * 
 * 3.TCP/IP编程
 * 1）.服务其/客户端：客户端发送请求到服务器，服务器接受请求，给予响应到客户端
 * 2）。serversocket
 * 3). socket
 * 
 * 
 * @author lg
 *
 */
public class SocketTest {
	@Test
	public void testURL() throws IOException{
		URL url= new URL("http://127.0.0.1.8080/examples/a.txt");
		System.out.println(url.getPath());
		System.out.println(url.getQuery());
		
		URLConnection urlConnection= url.openConnection();
		System.out.println(urlConnection);
		
		InputStream inputStream=urlConnection.getInputStream();
		OutputStream outputStream=new FileOutputStream("copya.JPG");
		byte[] buffer=new byte[1024];
		int len =0;
		while ((len=inputStream.read(buffer))!=-1) {
			outputStream.write(buffer,0,len);
			
		}
		inputStream.close();
		outputStream.close();
		
		
	}
	
	
	@Test
	public void testSocket() throws IOException{
		InetAddress address= InetAddress.getByName("127.0.0.1");
		//创建socket对象
		Socket socket = new Socket(address, 3553);
		
		//通过输入输出流和服务端交互
	/*	InputStream inputStream=socket.getInputStream();
		BufferedReader reader= new BufferedReader(new InputStreamReader(inputStream));
		System.out.println("=="+reader.readLine());*/
		InputStream inputStream=socket.getInputStream();
		OutputStream outputStream=new FileOutputStream("e:\\a.JPG");
		byte[] buffer=new byte[1024];
		int len =0;
		while ((len=inputStream.read(buffer))!=-1) {
			outputStream.write(buffer,0,len);
			
		}
				
		inputStream.close();
		outputStream.close();
		socket.close();
		
	}
	
	@Test
	public void testServerSocket() throws IOException {
		//创建serversocket对象
		ServerSocket serverSocket= new ServerSocket(3553);
		//接受客户端的请求
		Socket socket=serverSocket.accept();
		
		//通过输入输出流和客户端进行交互
	/*	OutputStream outputStream=socket.getOutputStream();
		PrintWriter writer= new PrintWriter(outputStream);
		writer.write("来自服务器端的问候");*/
		
		InputStream inputStream=new FileInputStream("a.JPG");
		byte[] buffer=new byte[1024];
		int len =0;
		
		OutputStream outputStream= socket.getOutputStream();
		while ((len=inputStream.read(buffer))!=-1) {
			outputStream.write(buffer,0,len);
			
		}
		
		
		outputStream.close();
		inputStream.close();
		//关闭socket资源
		socket.close();
		serverSocket.close();
		
	}
	
	
	@Test
	public void testInetAddress() throws IOException {
		InetAddress address=InetAddress.getByName("www.atguigu.com");
		System.out.println(address);
		
		address=InetAddress.getLocalHost();
		System.out.println(address);
	}
}
