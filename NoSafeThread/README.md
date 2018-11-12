
### ThreadTest1分析
#### 报错如下：
>Exception in thread "Thread-1" java.util.ConcurrentModificationException  
	at java.util.ArrayList$Itr.checkForComodification(Unknown Source)  
	at java.util.ArrayList$Itr.next(Unknown Source)  
	at scb.test.ThreadSafeDemo1.run(ThreadSafeDemo1.java:28)  
	at java.lang.Thread.run(Unknown Source)  

>synchronized(this){  
            Iterator<String> iterator = list.iterator();  
            int cnt = 0;  
            while(iterator.hasNext()){  
                iterator.next();  
                iterator.remove();  
                cnt++;  
               ......  
        }  
    }  
查看了下ArraryList和Itr的源码实现
