package org.jdk.concurrent.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;


public class Worker implements Runnable {

	CyclicBarrier barrier;

	public Worker(CyclicBarrier barrier) {
		super();
		this.barrier = barrier;
	}

	@Override
	public void run() {

		try {
			System.out.println(Thread.currentThread().getName() + ":我要干活了");
			Thread.sleep(2000);
			System.out.println(Thread.currentThread().getName() + ":等你们干完了,我们一起走");
			System.out.println(Thread.currentThread().getName() + ":等待线程数：" + barrier.getNumberWaiting());
			barrier.await();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + ":一起回去咯");

	}

}
