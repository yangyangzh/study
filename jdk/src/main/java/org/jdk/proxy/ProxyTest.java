package org.jdk.proxy;

import java.lang.reflect.Proxy;

public class ProxyTest {

	public static void main(String[] args) {

		// 具体执行任务的人
		EatInvocationHandler handler = new EatInvocationHandler();

		// 生成一个代理对象，三个参数表示用handler作为具体任务的执行者，由handle来执行该classloader加载的接口Class[]
		// {
		// IEatService.class,ISleepService.class }
		// 在这里，则是IEatService接口和ISleepService接口的行为都会被EatInvocationHandler来代理执行
		IEatService service = (IEatService) Proxy.newProxyInstance(IEatService.class.getClassLoader(),
				new Class[] { IEatService.class, ISleepService.class }, handler);
		System.out.println(service.eat());

		ISleepService service2 = (ISleepService) service;
		System.out.println(service2.sleep());

	}

}
