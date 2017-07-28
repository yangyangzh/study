package org.jdk.concurrent.Thread;

import java.lang.Thread.State;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.jdk.common.User;

/**
 * 实现线程三种方法： 
 * 1.继承Thread,很少使用,无法继承其他类 
 * 2.实现Runable接口
 * 3.实现Callable接口,配合使用ExecutorService、Callable、Future可支持回调,返回特定的对象,
 * 
 * @author Administrator
 *
 */
public class Test {

	public static void main(String[] args) throws Exception {

		// ExtendThreadRun();
		// RunableTheadRun();
		CallableThreadRun();
	}

	/**
	 * 测试ExtendThread
	 */
	public static void ExtendThreadRun() {
		ExtendThread t1 = new ExtendThread();
		t1.start();
	}

	/**
	 * 测试Runable的线程实现，以及线程的状态变化
	 * 线程的所有状态见 java.lang.Thread.State
	 */
	public static void RunableTheadRun() {
		Thread t1 = new Thread(new RunnableThread());
		t1.setName("线程1");// 设置新线程的名字
		t1.run(); // 此时run方法可以执行，但是线程的名字是main，说明是由main方法调用执行的，直接调用run方法，并不能开启新的线程，必须使用start方法才能开启线程
		System.out.println(t1.getState());// run方法后，线程状态是NEW
		t1.start(); // 开启线程
		System.out.println(t1.getState());// start方法后，线程的状态是RUNNABLE，等待jvm调用
		// 此处监控该线程的状态,状态变化为
		// 1.RUNNABLE 运行
		// 2.BLOCKED 阻塞（因为调用IO打印，当前线程会阻塞，等待IO完成）
		// 3.RUNNABLE IO完成后继续运行
		// 4.TERMINATED 终止
		while (!t1.getState().equals(State.TERMINATED)) {
			System.out.println(t1.getState());
		}

	}

	/**
	 * 测试Callable,ExecutorService,Future的使用
	 * 可看下ExecutorService和Future提供的主要方法
	 * @throws Exception
	 */
	public static void CallableThreadRun() throws Exception {
		ExecutorService pool = Executors.newFixedThreadPool(5); //生成固定大小的线程池
		//Executors.newSingleThreadExecutor();                    //生成单个线程的线程池
		//Executors.newCachedThreadPool();                        //生成缓存大小可变得线程池
		//Executors.newScheduledThreadPool(10);  //定时或者周期执行的线程池，类似于quartz定时任务，Timer等功能
		CallableThread t1 = new CallableThread();
		Future<User> f = pool.submit(t1);
		while (true){
			if (f.isDone()) {//判断线程是否执行完成
				System.out.println("已完成");
				System.out.println(f.get());//获得返回的值
				break;
			} else {
				System.out.println("没完成");
			}
		}
		//关闭线程池
		pool.shutdown();

	}

}
