package socketDemo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SoketServerDemo {

	public SoketServerDemo() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSocket serSocket = null;
		
		try {
			serSocket = new ServerSocket(7070);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (serSocket == null) {
			return;
		}
		System.out.println("服务器成功打开！");
		while(true) {
			System.out.println("服务器等待客户端连接");
			try {
				Socket socket = serSocket.accept();
				SocketServerThread socketThread = new SocketServerThread(socket);
				new Thread(socketThread).start();
				System.out.println("服务器处理客户端连接完成！");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
			
	}

}
