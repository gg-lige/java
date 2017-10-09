package com.lg.java.thread;

public class PrintNumber {
	public static void main(String[] args) {
		int i=0;
		NumberThread thread1=new NumberThread("t1");
		NumberThread thread2=new NumberThread("t2");
		
		thread1.setI(i);
		
		thread1.start();
		thread2.start();
	}
}

class NumberThread extends Thread{
	public NumberThread(String name) {
		super(name);
	}
	
	private static int i;
	public static void setI(int i) {
		NumberThread.i = i;
	}
	
	@Override
	public void run() {
		for (; i < 100; i++) {
			System.out.println(getName()+" :"+i);
		}
	}
}