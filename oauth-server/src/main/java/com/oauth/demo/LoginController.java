package com.oauth.demo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys")
public class LoginController {

    @GetMapping("/login")
    public void login(HttpServletRequest req) {
        req.getSession().setAttribute("user", "dracula");
    }
    
    public static void main(String[] args) {
        Object user=null;
        String a=(String)user;
        String b=null;
        String c=b+"c";
        System.out.println(a==null);
    }
    
    @GetMapping("/user")
    public void user(HttpServletRequest req,HttpServletResponse rsp) throws IOException {
        String user = (String) req.getSession().getAttribute("user");
        if(user!=null) {
            PrintWriter writer=rsp.getWriter();
            writer.write(user);
            writer.write(" status:login");
            writer.flush();
            writer.close();
        }else {
            PrintWriter writer=rsp.getWriter();
            writer.write("status:logout");
            writer.flush();
            writer.close();
        }
    }
    
    @GetMapping("/logout")
    public void logout(HttpServletRequest req) {
        req.getSession().setAttribute("user", null);
    }
}
