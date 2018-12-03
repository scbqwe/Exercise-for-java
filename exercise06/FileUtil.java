package socketDemo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

	public FileUtil() {
		// TODO Auto-generated constructor stub
	}
	
	public static boolean writeFile(String storedpath,List<String> context,Boolean flag) {
		File f = new File(storedpath);
		OutputStream out = null;
		try {
//			out = new FileOutputStream(f,flag);
//			OutputStreamWriter osw=new OutputStreamWriter(out);
//	        BufferedWriter  bw=new BufferedWriter(osw);
//	        for(String line :context) {
//	        	bw.write(line);
			 out = new FileOutputStream(storedpath, flag);
	            for(String cot : context){
	                byte[] byteContent = cot.getBytes();
	                out.write(byteContent);
	        }
		} catch (IOException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally {
			try {
				if(out!=null) {
					out.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}
	
	public static List<String> readFile(String fpath){
		File f = new File(fpath);
		List<String> context = new ArrayList<>();
		String line;
		InputStream is =null;
		try {
			is = new FileInputStream(f);
			InputStreamReader isr = new InputStreamReader(is,"utf-8");
			BufferedReader reader = new BufferedReader(isr);
			while((line = reader.readLine())!=null) {
				context.add(line);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
		   try {
			   if(is!=null) {
				   is.close();
			   }
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return context;
	}
	
	public static String getFullPath(String dir,String fname) {
		if(dir.lastIndexOf("\\")==dir.length()) {
			return dir+fname;
		}
		return dir+"\\"+fname;
	}
}
