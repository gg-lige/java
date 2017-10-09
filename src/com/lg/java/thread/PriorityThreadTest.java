package com.lg.java.thread;

public class PriorityThreadTest extends Thread{

	public static void main(String[] args) {
		Thread t1=new PriorityThreadTest();
		Thread t2=new PriorityThreadTest();
		System.out.println(Thread.currentThread().getPriority());
		System.out.println(t1.getPriority());
		System.out.println(t2.getPriority());
		
		t1.setPriority(MIN_PRIORITY);
		t2.setPriority(MAX_PRIORITY);
		
		t1.start();
		t2.start();
		
		for(int i=0;i<100;i++){
			System.out.println("main : "+i);
		}
		
	}
	
	@Override
	public void run() {
		for(int i=0;i<100;i++){
			System.out.println(getName()+": "+i);
		}
	}
}
