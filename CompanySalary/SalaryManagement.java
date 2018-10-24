package scb.task;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
class SalaryforMonth{
	
	String year=null;
	String month=null;
	String day=null;
	private LinkedList<LinkedList<String>> record= new LinkedList<>();
	//private LinkedList<String[]> managrecord=new LinkedList<>();
	//private LinkedList<String> holdrecord=new LinkedList<>();
	
	public SalaryforMonth(String date) {
		
		String[] temp=date.split("-");
		if(temp.length!=3) {
			System.err.println("请按照如下格式输入日期:[year]-[month]-[day]");
			return;
		}
		this.year=temp[0];
		this.month=temp[1];
		this.day=temp[2];
		if((MemberManag.memberlist.size()==0)) 
		{
			System.err.println("请确保公司里有股东、经理、员工！");
			return ;
		}
	}
	
    //若为员工和经理则发放生日礼物
	void setRecord(LinkedList<Members> list) {
		Scanner scan =new Scanner(System.in);
		int profit=0;
		if(this.month.equals("12")) {
		  System.out.println("请输入今年的利润:");
		  String str=scan.nextLine();
		  profit=Integer.parseInt(str);
		}
		
		System.out.println("请输入此月经理的奖金:");
		String bo=scan.nextLine();
		int bonus=Integer.parseInt(bo);
		//int bonus =bo;
		
		for( Members member:MemberManag.memberlist) {
			//员工
			if(member.type==1) {
				//bonus=0;
				Staff staff=(Staff)member;
				String[] sBirth=member.birthday.split("-");
				LinkedList<String> temp=new LinkedList<>();
				//当员工生日是在本月之内发放生日礼物
				if(sBirth[1].equals(this.month)) {
					  Integer sal=staff.salary+staff.gift;
					  temp.add(staff.type.toString());temp.add(staff.name);temp.add(sal.toString());
					  record.add(temp);
				}
				else {
						temp.add(staff.type.toString());temp.add(staff.name);temp.add(String.valueOf(staff.salary));
						record.add(temp);
				}
				
			}
			//经理
			if(member.type==2) {
				
	    	    Manager manager=(Manager)member;
				
				String[] sBirth=manager.birthday.split("-");
				LinkedList<String> temp=new LinkedList<>();
				//当员工生日是在本月之内发放生日礼物
				if(sBirth[1].equals(this.month)) {
						  Integer sal=manager.salary+manager.gift+bonus;
						  temp.add(manager.type.toString());temp.add(manager.name);temp.add(sal.toString());
						  record.add(temp);
					}
					else {
						    Integer sal=manager.salary+bonus;
							temp.add(manager.type.toString());temp.add(manager.name);temp.add(sal.toString());
							record.add(temp);
					}	
				}
			 //股东
			 if(member.type==0) {
				    Shareholder holder=(Shareholder)member;
			    	
			    	if(this.month.equals("12")) {
			    			LinkedList<String> temp=new LinkedList<>();
			    			Integer sal=(int)(profit*holder.stock);
			    			temp.add(holder.type.toString());temp.add(holder.name);temp.add(sal.toString());
			    			record.add(temp);	
			    	}	
			    }
		}
	}
	    
    LinkedList<LinkedList<String>> getRecord() {
		
		return this.record;
	}
	//HashMap<String,Integer> getHolder(){
		//return this.holdrecord;
	//}
	
	//HashMap<String,Integer> getManager(){
	//	return this.managrecord;
	//}
	
	//HashMap<String,Integer> getStaff(){
	//	return this.stafrecord;
	//}
}



public class SalaryManagement {
		
		private ConnDB connDB=null;
		private static  PreparedStatement pstmt=null;
		
		public SalaryManagement() {
			
			this.connDB =new ConnDB();
		}
		
		public static void Todb(SalaryforMonth MonthRecord) throws Exception  {
			String sql="insert into Salary(s_name,s_type,s_salary,s_date) values(?,?,?,?)";
			Connection Conn =ConnDB.getConnection();
			Conn.setAutoCommit(false); //设置事务属性，不让其自动提交
			//HashMap<String,Integer> staffrecord=MonthRecord.getStaff();
			String date=MonthRecord.year+"-"+MonthRecord.month+"-"+MonthRecord.day;
			try {
				pstmt=Conn.prepareStatement(sql);
				//Set<E> temp=staffrecord.entrySet();
			    //Iterator it=temp.iterator();
			    for(List<String> one:MonthRecord.getRecord())
			    {
			    	pstmt.setString(1,one.get(1));
			    	pstmt.setInt(2,Integer.parseInt(one.get(0)));
			    	pstmt.setInt(3,Integer.parseInt(one.get(2)));
			    	pstmt.setDate(4, Date.valueOf(date));
			    	pstmt.addBatch(); 
			    }
			    
			    int[] tt = pstmt.executeBatch();
			   //提交，设置事务初始值  
			    Conn.commit();  
	            Conn.setAutoCommit(true);
				
				//pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {  
	            try{  
	                //关闭资源  
	                if(pstmt != null)pstmt.close();  
	                if(Conn != null)Conn.close();  

	            }catch (SQLException e) {  
	                e.printStackTrace();  
	                System.err.println("资源关闭失败!!!");  
	            }  
	        }  

		}
		
		//获取某个人的所有记录
		public  void getRecord(String sql) {
			System.out.println("姓名\t"+"类型\t"+"工资\t"+"日期");
			ResultSet rs=null;
			try {
				 rs=this.connDB.executeQuery(sql);
				 while(rs.next()) {
					 String name=rs.getString(1);
					 int type=rs.getInt(2);
					 int sal=rs.getInt(3);
					 Date date=rs.getDate(4);
					 
					 System.out.println(name+"\t"+type+"\t"+sal+"\t"+date.toString());
						
					}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				//关闭资源
				try {
					if(rs!=null) {
						rs.close();
					}
					
				this.connDB.close();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		public static void  main(String[] args) {
		    //添加员工、经理、股东
			LinkedList<Members> members = new LinkedList<Members>();
			Staff staff=new Staff("qwe","1998-10",3000);
			Manager manager=new Manager("scb","1998-09",8000);
			Shareholder holder=new Shareholder("banma","1998-12",0.3);
			members.add(staff);
			members.add(manager);
			members.add(holder);
			MemberManag.addMembers(members);
			
			//计算此月的工资账单记录
			SalaryforMonth MonthRecord=new SalaryforMonth("2018-09-15");
		    //将账单记录导入数据库
			MonthRecord.setRecord(MemberManag.memberlist);
			
			
			SalaryManagement sm=new SalaryManagement();
			//根据姓名查询某员工的工资记录
			String name="";
			String sql="select * from Salary where S_name='"+name+"'";
			sm.getRecord(sql);
	}
	
	
	
}


