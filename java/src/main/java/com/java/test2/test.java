package com.java.test2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class test {

    public static void main(String[] args){
        Integer var1=new Integer(1);
        Integer var2=var1;
        doSomething(var2);
        System.out.print(var1.intValue());
        System.out.print(var1==var2);
        System.out.println();

        Thread t=new Thread(()->{System.out.println("1");});
        t.start();
        List<String> list=new ArrayList<>();
        list.add("1");
        list.forEach((s)->{System.out.println(s);});
        Integer s=0;

        
try {
    
} catch (Exception e) {
    // TODO: handle exception
}
        LamdaOperation la= (int a, int b) -> {return a;};
        
        InvocationHandler myproxy=(Object ob, Method method, Object[] params)->{return method.invoke(ob, args);};
        
     }
     public static void doSomething(Integer integer){
         integer=new Integer(2);
         }
     
     public void say() {
         System.out.println("say");
     }
     
     interface LamdaOperation{
         int getA(int a,int b);
     }
}

