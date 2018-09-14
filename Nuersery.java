
public class Nuersery {

	public static class Bottle{
		
		private int number=99;

		public int getNumber() {
			return number;
		}

		public void setNumber(int number) {
			this.number = number;
		}
	   public void song()
	   {
		   for (int i=this.number;i>=0;i--)
		   {
			   if (0==i) {
				   System.out.println("No more botles of beer on the wall.");
			   }
			   else {
				   System.out.println(i+"bottles of beer on the wall, "+i+"bottles of beer.");
				   System.out.println("Take one down.\nPass it around.");
				   System.out.println((i-1)+"bottles of beer on the wall.");
				   System.out.println("");
			   }
		   }
		   
	   }
	}
	public static void main(String[] args) {
		Bottle  test=new Bottle();
		test.song();
				  
	}
}
