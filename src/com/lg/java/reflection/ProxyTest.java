package com.lg.java.reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

import org.junit.Test;

public class ProxyTest {
	/**
	 * 关于动态代理的细节
	 * 1.需要一个被代理的对象
	 * 2.类加载器通常是和被代理对象使用相同的类加载器
	 * 3.一般的，Proxy.newInstance()的返回值是一个被代理对象实现的接口的类型，也可能是其他的接口类型
	 * 注意：第二个参数，必须是一个接口类型的数组
	 * 提示：若代理对象不需要额外实现被代理对象实现的接口以外的接口，可以使用target.getClass().getInterfaces(),
	 * 4.InvocationHandler 通常使用匿名内部类的方式:被代理对象得为final
	 * 
	 * 5.Object类型的proxy之正在被返回的那个代理对象，一般不使用它
	 */
	@Test
	public void testProxy2(){
		final ArithmeticCalculator target=new ArithmeticCalculatorImpl();
		Object proxy=Proxy.newProxyInstance(target.getClass().getClassLoader(),
				//new Class[] {ArithmeticCalculator.class,Validator.class},
				target.getClass().getInterfaces(),
				new InvocationHandler() {
					
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						// TODO Auto-generated method stub
						return method.invoke(target, args);
					}
				});
		
	}
	
	
	@Test
	public void testProxy(){
		final ArithmeticCalculator arithmeticCalculator=new ArithmeticCalculatorImpl();
		//newProxyInstance(ClassLoader loader,有动态代理产生的对象由那个类加载器来加载，通常情况下和被代理对象使用相同的类加载器
        //Class<?>[] interfaces,由动态代理产生的对象必须需要实现的接口的Class数组，
       // InvocationHandler h,当具体调用代理对象的方法时，将产生什么行为。
		ArithmeticCalculator proxy= 
				(ArithmeticCalculator)Proxy.newProxyInstance(arithmeticCalculator.getClass().getClassLoader(),
				new Class[] {ArithmeticCalculator.class}, new InvocationHandler() {			
			/**
			 * method:正在被调用的方法
			 * args:调用方法时传入的参数
			 */
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				System.out.println("The method "+method.getName()+" begins with "+ Arrays.asList(args));
				
				//调用被代理类的目标方法
				Object result=method.invoke(arithmeticCalculator, args);
				
				System.out.println("The method "+method.getName()+" ends with "+result );
				
				return result;
			}
		});
		proxy.mul(1,4);
		
		int result= proxy.add(5, 2);
		System.out.println(result);
	}
}
