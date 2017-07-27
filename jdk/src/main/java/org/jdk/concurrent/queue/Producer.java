package org.jdk.concurrent.queue;

import java.util.concurrent.ArrayBlockingQueue;

import org.jdk.common.User;

public class Producer implements Runnable {

	// 容器
	private final ArrayBlockingQueue<User> queue;

	public Producer(ArrayBlockingQueue<User> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		while (true) {
			produce();
		}
	}

	public void produce() {
		/**
		 * put()方法是如果容器满了的话就会把当前线程挂起
		 * offer()方法是容器如果满的话就会返回false，也正是我在前一篇中实现的那种策略。
		 */
		try {
			User user = new User();
			queue.put(user);
			Thread.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}