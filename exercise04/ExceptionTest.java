import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.ParseException;

public class ExceptionTest {
	
	static void one() {
		
		SimpleDateFormat dateformat=new SimpleDateFormat("yyyy-mm-dd");
		List<Date> list =new ArrayList(10);
		try {
			
			for (int cnt=0;cnt<10;cnt++) {
				Date date=dateformat.parse("09-12");
				list.add(date);
			}
		}catch(ParseException e) {
			System.out.println("one函数里存在异常:");
			e.printStackTrace();
		}
		
	}
	
	static void two() {
		
		SimpleDateFormat dateformat =new SimpleDateFormat("yyyy-mm-dd");
		List<Date> list =new ArrayList(10);
		for (int cnt=0;cnt<10;cnt++) {
			
			try {
				Date date =dateformat.parse("09-12");
				list.add(date);
			}catch(ParseException e){
				System.out.println("two函数里存在异常:"+cnt);
				e.printStackTrace();
			}
		}
		
	}
	public static void main(String[] args) {
		one();
		two();
	}
}
