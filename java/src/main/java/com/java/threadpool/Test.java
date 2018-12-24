package com.java.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

public class Test {

    public static List<Future> list=new ArrayList<>();
    
    public static void main(String[] args) throws InterruptedException {
        CachedThreadPoolTest r=new CachedThreadPoolTest();
        Thread t=new Thread(r);
        t.start();
        for(int i=0;i<=20;i++) {
            System.out.println("add "+i);
            r.add(i);
            Thread.sleep(100);
        }
        while(true) {
            if(list.size()==0) {
                System.out.println("add new Task !!!!!!!!!!!!!!!!!!!");
                r.add((int) (Math.random()*100));
            }
            Thread.sleep(100);
        }
    }
}
