package com.itboyst.facedemo.controller;


import com.itboyst.facedemo.dto.Zstudent_login;
import com.itboyst.facedemo.dto.Zteacher_cookie;
import com.itboyst.facedemo.service.Zstudent_loginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class Zstudent_loginController {

    @Autowired
    Zstudent_loginService zstudent_loginService;

    @RequestMapping("/overclass")
    @ResponseBody
    public int overclass(HttpSession session,int num){
        int u=0;
        Zteacher_cookie zteacher_cookie=(Zteacher_cookie)session.getAttribute("zteacher_cookie");

        for (int k=0;k<num;k++){
           int w=zstudent_loginService.updateloginstate("强退",zteacher_cookie.getZtrainingroomid());
           if(w==1) u++;
        }
        return u;
    }
}
