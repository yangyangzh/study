package org.jdk.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectTest {

	
	public static void main(String[] args) throws Exception {
		ReflectTest t = new ReflectTest();
		//通过类型加载class
		Class c = t.getClassByName("org.jdk.common.User");
		System.out.println(c.getName()); //获得类的名称
		//用class的默认构造器生成对象o
		Object o = c.newInstance(); 
		//由o对象获得class对象c1，和c相同
		Class c1 = t.getClassByObject(o);
		System.out.println(c1.getName());
		
		//获得构造器
		Constructor d =  c.getConstructor();
		
		//构造器生成对象
		Object o2 = d.newInstance();
		
		//类的注解
		for(Annotation a : c.getAnnotations()){
			System.out.println(a);
		}
		
		//类里的方法
		for(Method m : c.getMethods()){
			System.out.println(m);
		}
		
		//类的属性
		for(Field f :c.getDeclaredFields()){
			System.out.println(f);
		}
		
		//指定属性
		Field name = c.getDeclaredField("name");
		//private 属性需要设置可访问
		name.setAccessible(true);
		System.out.println(name.get(o2));
		//设置属性值
		name.set(o2, "eee");
		//获得属性值
		System.out.println(name.get(o2));
		
		//指定方法
		Method setName = c.getMethod("setName",String.class);
		//指定方法，参数是执行方法的对象和参数
		setName.invoke(o2,"888");
		
		Method getName = c.getMethod("getName");
		System.out.println(getName.invoke(o2));
		

	}
	
	/**
	 * 根据类路径返回class
	 * @param className
	 * @return
	 * @throws Exception
	 */
	public Class getClassByName(String className) throws Exception{
		return Class.forName(className);
	}
	
	/**
	 * 根据对象获得对象的class
	 * @param o
	 * @return
	 */
	public Class getClassByObject(Object o){
		return o.getClass();
	}
}
