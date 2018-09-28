import java.util.ArrayList;
import java.util.List;

/*
1、基本型和基本型封装型进行“==”运算符的比较，基本型封装型将会自动拆箱变为基本型后再进行比较，
因此Integer(0)会自动拆箱为int类型再进行比较，显然返回true。
2、另外两个Integer对象进行“==”比较时，如果有一方的Integer对象是new获得的，返回false,因为比较的是
两个对象的地址。
3、两个基本型的封装型进行equals()比较，首先equals()会比较类型，如果类型相同，则继续比较值，
如果值也相同，返回true，。
4、基本型封装类型调用equals(),但是参数是基本类型，这时候，先会进行自动装箱，基本型转换为其封装类型,
若类型不同返回false,若装箱后类型相同，则比较值，如果值相同，则返回true，否则返回false。
*/

public class ListDemo {
	
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		List<Integer> list =new ArrayList<>(1);
		//Integer tmp =new Integer(4); 此种初始化方法从第九版本已舍弃
		Integer tmp=new Integer(15);
		Integer tmp1=new Integer(15);
		Integer tmp2=15;
		Integer tmp3=15;
		
		System.out.println("tmp2==tmp3: "+(tmp2==tmp3));
		System.out.println("tmp==tmp1: "+(tmp==tmp1));//包装类会自动拆包为基础类型在进行比较
		System.out.println("equals: "+tmp.equals(tmp1));
		
	    list.add(tmp);
	    int cnt=tmp.intValue(); //复制
	    System.out.println("cnt(unpack): "+cnt);
	    //改变前
	    tmp=list.get(0);//复制
	    System.out.println("list(before): "+ list.get(0));
	    System.out.println("tmp(before): "+tmp);
	    //改变后
	    tmp=12;
	    System.out.println("tmp(after): "+tmp);
	    System.out.println("lsit(after): "+list.get(0));
	    
	}
}
