package org.jdk.concurrent.myqueue;

import org.jdk.common.User;

public class MyProducer implements Runnable {

	// 容器
	private final MyBlockQueue<User> queue;

	public MyProducer(MyBlockQueue<User> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		//while (true) {
			try {
				produce();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//}
	}

	public void produce() throws Exception {
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