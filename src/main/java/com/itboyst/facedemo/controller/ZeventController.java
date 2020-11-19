package com.itboyst.facedemo.controller;

import com.itboyst.facedemo.dto.Zstudent;
import com.itboyst.facedemo.dto.Zstudent_event;
import com.itboyst.facedemo.service.Zstudent_eventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.UUID;

@Controller
public class ZeventController {
    public final static Logger logger = LoggerFactory.getLogger(ZeventController.class);


    @Autowired
    Zstudent_eventService zstudent_eventService;

    @RequestMapping("/insertevent")
    @ResponseBody
    public int insertevent(HttpSession session,String ztype,String zcontent){

        //System.out.println(ztype+zcontent);
        Zstudent_event zstudent_event =new Zstudent_event();

        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        zstudent_event.setZid(uuid);

        //将请假的id存入session中去
        session.setAttribute("zstudent_eventid",uuid);


        Zstudent zstudent=(Zstudent)session.getAttribute("zstudent") ;
        String zstudentid=zstudent.getZid();

        zstudent_event.setZstudentID(zstudentid);

        zstudent_event.setZstatus("申请中");
        zstudent_event.setZtype(ztype);
        if (zcontent!=null ||zcontent!=""){
            zstudent_event.setZcontent(zcontent);
        }

        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        zstudent_event.setZapplicationtime(timestamp);


        return zstudent_eventService.insertevent(zstudent_event);
    }

    @RequestMapping("/removeup")
    @ResponseBody
    public int removeup(HttpSession session){
        String zid =(String) session.getAttribute("zstudent_eventid");

        return zstudent_eventService.delteup(zid);
    }

    @RequestMapping("/deleteleave")
    @ResponseBody
    public int deleteleave(HttpSession session){
        Zstudent zstudent=(Zstudent)session.getAttribute("zstudent");
        return  zstudent_eventService.deleteleave(zstudent.getZid(),"请假");

    }


}
