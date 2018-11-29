package scb.test.waterPool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * 放水线程
 * Created by captain on 2017/7/27.
 */
public class OutputThread implements Callable<Object>{

    private BlockingQueue<String> queue;

    public OutputThread(BlockingQueue queue){
        this.queue = queue;
    }

    @Override
    public Object call() throws InterruptedException {
        while(true){
            if(queue.size()==500){
                break;
            }
            for(int cnt=0;cnt<3;cnt++){
                queue.poll(10,TimeUnit.SECONDS);
            }
            System.out.println("放水3立方米，当前水量："+queue.size());
            //当前线程沉睡1秒
            TimeUnit.SECONDS.sleep(1);
        }
        return "success";
    }
}
