
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
变量介绍：（1）cursor:指向下一个元素的索引 （2）lastRet:上一个访问的元素的索引 （3）expectedModCount：Itr类的期望修改计数变量   （4）modCount：ArrayLisT继承自AbstractList的成员变量，用来记录ArrayList被修改的次数
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
	
	final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
```

