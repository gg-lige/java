package com.lg.java.exception;

import java.util.Arrays;
import java.util.List;

public class TestUserNotExistException {

	public static void main(String[] args) {
		String user="Tom";
		
		Object result =getUser(user);
		if(result==null){
			throw new UserNotFoundException("用户不存在");
		}
	}

	public static Object getUser(String user) {
		List<String> users =Arrays.asList("aa","bb","cc");
		
		if(users.contains(user)){
			return true;
		}
		return null;
	}
}
