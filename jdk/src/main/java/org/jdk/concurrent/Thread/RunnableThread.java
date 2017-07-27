package org.jdk.concurrent.Thread;

public class RunnableThread implements Runnable {

	@Override
	public void run() {
		int i = 1;
		while (i < 100000) {
			i++;
		}
		System.out.println(Thread.currentThread().getName() + ",running");
	}

}
