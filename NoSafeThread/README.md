
### ThreadTest1分析
#### 报错如下：
>Exception in thread "Thread-1" java.util.ConcurrentModificationException  
	at java.util.ArrayList$Itr.checkForComodification(Unknown Source)  
	at java.util.ArrayList$Itr.next(Unknown Source)  
	at scb.test.ThreadSafeDemo1.run(ThreadSafeDemo1.java:28)  
	at java.lang.Thread.run(Unknown Source)  
	
##### 如下为实现的线程类中对ArraryList的remove的代码
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

Itr.next()实现：
```  
	 public E next() {
            checkForComodification();
            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();
            Object[] elementData = ArrayList.this.elementData;
            if (i >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = i + 1;
            return (E) elementData[lastRet = i];
        }
```
