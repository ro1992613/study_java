package com.oauth.demo;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthorizationController {
    
    @GetMapping("")
    public void authorize(HttpServletRequest req,HttpServletResponse rsp) throws IOException {
        //原地址url
        String url=req.getParameter("url");
        //子系统的id
        String clinet_id=req.getParameter("clinet");
        //根据系统id，找到子系统的认证地址
        String client_auth_url=getClientAuthUrl(clinet_id);
        //是否登陆了
        if(req.getSession().getAttribute("user")!=null) {
            //重定向到子系统认证地址，附上原来的url和认证码
            rsp.sendRedirect(client_auth_url+"?url="+URLEncoder.encode(url,"utf-8")+"&token="+"12345678");
        }else {
            
        }
    }
    //获取认证地址
    public String getClientAuthUrl(String clinet_id) {
        String client_auth_url="http://127.0.0.1:8082/sys/auth";
        return client_auth_url;
    }
}
