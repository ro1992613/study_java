package com.java.test1;

import com.java.test2.test;

public class testm extends test{

    public static void main(String[] args) {
        String a="hello";
        String b="hello";
        System.out.println(a==b);
        a="hello   ".trim();
        System.out.println(a==b);
        a="hello";
        System.out.println(a==b);
    }
}
