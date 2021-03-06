package org.jdk.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class NIOServer {

	/* 缓冲区大小 */
	private int BLOCK = 4096;
	/* 接受数据缓冲区 */
	private ByteBuffer sendbuffer = ByteBuffer.allocate(BLOCK);
	/* 发送数据缓冲区 */
	private ByteBuffer receivebuffer = ByteBuffer.allocate(BLOCK);

	private Selector selector;
	
	Map<String, SocketChannel> map =  new ConcurrentHashMap<String, SocketChannel>();

	public NIOServer(int port) throws IOException {
		// 打开服务器套接字通道
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		// 服务器配置为非阻塞
		serverSocketChannel.configureBlocking(false);
		// 检索与此通道关联的服务器套接字
		ServerSocket serverSocket = serverSocketChannel.socket();
		// 进行服务的绑定
		serverSocket.bind(new InetSocketAddress(port));
		// 通过open()方法找到Selector
		selector = Selector.open();
		// 注册到selector，等待连接
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		System.out.println("Server Start----8888:");
	}

	// 监听
	private void listen() throws IOException {
		while (true) {
			// 选择一组键，并且相应的通道已经打开
			selector.select();
			// 返回此选择器的已选择键集。
			Set<SelectionKey> selectionKeys = selector.selectedKeys();
			Iterator<SelectionKey> iterator = selectionKeys.iterator();
			while (iterator.hasNext()) {
				SelectionKey selectionKey = iterator.next();
				iterator.remove();
				handleKey(selectionKey);
			}
		}
	}

	// 处理请求
	private void handleKey(SelectionKey selectionKey) throws IOException {
		// 接受请求
		ServerSocketChannel server = null;
		SocketChannel client = null;
		int count = 0;
		// 测试此键的通道是否已准备好接受新的套接字连接。
		if (selectionKey.isAcceptable()) {
			// 返回为之创建此键的通道。
			server = (ServerSocketChannel) selectionKey.channel();
			// 接受到此通道套接字的连接。
			// 此方法返回的套接字通道（如果有）将处于阻塞模式。
			client = server.accept();
			map.put(client.getRemoteAddress().toString(), client);
			// 配置为非阻塞
			client.configureBlocking(false);
			// 注册到selector，等待连接
			client.register(selector, SelectionKey.OP_READ);
		} else if (selectionKey.isReadable()) {
			// 返回为之创建此键的通道。
			client = (SocketChannel) selectionKey.channel();
			// 将缓冲区清空以备下次读取
			receivebuffer.clear();
			// 读取服务器发送来的数据到缓冲区中
			try {
				count = client.read(receivebuffer);
			} catch (IOException e) {
				System.out.println("断开连接");
				client.close();
			}
			if (count > 0) {
				String receiveText = new String(receivebuffer.array(), 0, count);
				System.out.println("服务器收到消息-- :");
				System.out.println(receiveText);
				client.register(selector, SelectionKey.OP_WRITE);
			}
		} else if (selectionKey.isWritable()) {
			// 将缓冲区清空以备下次写入
			sendbuffer.clear();
			// 返回为之创建此键的通道。
			client = (SocketChannel) selectionKey.channel();
			System.out.println("服务端发送消息-- ：");
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

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int port = 8888;
		NIOServer server = new NIOServer(port);
		server.listen();
	}
}