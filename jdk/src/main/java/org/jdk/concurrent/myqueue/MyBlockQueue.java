package org.jdk.concurrent.myqueue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 常见面试题
 * 
 * 根据ArrayBlockingQueue实现的自己的BlockQueue 学习 ReentrantLock 和 Condition 的用法
 * 
 * 测试时，可使用Queue的测试例子，将原来的ArrayBlockingQueue换成MyBlockQueue
 * 
 * @author Administrator
 *
 */
public class MyBlockQueue<E> {

	/**
	 * 此处主要为了学习线程方面知识 使用了jdk自带的queue来实现queue的先进先出功能 线程阻塞的功能由自己实现
	 */
	private Queue<E> queue = new LinkedList<E>();

	//可充入锁
	private ReentrantLock lock;

	//在满足队列没有满的情况下，线程可执行
	private Condition notFull;

	//在满足队列没有空的情况下，线程可执行
	private Condition notEmpty;

	// 队列的大小
	private int count;

	public MyBlockQueue(int count) {
		this.count = count;
	}

	/**
	 * 从队列中获取数据，如果获取不到，阻塞自己，直到有数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public E take() throws Exception {
		try {
			//获得锁
			lock.lock();
			
			//lock.tryLock() 
			//lock.tryLock(timeout, unit)
			//这两个方式是lock和synchronize的一个重要区别
			
			//take操作需要满足队列不为空，当为空时，当前线程会因为不满足条件notEmpty 开始await，其最终是生成一个等待线程队列
			//lock.getWaitQueueLength(condition);由该方法可看到因为condition这个条件而阻塞的线程有哪些
			while (queue.size() <= 0) {
				notEmpty.await();
			}

			E e = queue.remove();
			
			//移除一个元素后，队列已经不是满的了，满足notFull条件，唤醒因为notFull条件而阻塞的线程
			notFull.signal();
			
			return e;

		} finally {
			//最终必须要释放锁
			lock.unlock();
		}
	}

	/**
	 * 往队列中放数据，如果队列已满，则阻塞自己，直到有空
	 * @param e
	 * @throws Exception
	 */
	public void put(E e) throws Exception {
		try {
			lock.lock();

			//队列已经满了，不满足notFull条件，来put的线程都会以为这个条件而阻塞
			while (queue.size() >= count) {
				notFull.await();
			}
			queue.add(e);
			//队列put数据后，已经不空了，满足notEmpty,可唤醒因为notEmpty阻塞的线程
			notEmpty.signal();

		} finally {
			lock.unlock();
		}
	}

}
