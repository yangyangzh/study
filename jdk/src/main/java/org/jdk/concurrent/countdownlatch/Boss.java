package org.jdk.concurrent.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class Boss implements Runnable {

	CountDownLatch latch;

	public Boss(CountDownLatch latch) {
		super();
		this.latch = latch;
	}

	@Override
	public void run() {

		try {
			System.out.println(Thread.currentThread().getName() + ":老板来了，工人干完了老板在干");
			latch.await();
			System.out.println(Thread.currentThread().getName() + ":老板我要干活了");
			Thread.sleep(1000);
			System.out.println(Thread.currentThread().getName() + ":老板干完了");

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
