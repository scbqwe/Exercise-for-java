package scb.task;


//公司每种成员都有 姓名、生日、工资 三种属性
public abstract  class Members {
	public Integer  type=null;
	public String name=null; 
	public String birthday=null;
	public int salary=0;
}

//公司有三种成员：员工、经理、股东


//员工：每年工资总额、生日礼物金额
class Staff extends Members{
	//public static String type="员工";
	int salaryTol; //年总工资
	int gift=0;	   //礼物金额
	
	public Staff(String name,String birthday,int salary) {
		this.type=1;
		this.name=name;
		this.birthday=birthday;
		this.salary=salary;
		this.gift=(int)((salary*0.1)+100); 
	}
	
} 



//经理：每年工资总额、每年奖金总额、每月奖金、生日礼物金额

class Manager extends Members{
	int salaryTol;
	int bonusTol;
	int bonus=0;
	int gift=0;
	//public static String type="经理";
	public Manager(String name,String birthday,int salary) {
		this.type=2;
		this.name=name;
		this.birthday=birthday;
		this.salary=salary;
		this.gift= (int)(this.salary*0.1+300);
	}
	
	void setBonus(int bonus) {
		this.bonus=bonus;
	}
	
}

//股东:股权、年底分红

class Shareholder extends Members{ 
	
	double stock=0.0;
	//public static String type="股东";
	
	public Shareholder(String name,String birthday,double stock) {
		this.type=0;
		this.name=name;
		this.birthday=birthday;
		this.stock=stock;
		
	}
	
	int getDividend(int profit) {
		
	 int dividend=(int)(profit*0.1*stock);
	 return dividend;
	}
	
}


