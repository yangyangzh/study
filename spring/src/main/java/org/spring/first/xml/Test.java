package org.spring.first.xml;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * spring 两大特性DI和IOC即控制反转和依赖注入
 * 控制反转表示将对象的生成的过程交给spring容器来控制
 * 依赖注入表示将对象间的依赖关系交给spring容器
 * 注入方式：setter方法注入
 *       构造器注入
 *       注意各种类型的对象如何注入：List,Set,Map,Object
 * 除了手动注入，sping还提供自动注入方式
 * 详细看http://blog.csdn.net/topwqp/article/details/8686025
 * 
 * spring配置有XML和注解两种方式，可自行选择配置方式，目前趋势是注解，xml太繁琐
 * 后面例子都尽量使用注解,spring3之后支持selvet3.0规范，不需要任何配置xml文件即可开发web项目，web.xml也不需要
 * @author Administrator
 *
 */
public class Test {

	public static void main(String[] args) {
		
		System.out.println(System.getProperty("java.class.path"));//系统的classpaht路径
		System.out.println(System.getProperty("user.dir"));//用户的当前路径

		AbstractApplicationContext context = new ClassPathXmlApplicationContext("org/spring/first/xml/applicationContent.xml");
		/**
		 * 下面4个bean为xml配置的
		 * 
		 */
		User obj = (User) context.getBean("yyy");
		System.out.println(obj);
		User obj2 = (User) context.getBean("objctUser");
		System.out.println(obj2);
		Users obj3 = (Users) context.getBean("users");
		System.out.println(obj3);
		Users obj4 = (Users) context.getBean("usersll");
		System.out.println(obj4);
		 
	}

}
