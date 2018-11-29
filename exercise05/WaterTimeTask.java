package scb.test.waterPool;

//import java.util.ThreadUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ExecutionException;

/**
 * 定期放水类
 * Created by captain on 2017/7/27.
 */
public class WaterTimeTask extends TimerTask {

    
	@Override
    public void run() {
        BlockingQueue<String> queue = new ArrayBlockingQueue<String>(500);
        InputThread thread1 = new InputThread(queue);
        InputThread thread3 = new InputThread(queue);
        InputThread thread4 = new InputThread(queue);
        InputThread thread5 = new InputThread(queue);
        InputThread thread6 = new InputThread(queue);
        InputThread thread7 = new InputThread(queue);
        InputThread thread8 = new InputThread(queue);
        InputThread thread9 = new InputThread(queue);
        InputThread thread10 = new InputThread(queue);
        OutputThread thread2 = new OutputThread(queue);
        List<Callable<Object>> threadList = new ArrayList<>(10);
        threadList.add(thread1);
        threadList.add(thread2);
        threadList.add(thread3);
        threadList.add(thread4);
        threadList.add(thread5);
        threadList.add(thread6);
        threadList.add(thread7);
        threadList.add(thread8);
        threadList.add(thread9);
        threadList.add(thread10);
        
        List<Object> returnValues =null;
       
       // List<FutureTask<Object>> ftasks=null;
       try {
    	   returnValues = ThreadUtil.runCheckCallable(threadList, true);
	} catch (InterruptedException | ExecutionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       
           
        System.out.println("泳池已经注满");
        System.out.println(returnValues.get(0));
        System.out.println(returnValues.get(1));
        System.out.println(returnValues.get(2));
        System.out.println(returnValues.get(3));
        System.out.println(returnValues.get(4));
        System.out.println(returnValues.get(5));
        System.out.println(returnValues.get(6));
        System.out.println(returnValues.get(7));
        System.out.println(returnValues.get(8));
        System.out.println(returnValues.get(9));
    }
}
