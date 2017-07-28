package org.jdk.concurrent.Thread;

import java.util.concurrent.Callable;

import org.jdk.common.User;

public class CallableThread implements Callable<User> {

	@Override
	public User call() throws Exception {
		System.out.println(Thread.currentThread().getName() + ",running");
		return new User("小新", "3");
	}

}
