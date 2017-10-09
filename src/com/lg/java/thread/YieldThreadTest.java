package com.lg.java.thread;

public class YieldThreadTest extends Thread {

	public static void main(String[] args) {
		Thread t1=new YieldThreadTest("t1");
		Thread t2=new YieldThreadTest("t2");
		
		t1.start();
		t2.start();
		
	}
	
	public YieldThreadTest(String threadname) {
		super(threadname);
	}
	
	@Override
	public void run() {
		for(int i=0;i<100;i++){
			System.out.println(getName()+": "+i);
			
			if(i==10){
				yield();
			}
		}
	}
	
}
