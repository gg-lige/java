package com.lg.java.thread;

/**
 * 关于线程：
 * 1. java中，Thread类代表一个线程
 * 
 * 2. 实现线程有两种方式：
 * ①继承Thread类
 * ②实现Runnable接口
 * 
 * 3.继承Thread类
 *   必须重写run()方法：里边放置的是实际的线程体
 *   
 * 4.启动线程
 * ①创建Thread对象
 * ②调用start()方法启动线程，而不是run方法
 *
 * 5.实现Runnable接口的方式
 * ①创建实现Runnable接口的实现类：必须实现run()方法
 * ②创建5.1对应Runnable接口的实现类对象
 * ③创建Thread对象，利用 Thread(Runnable target)
 * ④调用Thread类 的start()
 * 
 * 6.线程生命周期的几个方法：
 * ①yield():若当前线程调用该方法，则由执行状态变为可运行状态
 * ②sleep(int mills):使当前线程休眠一段时间，以毫秒为单位
 * ③join:在一个线程中调用另外线程的join方法，将使当前线程 等待另一个线程执行完后在进入可执行状态
 * ④interrupt:将解除线程的阻塞状态
 * ⑤isAlive():可以判断当前线程是否处于可运行状态或执行状态
 * 
 * 7.线程安全的问题：
 * ①理解并编写出线程不安全是示例代码：多个线程访问一个共享资源
 * ②使用synchronized 代码块解决线程安全的问题，锁住共同的对象
 * 
 */
public class ThreadTest {
	public static void main(String[] args) {
		//1.创建线程对象
		Thread thread= new FirstThread("FirstThread");
		
		//2.调用start()方法启动线程
		thread.start();
		
		String threadname=thread.currentThread().getName();
		for (int i = 0; i < 100; i++) {
			System.out.println("~"+threadname+" :"+i);
		}
		
	}
	
}

class FirstThread extends Thread{
	public FirstThread(String name) {
		super(name);
	}
	//线程体在run方法中
	@Override
	public void run() {
		String threadname=Thread.currentThread().getName();
		for (int i = 0; i < 100; i++) {
			System.out.println("-"+threadname+" :"+i);
		}
	}
	
	
}