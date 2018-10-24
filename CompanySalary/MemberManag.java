package scb.task;

import java.util.LinkedList;
import java.util.List;

import scb.task.Staff;
import scb.task.Manager;
import scb.task.Shareholder;

public class MemberManag {
	public static LinkedList<Members> memberlist= new LinkedList<>();
	
	//股东的股权以百分制值输入
	void addMember(String type,String name,String birthday,int salary) {
		if (type.equals("员工")){
			Staff temp=new Staff(name,birthday,salary);
			memberlist.add(temp);	
		}
		else if(type.equals("经理")) {
			Manager temp=new Manager(name,birthday,salary);
			memberlist.add(temp);
		}
		else if (type.equals("股东")) {
			double stock= salary/100;
			Shareholder temp= new Shareholder(name,birthday,stock);
			memberlist.add(temp);
		}
		else {
			System.out.println("不存在此类型的公司成员");
		}	
	}
	
	static void addMembers(LinkedList<Members> members) {
		
		for(Members member:members) {
			
			memberlist.add(member);
		}
	}
}
