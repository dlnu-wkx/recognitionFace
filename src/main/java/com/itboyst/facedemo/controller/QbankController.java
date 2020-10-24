package com.itboyst.facedemo.controller;

import com.itboyst.facedemo.service.QbankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class QbankController {


    @Autowired
    QbankService Qservice;

    @RequestMapping("/findallquestion")
    @ResponseBody
    public Map<String, Object> findall(HttpServletResponse response) {
        return Qservice.findallQuestion(response);
    }
    /**
     * 跳转到题目库
     * weikaixuan 2020-10-13
     * @return
     */
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test(){

        return "test";
    }

}
