package com.lg.java.reflection;



import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;

import org.junit.Test;

public class ReflectionTest {
	/**
	 * 通過反射，获取定义Class时声明的父类的泛型参数的类型
	 * 如：public  EmployeeDao extends BaseDao<Employee,String>
	 * @param clazz 子类对应点class对象
	 * @param index 子类继承父类时传入的泛型的索引，从0开始
	 * @return
	 */
	public static Class getSuperclassGentricType(Class clazz, int index) {
		Type type=clazz.getGenericSuperclass();
		
		if(!(type instanceof ParameterizedType)){
			return null;}
		ParameterizedType parameterizedType=(ParameterizedType) type;
		Type[] args=parameterizedType.getActualTypeArguments();
		
		if(args==null){return null;}
		if(index<0||index>args.length-1){ return null;}
		
		Type arg=args[index];
		if(arg instanceof Class){
			return  (Class) arg;
		}
		return null;
	}
	
	@Test
	public void testGetSupeclassGenericType() {
		Class clazz=EmployeeDao.class;
		Class argClass= getSuperclassGentricType(clazz,0);//Employee.class
		System.out.println(argClass);
		
		argClass= getSuperclassGentricType(clazz,1);//String.class
		System.out.println(argClass);
		
	}
	
	


	@Test
	public void testGenericAndReflection() {
		PersonDao pDao=new PersonDao();
		Person entity=new Person();
		pDao.save(entity);
		Person result=pDao.get(1);
}
	
	/**
	 * Annotation和反射：
	 * 1.获取Annotation
	 * getAnnotation(Class<T> annotationClass)
	 * getDeclaredAnnotation()
	 *  
	 * @throws Exception
	 */
	@Test
	public void testAnnotation() throws Exception {
		String classname="com.lg.java.reflection.Person";
		Class clazz=Class.forName(classname);
		
		Object object=clazz.newInstance();
		
		Method method=clazz.getDeclaredMethod("setAge", int.class);
		int val=22;
		Annotation annotation=method.getAnnotation(AgeValidator.class);
		if (annotation!=null) {
			if(annotation instanceof AgeValidator){
				AgeValidator ageValidator=(AgeValidator) annotation;
				if(ageValidator.min()>val ||ageValidator.max()<val){
					throw new RuntimeException("非法年龄");
				}
			}
				
		} 
		method.invoke(object, 23);
		System.out.println(object);
	}
	
	
	@Test
	public void testClassField() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		String classname="com.lg.java.reflection.Student";
		String fieldname="age"; //可能为私有，可能在其父类中
		Object val=20;
		
		//创建classname对应类的对象，并为其fieldname赋值为val
		Object object=null;
		
		Class clazz=Class.forName(classname);
		Field field=null;
		for (Class clazz2=clazz; clazz2 != Object.class; clazz2 = clazz2.getSuperclass()) {
			try {
				field = clazz2.getDeclaredField(fieldname);
			} catch (Exception e) {
			}
		}
		object=clazz.newInstance();
		field.setAccessible(true);
		field.set(object, val);
		
		Student student=(Student)object;
		System.out.println(student);
		
		
		
		
	}
	
	/**
	 * Field：封裝了字段的信息
	 * 1.获取字段
	 * 1.1	Field[] fields=clazz.getDeclaredFields();
	 * 1.2  Field field=clazz.getDeclaredField("name");
	 * 
	 * 2.获取指定对象的指定字段的值
	 *  public Object get(Object obj)
	 *  obj为字段所在的对象 
	 *  
	 *  Object val=field.get(person);
	 *  注意若该字段为私有的，需要调用setAccessible(true);
	 *  
	 *  3.设置指定对象的指定字段的值
	 *  public void set(Object obj, Object value);
	 *  obj字段所在的对象
	 *  value要设置的值
	 *  field.set(person,"fag");
	 */
	@Test
	public void testField() throws ClassNotFoundException, NoSuchFieldException, Exception {
		String className="com.lg.java.reflection.Person";
		Class clazz=Class.forName(className);
		
		//1.获取字段
		//1.1 获取Field的数组
		Field[] fields=clazz.getDeclaredFields();
		for (Field field : fields) {
			System.out.println(field.getName());
		}
		
		//1.2获取指定名字的field
		Field field=clazz.getDeclaredField("name");
		System.out.println(field.getName());
		
		Person person=new Person("lg", 3);
		//2获取指定对象的Field值
	//	Object val=field.get(person);
	//	System.out.println("!!"+val);
		
		//1.3设置指定对象的field值
	//	field.set(person, "www");
	//	System.out.println("--"+person.getName());
		
		//1.4若该字段是私有的，需要调用setAccessiable(true)方法
		Field field2=clazz.getDeclaredField("age");
		field2.setAccessible(true);
		System.out.println(field2.get(person));
	}
	
	//======================================================
	@SuppressWarnings("unchecked")
	@Test
	public void testClassMethod() throws Exception {
		// 1.全类名
		String classname = "com.lg.java.reflection.Person";
		// 2.可能在1给的类中，也可能在父类，可能私有方法，可公有，
		String methodname = "method3";
		// 3.执行2对应的方法时需要传入的参数列表
		Object[] args = { "lg", 22 };

		// 根据以下条件，执行methodName对应的方法，并打印返回值
		// 1.加载classname对应的类获取Class对象clazz，
		Class clazz = Class.forName(classname);

		// 2.调用clazz的getDeclaredMethod()方法获取2对应的Method方法
		// 注意：
		// 2.1 因为该方法可能不在当前类中，所以有可能去父类中获取
		// 2.2 因为给定的条件中可能没有给定参数类型的列表，需要从args中获取参数类型列表
		Class[] parameterTypes = new Class[args.length];
		for (int i = 0; i < args.length; i++) {
			parameterTypes[i] = args[i].getClass();
		}
		Method method = null;
		for (Class clazz2=clazz; clazz2 != Object.class; clazz2 = clazz2.getSuperclass()) {
			try {
				method = clazz2.getDeclaredMethod(methodname, parameterTypes);
			} catch (Exception e) {
			}
		}

		// 3.因为method可能是私有的，所以设置setAccessible为true
		method.setAccessible(true);
		// 4.调用method方法
		// 4.1 利用1得到的clazz先创建className对应类的一个对象
		Object object=clazz.newInstance();
		
		// 4.2在调用Method的invoke方法执行方法
		Object result=method.invoke(object, args);
		System.out.println(result);
	}

	@Test
	public void testInvoke2() {
		Object object = new Student();
		invoke2(object, "method1", 10);// Student 的method1被调用，打印：private void
										// method1

		Object result = invoke2(object, "method2");
		System.out.println(result);
	}

	public Object invoke2(Object object, String MethodName, Object... args) {
		// 1.获取Method对象
		Class[] parameterTypes = new Class[args.length];
		for (int i = 0; i < args.length; i++) {
			parameterTypes[i] = args[i].getClass();
		}
		try {
			// 2.执行Method方法
			Method method = getMethod(object.getClass(), MethodName, parameterTypes);
			method.setAccessible(true);
			// 3.返回方法的返回值
			return method.invoke(object, args);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 获取clazz的methodName方法，该方法可能是私有方法，还可能在父类中（私有方法）
	 * 
	 * @param clazz
	 * @param methodName
	 * @param parameterTypes
	 * @return
	 */
	public Method getMethod(Class clazz, String methodName, Class... parameterTypes) {
		for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
			Method method;
			try {
				method = clazz.getDeclaredMethod(methodName, parameterTypes);
				return method;
			} catch (Exception e) {
			}
		}

		return null;
	}

	@Test
	public void testGetMethod() throws Exception {
		Class clazz = Class.forName("com.lg.java.reflection.Student");

		Method method = getMethod(clazz, "method1", Integer.class);
		System.out.println(method);

		method = getMethod(clazz, "method2");
		System.out.println(method);
	}

	/**
	 * 若通过method的invoke()方法调用方法，而访问权限不足，则可以先使该方法变为可访问的
	 * method.setAccessible(true);
	 * 
	 * @throws Exception
	 */
	@Test
	public void testInvokePrivateMethod() throws Exception {
		Object object = new Student();
		Class clazz = object.getClass();

		Method method = clazz.getDeclaredMethod("method1", Integer.class);
		System.out.println(method);

		// 若需要反射执行私有方法，
		method.setAccessible(true);

		method.invoke(object, 23);
	}

	/**
	 * 获取当前类的父类 直接调用Class对象的getSuperClass()方法
	 * 
	 * @throws ClassNotFoundException
	 */
	@Test
	public void testSuperClass() throws ClassNotFoundException {
		String clname = "com.lg.java.reflection.student";
		Class clazz = Class.forName(clname);
		Class superclazz = clazz.getSuperclass();
		System.out.println(superclazz);
	}

	@Test
	public void testInvoke() {
		Object object = new Person();
		invoke(object, "setName", "cnsd", 23);

		invoke("com.lg.java.reflection.Person", "setName", "dfsa", 3);

		Object result = invoke("java.text.SimpleDateFormat", "format", new Date());

	}

	/**
	 * 
	 * @param className
	 *            某个类的全类名
	 * @param MethodName
	 *            类的一个方法的方法名，该方法也可能是私有方法
	 * @param args
	 *            调用该方法需要传入的参数
	 * @return 调用方法后的返回值
	 */
	public Object invoke(String className, String MethodName, Object... args) {
		Object object = null;
		try {
			object = Class.forName(className).newInstance();
			invoke(object, MethodName, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param object
	 *            方法执行的那个对象
	 * @param MethodName
	 *            类的一个方法的方法名，该方法也可能是私有方法
	 * @param args
	 *            调用该方法需要传入的参数
	 * @return 调用方法后的返回值
	 */
	public Object invoke(Object object, String MethodName, Object... args) {
		Class[] parameterTypes = new Class[args.length];
		for (int i = 0; i < args.length; i++) {
			parameterTypes[i] = args[i].getClass();
		}
		// 1.获取Method对象
		try {
			// 2.执行Method方法
			Method method = object.getClass().getDeclaredMethod("setName", parameterTypes);
			// 3.返回方法的返回值
			return method.invoke(object, args);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * class是对一个类的描述 类的属性：Field 类的方法：Method 类的构造器：Constructor
	 * 
	 * Method对应类中的方法： 1.1获取类的方法的数组：clazz.getDeclaredFields()
	 * 1.2获取类的指定方法：clazz.getDeclaredMethods(String name, 方法名
	 * Class<?>...paramaterTypes) 方法的参数类型列表，用Class来描述 1.3通过method对象方法执行方法 public
	 * Object invoke(Object obj, 执行那个对象的方法 Object... args) 执行方法时需要传入的参数
	 * 
	 * 
	 * @throws ClassNotFoundException
	 * @throws Exception
	 * @throws NoSuchMethodException
	 */
	@Test
	public void testMethod() throws ClassNotFoundException, NoSuchMethodException, Exception {
		Class clazz = Class.forName("com.lg.java.reflection.Person");
		// 1.得到clazz对应的类中有哪些方法，不能获取私有方法
		Method[] methods = clazz.getMethods();
		for (Method method : methods) {
			System.out.println(method.getName());
		}

		// 2.获取所有的方法，包括private方法，且只获取当前类声明的方法，
		Method[] methods2 = clazz.getDeclaredMethods();
		for (Method method : methods2) {
			System.out.println("!!" + method.getName());
		}

		// 3.获取指定的方法
		Method method = clazz.getDeclaredMethod("setName", String.class);
		System.out.println("---" + method.getName());

		method = clazz.getDeclaredMethod("test");
		System.out.println(method.getName());

		method = clazz.getDeclaredMethod("setName", String.class, Integer.class);
		System.out.println(method.getName());

		// 4.执行方法
		Object object = clazz.newInstance();
		method.invoke(object, "dd", 11);
	}

	@Test
	public void testClassLoader() throws ClassNotFoundException {
		// 1.获取系统类加载器
		ClassLoader clLoader = ClassLoader.getSystemClassLoader();
		System.out.println(clLoader);
		// 2。获取系统类加载器的父类加载器
		clLoader = clLoader.getParent();
		System.out.println(clLoader);
		// 3.获取扩展类加载器的父类加载器
		clLoader = clLoader.getParent();
		System.out.println(clLoader);
		// 4.测试当前类由那个加载器加载
		clLoader = Class.forName("com.lg.java.reflection.ReflectionTest").getClassLoader();
		System.out.println(clLoader);
		// 5测试JDK提供的Object类由那个加载器加载
		clLoader = Class.forName("java.lang.Object").getClassLoader();
		System.out.println(clLoader);

		// 6. 关于类加载器的一个主要方法:getResourceAsStream一般位置从src文件里开始走
		InputStream inputStream = this.getClass().getClassLoader()
				.getResourceAsStream("com/lg/java/reflection/a.properties");
		System.out.println(inputStream);
	}

	/**
	 * class类的NewInstance()
	 * 
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	@Test
	public void testNewInstance() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
		String className = "com.lg.java.reflection.Person";
		Class clazz = Class.forName(className);

		// 利用class对象的newInstance()方法来创建类的一个对象，
		// 实际调用的是类的无参数的构造器
		// 一般声明了有参构造器，也要声明无参构造器
		Object object = clazz.newInstance();
		System.out.println(object);

	}

	/**
	 * 1 2对象照镜子后可以得到的信息：某个类的数据成员名、方法和构造器、某个类到底实现了哪些接口。 3对于每个类而言，JRE 都为其保留一个不变的
	 * Class 类型的对象。 一个 Class 对象包含了特定某个类的有关信息。 4Class 对象只能由系统建立对象 5一个类在 JVM
	 * 中只会有一个Class实例 6每个类的实例都会记得自己是由哪个 Class 实例所生成
	 */
	@Test
	public void test() {
		Class clazz = null;
		// 1.得到class对象
		// 1.1直接通过类名.class的方式得到
		clazz = Person.class;
		// Field[] fields=clazz.getDeclaredFields();

		// 1.2通过对象调用getClass()方法得到
		Object person = new Person("s", 2);
		clazz = person.getClass();

		// 1.3通过全类名的方式获取
		String className = "com.lg.java.reflection.Person";
		System.out.println(className);

	}

}
