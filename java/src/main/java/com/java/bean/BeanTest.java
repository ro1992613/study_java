package com.java.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class BeanTest {

    @Bean
    public User usera() {
        User a= new User();
        a.setName("a");
        return a;
    }
    @Bean
    public User userb() {
        User b= new User();
        b.setName("b");
        return b;
    }
    @Bean
    @Primary
    public SuperUser superUsera(User usera) {
        SuperUser s=new SuperUser();
        s.setUser(usera);
        s.setA(usera.getName());
        return s;
    }
}
