package socketDemo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketFileServer {
	
	public final static String path = "c:\\Users\\asus\\Desktop\\part5\\";
	public SocketFileServer() {
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
		if(serSocket == null) {
			System.out.println("服务器启动失败！");
			return;
		}
		System.out.println("服务器已启动！");
		while(true) {
			try {
				System.out.println("等待客户端连接！");
				Socket socket = serSocket.accept();
				ServerThread serThread = new ServerThread(socket);
				new Thread(serThread).start();
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("服务器打开客户端失败！");
				e.printStackTrace();
			}
	  }
	}

}
