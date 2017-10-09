package com.lg.java.thread;

public class SleepThread extends Thread{
	public static void main(String[] args) {
		Thread thread=new SleepThread();
		thread.start();
	}
	
	@Override
		public void run() {
			for (int i = 0; i < 100; i++) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(getName()+" : "+i);
				
				
			}
		}
}
