package scb.task;

import java.util.LinkedList;
import java.util.List;

public class MemberManag1 {
	public static LinkedList<Staff> stafflist= new LinkedList<Staff>();
	public static LinkedList<Manager> managerlist=new LinkedList<Manager>();
	public static LinkedList<Shareholder> holderlist=new LinkedList<Shareholder>();
	
//股东的股权以百分制值输入
	void addMember(String type,String name,String birthday,int salary) {
		
		if (type.equals("员工")){
			Staff temp=new Staff(name,birthday,salary);
			stafflist.add(temp);
			
		}
		else if(type.equals("经理")) {
			Manager temp=new Manager(name,birthday,salary);
			managerlist.add(temp);
		}
		else if (type.equals("股东")) {
			double stock= salary/100;
			Shareholder temp= new Shareholder(name,birthday,stock);
			holderlist.add(temp);
		}
		else {
			System.out.println("不存在此类型的公司成员");
		}
	}
	
	static void addMembers(LinkedList<Members> members) {
		for(Members member:members) {
			if(member.getClass().equals(Staff.class)) {
				
				stafflist.add((Staff)member);
				System.out.println("加入成功");
			}
			else if(member.getClass().equals(Manager.class)) {
				
				managerlist.add((Manager)member);
			}
			else if(member.getClass().equals(Shareholder.class)) {
				
				holderlist.add((Shareholder)member);
			}
			
		}
		
	}
	
//	public static void main(String[] args) {
//		LinkedList<Members> members = new LinkedList<Members>();
//		Staff staff=new Staff("qwe","12-9",3000);
//		Manager manager=new Manager("scb","12-9",8000);
//		Shareholder holder=new Shareholder("banma","12-9",0.3);
//		members.add(staff);
//		members.add(manager);
//		members.add(holder);
//		if(members.get(0).type.equals("员工"))
//		{
//			System.out.println("ok");
//		}
//	   if(members.get(1).type.equals("经理")) {
//			System.out.println("经理");
//		}
//	   if(members.get(2).type.equals("股东")) {
//			System.out.println("股东");
//		}
		//以上为测试用
//		addMembers(members);
//		
//		System.out.println(stafflist.size());
//		System.out.println(managerlist.size());
//		System.out.println(holderlist.size());
		
//	}
}
