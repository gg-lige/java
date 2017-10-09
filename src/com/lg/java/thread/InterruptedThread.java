package com.lg.java.thread;

public class InterruptedThread extends Thread{

	public static void main(String[] args) {
		InterruptedThread thread=new InterruptedThread();
		thread.start();
		
		thread.interrupt();
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println(getName()+" : "+i);
			if (i==10) {
				
				try {
					Thread.sleep(100000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
		}
	}
}
