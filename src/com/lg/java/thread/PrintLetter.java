package com.lg.java.thread;

public class PrintLetter implements Runnable{

	private char c='a';
	
	@Override
	public void run() {
		while (c<='z') {
			print();
		}
	}

	 public synchronized void print(){
		 if (c<='z') {
			
			 System.out.println(Thread.currentThread().getName()+": "+c);
			 c++;
			 notify();
			 try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	 }
	 
	 public static void main(String[] args) {
		Runnable runnable=new PrintLetter();
		
		Thread t1=new Thread(runnable);
		t1.setName("t1");
		
		Thread t2=new Thread(runnable);
		t2.setName("t2");
		
		t1.start();
		t2.start();
	}
}
