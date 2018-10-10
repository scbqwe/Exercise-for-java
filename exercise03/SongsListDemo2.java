import  java.util.*;

class songInfo{
		private String  name=null;
		private String  singer=null;
		private float time;
		
	    public songInfo(String songname,String singer,float time) {
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
	    public float getTime() {
	    	return this.time;
	    }
	   public  void display() {
		   System.out.println(this.name +"[ "+ this.singer +" ]" + "----"+this.time);
	   }
	} 
	
class User {
		
		public void addSong(String name,String singer,float time) {
			
			
			songInfo song = new songInfo(name,singer,time);
			SongsListDemo2.songlist.addLast(song);
		}
		
	}
	
	
class SongDriver{
		
		private User user=new User();
		
		public void LoadSongs() throws InterruptedException {
			
			while(!SongsListDemo2.songlist.isEmpty()) {
			  songInfo tmp=SongsListDemo2.songlist.getFirst();
			  SongsListDemo2.songlist.remove();
			  tmp.display();
			  System.out.println("正在播放......");
			  Thread.sleep(5);
			}
			System.out.println("歌曲列表中所有歌曲已播放完，请添加歌曲.....");
			this.addSongs();
		}
			
		public void addSongs() throws InterruptedException {
			    System.out.println("----------用户添加歌曲-----------");
			    System.out.println("依次为[歌名] [歌手] [时长]:");
			    while(true) {
				
				
				String[] info=SongsListDemo2.scan.nextLine().split(" ");
				if(info[0].equals("q")) break;
				else { 
					if(info.length != 3) {System.err.println("歌曲输入信息不够，请重新输入");}
					else user.addSong(info[0],info[1], Float.parseFloat(info[2]));
				}
				//while(SongsListDemo.scan.hasNextLine()) {
					//@SuppressWarnings("unused")
				//	String tmp =SongsListDemo.scan.nextLine();
			//	}
				
	    }
		this.LoadSongs();
	}
}

public class SongsListDemo2 {
	
	public static  Scanner scan=new Scanner(System.in);
	public static LinkedList <songInfo> songlist =new LinkedList<> ();
	
	public static void main(String[] args) throws InterruptedException {
	   
		SongDriver driver =new SongDriver();
		driver.LoadSongs();
	
  }
}
