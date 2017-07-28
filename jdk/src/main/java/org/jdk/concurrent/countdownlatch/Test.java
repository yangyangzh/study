package org.jdk.concurrent.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDownLatch表示等其他几个线程CountDown后，线程才执行，不管其他线程在CountDown后做什么， 关注的当前线程
 * CyclicBarrier则是在到达栅栏后，必须等待其他的都到了才能执行，关注的一批线程
 * @author Administrator
 *
 */
public class Test {

	public static void main(String[] args) {
		//给老板安排三个工人，三个工人干完了，老板才干活
		CountDownLatch latch = new CountDownLatch(3);
		ExecutorService executor = Executors.newFixedThreadPool(4);
		Thread t1 = new Thread(new Worker(latch));
		Thread t2 = new Thread(new Worker(latch));
		Thread t3 = new Thread(new Worker(latch));
		Thread t4 = new Thread(new Boss(latch));
		executor.submit(t1);
		executor.submit(t2);
		executor.submit(t3);
		executor.submit(t4);
	}

}
