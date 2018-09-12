package com.auth.demo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys")
public class ClientAuthorizationController {

    @GetMapping("/auth")
    public void auth(HttpServletRequest req,HttpServletResponse rsp) throws IOException, ServletException {
        String url=req.getParameter("url");
        String token=req.getParameter("token");
        //判断token
        if(checkToken(token)) {
            //根据token拿到权限
            req.getSession().setAttribute("user", "login");
            //返回到原地址
            rsp.sendRedirect(url);
        }
        rsp.getWriter().write("success");
    }
    
    @GetMapping("/user")
    public void user(HttpServletRequest req,HttpServletResponse rsp) throws IOException {
        String user = (String) req.getSession().getAttribute("user");
        System.out.println(req.getSession().getId());
        System.out.println(user);
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
    
    @GetMapping("/login")
    public void login(HttpServletRequest req) {
        req.getSession().setAttribute("user", "dracula");
    }
    
    @GetMapping("/logout")
    public void logout(HttpServletRequest req) {
        req.getSession().setAttribute("user", null);
    }
    
    public boolean checkToken(String token) {
        
        return true;
    }
}
