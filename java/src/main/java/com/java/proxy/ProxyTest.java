package com.java.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyTest {

    public static void main(String[] args) throws NoSuchMethodException, SecurityException, Throwable {
        PersonProxy proxy=new PersonProxy();
        Person p=new Person();
        proxy.setPerson(p);
        proxy.invoke(p, Person.class.getMethod("said"), null);
        
        SimpleProxy sp=new SimpleProxy();
        String[] s= {"abc"};
        sp.invoke(p, Person.class.getMethod("see"), null);
        sp.invoke(p, Person.class.getMethod("saidWord", String.class), s);
    }
}

class SimpleProxy implements InvocationHandler{

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(proxy, args); 
        return result;
    }
    
}
