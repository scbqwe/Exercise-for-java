
import java.util.Scanner;

abstract class vehicle{
	protected String type=null; //交通工具类型
	protected double toStationTime;//到车站的时间
	protected double toDesTime;    //从车站到目的地时间
	protected double ticketTime;	//取票时间
	protected double securTime;	//安检时间
	protected double stayTime;	//候车时间
	protected double speed;  	//车速
	protected double price; 	//单价
	
	
	abstract String Consumption(String departure,String destination,double distance);
	
	void display(String departure,String destination,double distance) {
		String consum=Consumption(departure,destination,distance);
		
		System.out.println(consum);
		
	}
}

class airplane extends vehicle{

	public airplane() {
		this.type="飞机";
		this.price=0.75;//  公里/秒
		this.speed=900; // 公里/小时
		this.toStationTime=1.0; //小时
		this.ticketTime=0.5;
		this.stayTime=1.0;
		this.toDesTime=1.0;
		this.securTime=0.2;
	}
	
	@Override
	String Consumption(String departure, String destination, double distance) {
		// TODO Auto-generated method stub
		double time=this.ticketTime+this.securTime+this.stayTime+this.toDesTime+
				 this.toStationTime+distance/this.speed;
		double money=distance*this.price;
		String display=this.type+"	"+departure+"=======>>"+destination+":\n"+
				"时长: "+time+" 小时\n"+"金额: "+money+" 元\n";
		return display;
	}
	
}

class train extends vehicle{

	public train() {
		this.type="高铁";
		this.price=0.45;//  公里/秒
		this.speed=300; // 公里/小时
		this.toStationTime=0.5; //小时
		this.ticketTime=0.4;
		this.stayTime=0.5;
		this.toDesTime=0.5;
		this.securTime=0.2;
		
	}
	@Override
	String Consumption(String departure, String destination, double distance) {
		// TODO Auto-generated method stub
		double time=this.ticketTime+this.securTime+this.stayTime+this.toDesTime+
				 this.toStationTime+distance/this.speed;
		double money=distance*this.price;
		String display=this.type+"	"+departure+"=======>>"+destination+":\n"+
				"时长: "+time+" 小时\n"+"金额: "+money+" 元\n";
		return display;
	}
	
}
class bus extends vehicle{

	public bus() {
		this.type="汽车";
		this.price=0.32;//  公里/秒
		this.speed=120; // 公里/小时
		this.toStationTime=0.5; //小时
		this.ticketTime=0.2;
		this.stayTime=0.5;
		this.toDesTime=0.5;
		this.securTime=0.15;
		
	}
	@Override
	String Consumption(String departure, String destination, double distance) {
		// TODO Auto-generated method stub
		double time=this.ticketTime+this.securTime+this.stayTime+this.toDesTime+
				 this.toStationTime+distance/this.speed;
		double money=distance*this.price;
		String display=this.type+"	"+departure+"=======>>"+destination+":\n"+
				"时长: "+time+" 小时\n"+"金额: "+money+" 元\n";
		return display;
	}
	
}

public class TransPortManag {
	
	public static void main(String[] args) {
		airplane a=new airplane();
		train t=new train();
		bus b=new bus();
	
		System.out.println("依次输入出发地、目的地、距离:");
		Scanner scan=new Scanner(System.in);
		String str=scan.nextLine();
		if(scan.hasNextLine()) {
			String temp=scan.nextLine();
		}
		String[] s=str.split("\t");
		a.display(s[0], s[1],Double.parseDouble(s[2]));
		b.display(s[0], s[1],Double.parseDouble(s[2]));
		t.display(s[0], s[1],Double.parseDouble(s[2]));
	}
}
