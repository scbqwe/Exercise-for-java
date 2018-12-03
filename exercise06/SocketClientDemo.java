package socketDemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class SocketClientDemo {

	public SocketClientDemo() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		
		Socket client =null;
		try {
		    client = new Socket("localhost",7070);
		    
		    //第一次通信
		    PrintWriter writer = new PrintWriter(client.getOutputStream());
		    writer.println("请求支援！");
		    writer.flush();
		    
		    //获取服务器端回传的信息
		    Reader reader = new InputStreamReader(client.getInputStream());
		    BufferedReader in = new BufferedReader(reader);
		    String msg = in.readLine();
		    System.out.println("服务器回传的信息："+msg);
		    
		    TimeUnit.SECONDS.sleep(5);
		    
		    //第二次通讯
		    writer.println("大量敌军来袭！");
		    writer.flush();
		    msg = in.readLine();
		    System.out.println("服务器返回的暗号："+msg);
		}  catch (InterruptedException |IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(client !=null) {
				try {
					client.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
}
