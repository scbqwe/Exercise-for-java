import java.util.ArrayList;
import java.util.List;

/*
1�������ͺͻ����ͷ�װ�ͽ��С�==��������ıȽϣ������ͷ�װ�ͽ����Զ������Ϊ�����ͺ��ٽ��бȽϣ�
���Integer(0)���Զ�����Ϊint�����ٽ��бȽϣ���Ȼ����true��
2����������Integer������С�==���Ƚ�ʱ�������һ����Integer������new��õģ�����false,��Ϊ�Ƚϵ���
��������ĵ�ַ��
3�����������͵ķ�װ�ͽ���equals()�Ƚϣ�����equals()��Ƚ����ͣ����������ͬ��������Ƚ�ֵ��
���ֵҲ��ͬ������true����
4�������ͷ�װ���͵���equals(),���ǲ����ǻ������ͣ���ʱ���Ȼ�����Զ�װ�䣬������ת��Ϊ���װ����,
�����Ͳ�ͬ����false,��װ���������ͬ����Ƚ�ֵ�����ֵ��ͬ���򷵻�true�����򷵻�false��
*/

public class ListDemo {
	
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		List<Integer> list =new ArrayList<>(1);
		//Integer tmp =new Integer(4); ���ֳ�ʼ�������ӵھŰ汾������
		Integer tmp=new Integer(15);
		Integer tmp1=new Integer(15);
		Integer tmp2=15;
		Integer tmp3=15;
		
		System.out.println("tmp2==tmp3: "+(tmp2==tmp3));
		System.out.println("tmp==tmp1: "+(tmp==tmp1));//��װ����Զ����Ϊ���������ڽ��бȽ�
		System.out.println("equals: "+tmp.equals(tmp1));
		
	    list.add(tmp);
	    int cnt=tmp.intValue(); //����
	    System.out.println("cnt(unpack): "+cnt);
	    //�ı�ǰ
	    tmp=list.get(0);//����
	    System.out.println("list(before): "+ list.get(0));
	    System.out.println("tmp(before): "+tmp);
	    //�ı��
	    tmp=12;
	    System.out.println("tmp(after): "+tmp);
	    System.out.println("lsit(after): "+list.get(0));
	    
	}
}
