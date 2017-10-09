package com.lg.java.thread;

public class ShareApple implements Runnable {
	private int appleCount=5;
	
	public static void main(String[] args) {
		ShareApple apple=new ShareApple();
		Thread a=new Thread(apple);
		Thread b=new Thread(apple);
		a.setName("明");
		b.setName("红");
		
		a.start();
		b.start();
	}
	
	@Override
	public void run() {
		boolean flag= getApple();
		while (flag) {
			flag= getApple();
		}
		System.out.println(Thread.currentThread().getName()+" 线程结束了");
	}

	public boolean getApple() {
		synchronized (this) {
			
	
		if(appleCount>0){			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println(Thread.currentThread().getName()+"拿走了一个苹果,还剩下"+appleCount+"个");
			appleCount--;
			return true;
		}
		return false;
		}
	}
	
}
