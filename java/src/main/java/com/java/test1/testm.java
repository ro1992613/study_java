package com.java.test1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        
        testm tm=new testm();
        int[] s= {-1,0,1,2,-1,-4};
        System.out.println(tm.threeSum(s));
    }
    
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list=new ArrayList<>();
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                for(int k=j+1;k<nums.length;k++){
                    if(nums[i]+nums[j]+nums[k]==0){
                        List<Integer> temp=new ArrayList<>();
                        temp.add(nums[i]);
                        temp.add(nums[j]);
                        temp.add(nums[k]);
                        if(check(list,temp)) {
                            list.add(temp);
                        }
                        System.out.println("end list");
                        System.out.println(list);
                        System.out.println();
                    }
                }
            }
        }
        return list;
    }
    
    public boolean check(List<List<Integer>> list,List<Integer> temp){
        if(list.size()==0) {return true;}
        for(List<Integer> v:list){
            Collections.sort(v);
            Collections.sort(temp);
            System.out.println();
            System.out.println(v);
            System.out.println(temp);
            System.out.println();
            for(int i=0;i<=2;i++) {
                System.out.println("***"+v.get(i) +"   "+ temp.get(i));
                if(v.get(i) != temp.get(i)) {
                    return true;
                }
            }
        }
        return false;
    }
}
