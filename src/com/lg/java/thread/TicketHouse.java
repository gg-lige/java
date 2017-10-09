package com.lg.java.thread;

public class TicketHouse implements Runnable {

	private int fiveCount=1, tenCount=0, twentyCount=0;
	
	public synchronized void buy(){
		String name=Thread.currentThread().getName();
		
		//zf:20
		if("zf".equals(name)){
			if (fiveCount<3) {
				try {
					System.out.println("5元面值： "+fiveCount+" ,zf必须等待");
					wait();
					System.out.println("5元面值： "+fiveCount+" ,卖一张票给zf");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}		
		}else if("gy".equals(name)){
			fiveCount++;
			System.out.println("买一张票给gy，钱正好，5元面值： "+ fiveCount);
		}else if ("lb".equals(name)) {
			fiveCount++;
			System.out.println("买一张票给lb，钱正好，5元面值： "+ fiveCount);
		}
		if (fiveCount==3) {
			notifyAll();
		}
	}
	
	@Override
	public void run() {
		buy();
	}
	
	public static void main(String[] args) {
		Runnable runnable=new TicketHouse();
		Thread thread1=new Thread(runnable);
		thread1.setName("zf");
		Thread thread2=new Thread(runnable);
		thread2.setName("gy");
		Thread thread3=new Thread(runnable);
		thread3.setName("lb");
		
		thread1.start();
		thread2.start();
		thread3.start();
		
		
	}

}
