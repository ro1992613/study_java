package com.java.test2;

import java.math.BigDecimal;

public class test {

    protected void methoda() {
        System.out.println("protected");
    }
    
    public static void main(String[] args) {
        int twCount5To10=1;
        int lwCount5To10=0;
        try {
            int count5To10Change = (int) (((float) (twCount5To10 - lwCount5To10) )/ lwCount5To10 * 100);
           System.out.println(count5To10Change);
           float s1=0;
           float s2=1; 
           int s3=0;
           int s=(int) (s2/s3*100);
           System.out.println(s);
           BigDecimal twAvgSystemPrice=new BigDecimal("0");
           System.out.println(twAvgSystemPrice.intValue()==0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
