package com.java.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired User usera;
    @Autowired User userb;
    @Autowired SuperUser superUsera;
    @GetMapping("/test1")
    public void test1() {
        System.out.println(usera.getName());
    }
    
    @GetMapping("/test2")
    public void test2() {
        System.out.println(userb.getName());
    }
    
    @GetMapping("/test3")
    public void test3() {
        System.out.println(superUsera.getA());
    }
}
