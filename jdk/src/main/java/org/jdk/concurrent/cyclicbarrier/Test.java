package org.jdk.concurrent.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CyclicBarrier栅栏，所有的人线程要到了栅栏这里要等待，等所有线程都到了才能各自继续执行
 * Worker表示工人下班都要一起
 * @author Administrator
 *
 */
public class Test {

	public static void main(String[] args) {
		// 如果将参数改为4，但是下面只加入了3个选手，这永远等待下去
		// Waits until all parties have invoked await on this barrier.
		CyclicBarrier barrier = new CyclicBarrier(3);

		ExecutorService executor = Executors.newFixedThreadPool(4);
		Thread t1 = new Thread(new Worker(barrier));
		Thread t2 = new Thread(new Worker(barrier));
		Thread t3 = new Thread(new Worker(barrier));
		Thread t4 = new Thread(new Worker(barrier));

		t1.setName("线程1");
		t2.setName("线程2");
		t3.setName("线程3");
		t4.setName("线程4");
		executor.submit(t1);
		executor.submit(t2);
		executor.submit(t3);
		executor.submit(t4);
		
		//可以重置，重新使用
		barrier.reset();
		
		executor.submit(t1);
		executor.submit(t2);
		executor.submit(t3);
		executor.submit(t4);

		executor.shutdown();
	}

}
