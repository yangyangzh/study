package org.jdk.concurrent.myqueue;

import org.jdk.common.User;

/**
 * 由BlockingQueue实现的生产者和消费者模型 BlockingQueue 阻塞队列
 * 生产者往队列里生产数据，如果队列已满，则自行阻塞当前线程，直到队列不满的时候，唤醒该线程 ;消费者从队列里消费数据，如果队列为空，类似
 * 
 * @author Administrator
 *
 */
public class MyQueueTest {

	public static void main(String[] args) {
		int capacity = 10;
		MyBlockQueue<User> queue = new MyBlockQueue<User>(capacity);
		Thread t1 = new Thread(new MyProducer(queue));
		Thread t2 = new Thread(new MyProducer(queue));
		Thread t3 = new Thread(new MyConsumer(queue));
		Thread t4 = new Thread(new MyConsumer(queue));
		t1.setName("生产者1");
		t2.setName("生产者2");
		t3.setName("消费者1");
		t4.setName("消费者2");
		// t1.start();
		// t2.start();
		t3.start();
		t4.start();

		/**
		 * 此处可以监控着四个线程的状态和当前队列的数量
		 * 由于线程执行很快，sleep2秒会看到大部分的线程状态为TIMED_WAITING,因睡眠而等待
		 * 其他时候在RUNNABLE,BLOCKED,WAITING中切换
		 */
		int i = 0;
		while (i < 2) {
			// System.out.println("线程名： " + t1.getName() + " ,状态： " +
			// t1.getState() + " ,队列数量：" + queue.size());
			// System.out.println("线程名： " + t2.getName() + " ,状态： " +
			// t2.getState() + " ,队列数量：" + queue.size());
			System.out.println("线程名： " + t3.getName() + "  ,状态： " + t3.getState() + " ,队列数量：" + queue.size());
			System.out.println("线程名： " + t4.getName() + "  ,状态： " + t4.getState() + " ,队列数量：" + queue.size());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++;
		}
		t1.start();
		t2.start();
		while (true) {
			// System.out.println("线程名： " + t1.getName() + " ,状态： " +
			// t1.getState() + " ,队列数量：" + queue.size());
			// System.out.println("线程名： " + t2.getName() + " ,状态： " +
			// t2.getState() + " ,队列数量：" + queue.size());
			System.out.println("线程名： " + t3.getName() + "  ,状态： " + t3.getState() + " ,队列数量：" + queue.size());
			System.out.println("线程名： " + t4.getName() + "  ,状态： " + t4.getState() + " ,队列数量：" + queue.size());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
