package org.jdk.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 动态代理中具体方法的执行者
 * @author Administrator
 *
 */
public class EatInvocationHandler implements InvocationHandler {

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("需要代理的接口是：" + method.getDeclaringClass() + " ,方法是：" + method.getName());
		if (method.getDeclaringClass().equals(IEatService.class)) {
			return "我帮你吃过了";
		} else {
			return "我帮你睡过了";
		}
	}

}
