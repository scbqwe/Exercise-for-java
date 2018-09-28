import  java.util.*;
public class SongsListDemo {
	
	 static LinkedList <songInfo> songlist=new LinkedList<> ();
	
	
	//存储歌曲信息
 	public static  class songInfo{
		private String  name=null;
		private String  singer=null;
		private int time;
		
	    public songInfo(String songname,String singer,int time) {
	    	this.name=songname;
	    	this.singer=singer;
	    	this.time=time;
	    }
	    
	    public String getName() {
	      return this.name;
	    }
	    public String getSinger() {
	    	return this.singer;
	    }
	    public int getTime() {
	    	return this.time;
	    }
	   public  void display() {
		   System.out.println(this.name +"[ "+ this.singer +" ]" + "----"+this.time);
	   }
	} 
	
	public static class User {
		private int UsrID;
		private String usrName;
		
		public User() {
			this.UsrID=0;
			this.usrName=null;
		}
		
		public User(String usrName,int id) {
			this.UsrID=id;
			this.usrName=usrName;
		}
		public void addSong(String name,String singer,int time ) {
			songInfo song = new songInfo(name,singer,time);
			songlist.addLast(song);
		}
		
	}
	
	public static void main(String[] args) throws InterruptedException {
	  
		User user=new User();
		Scanner sc =new Scanner(System.in);
        for(int i=0;i<3;i++)
        {
        	
        	String[] s=sc.nextLine().split(" ");
        	user.addSong(s[0],s[1], Integer.parseInt(s[2]));
        	System.out.println(s[0]);
       }
	    //sc.close();
		while(true) {
		  if (songlist.isEmpty()) {
			  Scanner scan =new Scanner(System.in);
			  System.out.println("所有歌曲已播放完毕，请向歌单中添加新歌曲：(按q结束添加)");
			  
			  //TODO:将输入创建个songInfo对象加入songlists中
			  //
			  while(true) {
				   
				  String[] t1= scan.nextLine().split(" ");
				  if("q"== t1[0]) {scan.close();break;}
				  user.addSong(t1[0],t1[1], Integer.parseInt(t1[2]));
				  
			  }
		  }
			  songInfo tmp=songlist.getFirst();
			  songlist.remove();
			  tmp.display();
			  System.out.println("正在播放......");
			  Thread.sleep(5);
		  
	  }
	
  }
}
