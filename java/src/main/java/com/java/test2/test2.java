package com.java.test2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class test2 {

    public static void main(String[] args) {
        test2 t=new test2();
       System.out.println( t.countPrimes("([)]"));;

    }
    
    public boolean countPrimes(String s) {
        LinkedList<String> link=new LinkedList<>();
        for(int i=0;i<=s.length()-1;i++) {
            try {
                String t=s.substring(i, i+1);
                if(t.equals("{") || t.equals("(") || t.equals("[") ) {
                    link.push(t);
                }
                System.out.println("t="+t+" last="+link.getLast());
                if(t.equals("}")) {
                    if(!link.removeLast().equals("{")) {
                        return false;
                    }
                }
                if(t.equals(")")) {
                    if(!link.removeLast().equals("(")) {
                        return false;
                    }
                }
                if( t.equals("]")) {
                    if(!link.removeLast().equals("[")) {
                        return false;
                    }
                }
                System.out.println("link=  "+link);
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }
    
    public boolean isReal(int n,List<Integer> list){
        boolean rs=false;
        for(Integer i:list){
            if(n%i==0){
                rs=true;
                break;
            }
        }
        return rs;
    }
}
