package com.java.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class PersonProxy implements InvocationHandler{

    Person p;
    
    public void setPerson(Person p) {
        this.p=p;
    } 
    
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(this.p, args); 
        return result;
    }

}
