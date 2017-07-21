package org.jdk.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class NIOClient {

	/* 缓冲区大小 */
	private static int BLOCK = 4096;
	/* 接受数据缓冲区 */
	private static ByteBuffer sendbuffer = ByteBuffer.allocate(BLOCK);
	/* 发送数据缓冲区 */
	private static ByteBuffer receivebuffer = ByteBuffer.allocate(BLOCK);

	/* 服务器端地址 */
	private final static InetSocketAddress SERVER_ADDRESS = new InetSocketAddress("localhost", 8888);

	SocketChannel socketChannel;

	Selector selector;

	public NIOClient() throws Exception {
		// 打开socket通道
		socketChannel = SocketChannel.open();
		// 设置为非阻塞方式
		socketChannel.configureBlocking(false);
		// 打开选择器
		selector = Selector.open();
		// 注册连接服务端socket动作
		socketChannel.register(selector, SelectionKey.OP_CONNECT);
		// 连接
		socketChannel.connect(SERVER_ADDRESS);

	}

	public static void main(String[] args) throws Exception {
		NIOClient client = new NIOClient();
		client.Listen();
		// 分配缓冲区大小内存
	}

	public void Listen() throws Exception {
		while (true) {
			// 选择一组键，其相应的通道已为 I/O 操作准备就绪。
			// 此方法执行处于阻塞模式的选择操作。
			selector.select();
			// 返回此选择器的已选择键集。
			Set<SelectionKey> selectionKeys = selector.selectedKeys();
			// System.out.println(selectionKeys.size());
			Iterator<SelectionKey> iterator = selectionKeys.iterator();
			while (iterator.hasNext()) {
				SelectionKey selectionKey = iterator.next();
				handleKey(selectionKey);
			}
		}
	}

	// 处理请求
	private void handleKey(SelectionKey selectionKey) throws IOException {
		// 接受请求
		SocketChannel client = null;
		String receiveText;
		String sendText;
		int count = 0;
		// 测试此键的通道是否已准备好套接字连接。
		if (selectionKey.isConnectable()) {
			System.out.println("client connect");
			client = (SocketChannel) selectionKey.channel();
			// 判断此通道上是否正在进行连接操作。
			// 完成套接字通道的连接过程。
			if (client.isConnectionPending()) {
				client.finishConnect();
				System.out.println("完成连接,准备发送消息-- ：");
				sendbuffer.clear();
				Scanner s = new Scanner(System.in);
				sendbuffer.put(s.nextLine().getBytes());
				sendbuffer.flip();
				client.write(sendbuffer);
			}
			client.register(selector, SelectionKey.OP_READ);
		} else if (selectionKey.isReadable()) {
			// 返回为之创建此键的通道。
			client = (SocketChannel) selectionKey.channel();
			// 将缓冲区清空以备下次读取
			receivebuffer.clear();
			// 读取服务器发送来的数据到缓冲区中
			count = client.read(receivebuffer);
			if (count > 0) {
				receiveText = new String(receivebuffer.array(), 0, count);
				System.out.println("接受服务端消息 -- :");
				System.out.println(receiveText);
				client.register(selector, SelectionKey.OP_WRITE);
			}
		} else if (selectionKey.isWritable()) {
			// 将缓冲区清空以备下次写入
			sendbuffer.clear();
			// 返回为之创建此键的通道。
			client = (SocketChannel) selectionKey.channel();
			System.out.println("发送消息给服务端 -- :");
			Scanner s = new Scanner(System.in);
			// 向缓冲区中输入数据
			sendbuffer.put(s.nextLine().getBytes());
			// 将缓冲区各标志复位,因为向里面put了数据标志被改变要想从中读取数据发向服务器,就要复位
			sendbuffer.flip();
			// 输出到通道
			client.write(sendbuffer);
			client.register(selector, SelectionKey.OP_READ);
		}
	}
}