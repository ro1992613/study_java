package com.java.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CachedThreadPoolTest implements Runnable {
    
    
    public void add(int size) {
        //ExecutorService cachedThreadPool=localCachedThreadPool.get();
        List<Future> list=Test.list;
        for(int i=0;i<size;i++) {
            Future future =cachedThreadPool.submit(new MyCallable());
            list.add(future);
        }
    }

    //ThreadLocal<ExecutorService> localCachedThreadPool = new ThreadLocal<ExecutorService>();
    //ThreadLocal<List<Future>> localList = new ThreadLocal<List<Future>>();
    //ExecutorService cachedThreadPool=Executors.newCachedThreadPool();
    
    ExecutorService cachedThreadPool=Executors.newFixedThreadPool(10);
    
    public void run() {
        //ExecutorService cachedThreadPool=localCachedThreadPool.get();
        List<Future> list=Test.list;
        for(int i=0;i<10;i++) {
            Future future =cachedThreadPool.submit(new MyCallable());
            list.add(future);
        }
        try {
            runtask();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    
    public synchronized void runtask() throws InterruptedException, ExecutionException {
      //localCachedThreadPool.get();
      List<Future> list=Test.list;
      int i=0;
      while(list.size()>=0) {
          if(list.size()==0) {
              System.out.println("no task");
              try {
                wait(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
              continue;
          }
          System.out.println("listsize="+list.size() +" i="+i);
          Thread.sleep(100);
          if(i>=list.size()) {
              i=0;
              continue;
          }
          Future future =list.get(i);
          if(future.isDone()) {
              System.out.println("done");
              System.out.println(future.get());
              list.remove(i);
              i=0;
//              if(list.size()==1) {
//                  System.out.println("******add new Task!");
//                  Thread.sleep(1000);
//                  while(true) {
//                      Future f =cachedThreadPool.submit(new MyCallable());
//                      list.add(f);
//                      if(list.size()>100) {
//                          break;
//                      }
//                  }
//              }
              continue;
          }else {
              System.out.println("undone");
              i++;
              continue;
          }
      }
    }

}

class MyCallable implements Callable{

    @Override
    public Object call() throws Exception {
        // TODO Auto-generated method stub
        int s=(int) (Math.random()*100);
        Thread.sleep(s+2000);
        return Thread.currentThread().getId();
    }
    
}

class myThread implements Runnable{

    @Override
    public void run() {
        // TODO Auto-generated method stub
        while(true) {
            System.out.println("runnable "+Thread.currentThread().getId());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
}