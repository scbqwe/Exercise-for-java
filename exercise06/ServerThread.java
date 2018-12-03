package socketDemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerThread implements Runnable {

	private Socket socket;
	public ServerThread(Socket socket) {
		// TODO Auto-generated constructor stub
		this.socket = socket;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("服务器传送线程已经启动！");
		Reader reader =null;
		try {
			reader = new InputStreamReader(socket.getInputStream());
			BufferedReader in = new BufferedReader(reader);
			//对暗号，防止其他程序攻击
			String msg =  in.readLine();
			if(!"右友出征，寸草不生".equals(msg)) {
				return;
			}
			System.out.println("服务器暗号对接通过！");
			//接受传输的文本内容
			List<String> context = getFile(in);
			for(String line:context) {
				System.out.println(line);
			}
			System.out.println("服务器文件接收完成！");
			String fname = in.readLine(); //客户端指定的文件名
			
			System.out.println("服务器文件名接收完成！");
			boolean flag = FileUtil.writeFile(FileUtil.getFullPath(SocketFileServer.path,fname),context,false);
			PrintWriter writer = new PrintWriter(socket.getOutputStream());
			
			if(flag) {
				writer.println("文件保存成功！");
			}else {
				writer.println("文件保存失败！");
			}
			writer.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(socket!=null) {
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	private List<String> getFile(BufferedReader in) throws IOException{
		List<String> context = new ArrayList<>();
		while(true) {
			String tempStr = in.readLine();
			if("endend".equals(tempStr)) {
				break;
			 }
			context.add(tempStr);
		}
		return context;
	}
}
