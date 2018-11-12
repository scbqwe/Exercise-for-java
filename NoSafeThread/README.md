
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
	
	public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForComodification();

            try {
                ArrayList.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
```
ArraryList remove():
```
public E remove(int index) {
        rangeCheck(index);
        modCount++;
        E oldValue = elementData(index);
        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index+1, elementData, index,numMoved);
        elementData[--size] = null; // clear to let GC do its work
        return oldValue;
    }
```
从上述源码中可以看出每次调用next()函数时首先都会判断modCount和expectedModCount的值是否相等，若不等则抛出ConcurrentModificationException异常。注意expectedModCount是Itr类的成员变量，modCount是ArrayList的成员变量。
Itr类的remove()会调用ArraryList的remove()方法,而ArrayList类的remove()会对modCount++。故在多线程中，Itr的iterator对象是每个线程各自拥有的，因此当一个线程调用了Itr的remove()方法从而调用了ArraryList的remove()方法使得modCount发生改变，同时也令此线程的iterator对象的expectedModCount=modCount，但其他线程的iterator对象的expectedModCount的值并未发生改变，故而在调用next()时因expectedModCount！=modCount而抛出ConcurrentModificationException异常。




