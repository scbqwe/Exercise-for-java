package scb.test.waterPool;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ThreadUtil {

	//ExecutorService:管理线程池的接口
	private static ExecutorService execService;
	
	/**
	 * 
	 * @param threads
	 * @param isBlock
	 * @return
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public static List<Object> runCheckCallable(List<Callable<Object>> threads,Boolean isBlock) throws InterruptedException, ExecutionException{
		//若传进来的线程列表中存在Null,返回空列表
		if(CheckNullThread(threads)) {
			return new ArrayList<>();
		}
		
		// 创建一个可缓存线程池
		execService = Executors.newCachedThreadPool();
		
		List<Future<Object>> futureList = execService.invokeAll(threads);
		
		 if (!isBlock){
	            return new ArrayList<>();
	        }
		//返回结果
		 return getAllReturns(futureList);
	}
	
	private static Boolean CheckNullThread(List<Callable<Object>> threads) {
		
		if(threads ==null||threads.isEmpty()) {
			return true;
		}
		
		for(Callable<Object> thread:threads) {
			if(thread == null) return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param futureList
	 * @return
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	private static List<Object> getAllReturns(List<Future<Object>> futureList) throws InterruptedException, ExecutionException{
		
		List<Object> rslist = new ArrayList<>(futureList.size());
		Iterator<Future<Object>> itr = null;
		while(true) {
			itr = futureList.iterator();
			if(itr.hasNext()) {
				Future<Object> one = itr.next();
				if(one.isDone()) {
					rslist.add(one.get());
					itr.remove();
				}
			}
			
			if(futureList.isEmpty()) {
				break;
			}
			//每隔10秒轮询一次
			TimeUnit.SECONDS.sleep(10);
		}
		return rslist;
	}
	
	/**
	 * 
	 * @param threads
	 * @param isBlock
	 * @throws InterruptedException
	 */
	public static void runCheckRunnable(List<Runnable> threads,Boolean isBlock) throws InterruptedException {
		//处理null
		if(threads == null||threads.isEmpty()) {
			return;
		}
		
		// 创建一个可缓存线程池
		execService = Executors.newCachedThreadPool();
		//添加线程并返回Future线程
        List<Future<Object>> futureList = new ArrayList<>(threads.size());
        //循环遍历，将每个线程依次添加进去
        for (Runnable runnable:threads){
            if (runnable!=null){
                Future future =  execService.submit(runnable);
                futureList.add(future);
            }
        }
        //线程不阻塞
        if (!isBlock){
            return;
        }
        while(true){
            Iterator<Future<Object>> iterator = futureList.iterator();
            while(iterator.hasNext()) {
                Future<Object> future = iterator.next();
                if ( future.isDone()) {               //线程执行结束
                    iterator.remove();
                }
            }
            if (futureList.size()==0){
                break;
            }
            
            TimeUnit.MILLISECONDS.sleep(10);
        }
    }
	/**
	 * 
	 * @param runnable
	 */
	 public static void runCheckRunnable(Runnable runnable){

	        if (execService==null){
	        	execService = Executors.newCachedThreadPool();
	        }
	        execService.submit(runnable);
	    }
}
