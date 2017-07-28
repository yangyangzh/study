package org.jdk.concurrent.myqueue;

import java.util.concurrent.ArrayBlockingQueue;

import org.jdk.common.User;

public class MyConsumer implements Runnable {

	// 容器
	private final MyBlockQueue<User> queue;

	public MyConsumer(MyBlockQueue<User> queue) {
		this.queue = queue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		//while (true) {
			try {
				consume();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//}
	}

	public void consume() throws Exception {
		/**
		 * take()方法和put()方法是对应的，从中拿一个数据，如果拿不到线程挂起
		 * poll()方法和offer()方法是对应的，从中拿一个数据，如果没有直接返回null
		 */
		try {
			User user = queue.take();
			Thread.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}