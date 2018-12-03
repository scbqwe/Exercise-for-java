package socketDemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Scanner;

public class SocketClient {

	public SocketClient() {
		// TODO Auto-generated constructor stub
	}
    private boolean sendFileToServer(List<String> context,String fname) throws  IOException  {
    	Socket socket = null;
		
		socket = new Socket("localhost",7070);
		
    	PrintWriter writer = new PrintWriter(socket.getOutputStream());
    	writer.println("右友出征，寸草不生"); //第一步对暗号
    	writer.flush();
    	for(String str:context) {
    		writer.println(str);
    		System.out.println(str);
    		writer.flush();
    	}
    	writer.println("endend");
    	writer.flush();
    	writer.println(fname);
    	writer.flush();
    	Reader reader = new InputStreamReader(socket.getInputStream());
    	BufferedReader in = new BufferedReader(reader);
    	String msg = in.readLine();
    	socket.close();
    	if("文件保存成功！".equals(msg)) {
    		return true;
    	}else {
    		return false;
    	}
		
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		SocketClient client = new SocketClient();
		while(true) {
			System.out.println("请输入您需要的功能：1传送文件，2退出");
			String msg = sc.next();
			if("2".equals(msg)) {
				break;
			}else {
				System.out.println("请输入要传送文件的完整路径：");
				String path =sc.next();
				List<String> context = FileUtil.readFile(path);
				if(context.size()>0) {
					boolean flag;
					try {
						flag = client.sendFileToServer(context,path.substring(path.lastIndexOf("\\"), path.length()));
						if(flag) {
							System.out.println("文件传送成功！");
						}else {
							System.out.println("文件传送失败！");
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
				}	
			}
		}
	}
}
