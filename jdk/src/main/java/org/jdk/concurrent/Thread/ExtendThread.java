package org.jdk.concurrent.Thread;

public class ExtendThread extends Thread{

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + ",running");
	}

}
