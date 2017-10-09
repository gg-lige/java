package com.lg.java.exception;

public class UserNotFoundException extends RuntimeException{

	/**
	 * 自己定义的异常一般这样定义，通常继承 RuntimeException
	 */
	private static final long serialVersionUID = 1L;
	
	public  UserNotFoundException() {		
	}
	
	public  UserNotFoundException(String msg) {		
		super(msg);
	}
}
