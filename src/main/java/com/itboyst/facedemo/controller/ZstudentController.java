package com.itboyst.facedemo.controller;

import com.itboyst.facedemo.dto.Zschedule;
import com.itboyst.facedemo.dto.Zstudent;
import com.itboyst.facedemo.service.ZstudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ZstudentController {
    public final static Logger logger = LoggerFactory.getLogger(ZstudentController.class);


    @Autowired
    ZstudentService zstudentService;

    @RequestMapping("/findallsname")
    @ResponseBody
    public List<String> findallsaname(HttpSession session){

        Zschedule zschedule =(Zschedule)session.getAttribute("zschedule");

        return zstudentService.findnamebyzscheduleID(zschedule.getZid());
    }

    @RequestMapping("/findallbystudentname")
    @ResponseBody
    public Zstudent findallbystudentname(String name){

        return zstudentService.findallbystudentname(name);
    }

    @RequestMapping("/findstudentnamelike")
    @ResponseBody
    public List<String> findstudentnamelike(String name){
        return zstudentService.findstudentnamelike(name);
    }


}
