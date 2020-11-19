package com.itboyst.facedemo.controller;


import com.itboyst.facedemo.dto.Zstudent_login;
import com.itboyst.facedemo.dto.Zteacher_cookie;
import com.itboyst.facedemo.service.Zstudent_loginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class Zstudent_loginController {
    public final static Logger logger = LoggerFactory.getLogger(Zstudent_loginController.class);


    @Autowired
    Zstudent_loginService zstudent_loginService;

    @RequestMapping("/overclass")
    @ResponseBody
    public int overclass(HttpSession session){
        Zteacher_cookie zteacher_cookie=(Zteacher_cookie)session.getAttribute("zteacher_cookie");

        return zstudent_loginService.updateloginstate("强退",zteacher_cookie.getZtrainingroomid());

    }
}
