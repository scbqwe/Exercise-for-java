package socketDemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.Socket;
import java.nio.Buffer;

public class SocketServerThread implements Runnable{

	private Socket socket;
	public SocketServerThread(Socket socket) {
		// TODO Auto-generated constructor stub
		this.socket = socket;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("处理客户端线程启动！");
		//接受从客户段传来的信息 
		try {
			Reader reader = new InputStreamReader(socket.getInputStream());
			BufferedReader in = new BufferedReader(reader);
			//第一次通讯
			String msg = in.readLine();
			System.out.println("客户端传来的信息："+msg);
			
			//传回信息给客户端
			PrintWriter writer = new PrintWriter(socket.getOutputStream());
			writer.println("这是来自火星的问候！");
			writer.flush();
			
			//第二次通讯
			msg = in.readLine();
			System.out.println("客户端传来的暗号："+msg);
			writer.println("暴风雨就要来了！");
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(socket != null) 
			{
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
    
}
