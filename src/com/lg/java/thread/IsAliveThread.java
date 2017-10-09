package com.lg.java.thread;

public class IsAliveThread extends Thread{
	public static void main(String[] args) {
		Thread thread=new IsAliveThread();
		System.out.println(thread.isAlive());
		
		thread.start();
		System.out.println(thread.isAlive());
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(thread.isAlive());
		
		//已经结束的线程，在调用start()方法会抛出异常
		thread.start();
		
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println(getName()+" : "+i);
		}
	}
}
