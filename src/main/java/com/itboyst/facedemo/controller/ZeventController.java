package com.itboyst.facedemo.controller;

import com.itboyst.facedemo.base.Iputil;
import com.itboyst.facedemo.dto.Zstudent;
import com.itboyst.facedemo.dto.Zstudent_cookie;
import com.itboyst.facedemo.dto.Zstudent_event;
import com.itboyst.facedemo.service.Zstudent_eventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.UUID;

import static com.itboyst.facedemo.base.UUIDutil.ReplaceSQLChar;

@Controller
public class ZeventController {
    public final static Logger logger = LoggerFactory.getLogger(ZeventController.class);


    @Autowired
    Zstudent_eventService zstudent_eventService;

    @RequestMapping("/insertevent")
    @ResponseBody
    public int insertevent(HttpSession session, String ztype, String zcontent, HttpServletRequest request){

        //字符替换
        zcontent =ReplaceSQLChar(zcontent);

        //System.out.println(ztype+zcontent);
        Zstudent_event zstudent_event =new Zstudent_event();

        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        zstudent_event.setZid(uuid);

        //将请假的id存入session中去
        session.setAttribute("zstudent_eventid",uuid);


        Zstudent zstudent=(Zstudent)session.getAttribute("zstudent") ;
        String zstudentid=zstudent.getZid();

        zstudent_event.setZstudentID(zstudentid);

        zstudent_event.setZstatus("取消");
        zstudent_event.setZtype(ztype);
        if (zcontent!=null && zcontent!=""){
            zstudent_event.setZcontent(zcontent);
        }

        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        zstudent_event.setZapplicationtime(timestamp);


        //插入之前将之前的事件状态更改
        zstudent_eventService.updateeventstatus(zstudent_event);

        zstudent_event.setZstatus("申请中");

        Zstudent_cookie zstudent_cookie=(Zstudent_cookie)session.getAttribute("zstudent_cookie");

        zstudent_event.setZscheduleID(zstudent_cookie.getZscheduleID());
        zstudent_event.setZrecognizeIP(Iputil.getClientIpAddress(request));
        //插入新的学生事件信息
        return zstudent_eventService.insertevent(zstudent_event);
    }

    /**
     * 取消举手
     * @param session
     * @return
     */
    @RequestMapping("/removeup")
    @ResponseBody
    public int removeup(HttpSession session){
        Zstudent zstudent=(Zstudent) session.getAttribute("zstudent");

        Zstudent_event zstudent_event=new Zstudent_event();
        zstudent_event.setZstudentID(zstudent.getZid());
        zstudent_event.setZtype("举手");
        zstudent_event.setZstatus("取消");


        return zstudent_eventService.updateeventstatus(zstudent_event);
    }

    @RequestMapping("/deleteleave")
    @ResponseBody
    public int deleteleave(HttpSession session){
        Zstudent zstudent=(Zstudent) session.getAttribute("zstudent");

        Zstudent_event zstudent_event=new Zstudent_event();
        zstudent_event.setZstudentID(zstudent.getZid());
        zstudent_event.setZtype("请假");
        zstudent_event.setZstatus("取消");

        return zstudent_eventService.updateeventstatus(zstudent_event);

    }

    @RequestMapping("/outsystem")
    @ResponseBody
    public int outsystem(String ztype,HttpSession session){
        Zstudent zstudent=(Zstudent) session.getAttribute("zstudent");
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        String uuid = UUID.randomUUID().toString().replaceAll("-","");

        Zstudent_event zstudent_event=new Zstudent_event();
        zstudent_event.setZid(uuid);
        zstudent_event.setZstudentID(zstudent.getZid());
        zstudent_event.setZtype(ztype);
        zstudent_event.setZstatus("申请中");
        zstudent_event.setZapplicationtime(timestamp);

        return zstudent_eventService.insertevent(zstudent_event);
    }

    @RequestMapping("/removeout")
    @ResponseBody
    public int removeout(HttpSession session){
        Zstudent zstudent=(Zstudent) session.getAttribute("zstudent");
        Zstudent_event zstudent_event=new Zstudent_event();
        zstudent_event.setZstudentID(zstudent.getZid());
        zstudent_event.setZtype("退出系统");
        zstudent_event.setZstatus("取消");

        return zstudent_eventService.updateeventstatus(zstudent_event);
    }



}
