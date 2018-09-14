public class test {
 
	public static void main(String[] args)
	{
		String str1="hello,guys!";
		String str2=str1;
		//System.out.println("str1: "+str1.hashCode());
		//System.out.println("str2: "+str2.hashCode());
		System.out.println("str1: "+System.identityHashCode(str1));
		System.out.println("str2: "+System.identityHashCode(str2));
	}
}
