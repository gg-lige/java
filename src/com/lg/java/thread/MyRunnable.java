package com.lg.java.thread;

public class MyRunnable implements Runnable {

	int i=0;
	@Override
	public void run() {
		for( ; i< 100 ; i++){
			System.out.println(Thread.currentThread().getName()+" ： "+i);
		}
	}

	public static void main(String[] args) {
		MyRunnable mRunnable=new MyRunnable();
		
		Thread t1= new Thread(mRunnable);
		Thread t2= new Thread(mRunnable);
		
		t1.start();
		t2.start();
	}
	
}
