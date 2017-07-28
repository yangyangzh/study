package org.jdk.concurrent.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class Worker implements Runnable {

	CountDownLatch latch;

	public Worker(CountDownLatch latch) {
		super();
		this.latch = latch;
	}

	@Override
	public void run() {

		try {
			System.out.println(Thread.currentThread().getName() + ":我要干活了");
			Thread.sleep(10000);
			System.out.println(Thread.currentThread().getName() + ":我干完了,签个字");
			// 完成后加1
			latch.countDown();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
