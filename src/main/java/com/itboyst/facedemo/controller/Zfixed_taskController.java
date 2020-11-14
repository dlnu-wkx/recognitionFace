package com.itboyst.facedemo.controller;

import com.itboyst.facedemo.dto.Zfixed_task;
import com.itboyst.facedemo.dto.Zstudent_cookie;
import com.itboyst.facedemo.dto.Zstudent_facility;
import com.itboyst.facedemo.dto.Ztraining_task;
import com.itboyst.facedemo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

@Controller
public class Zfixed_taskController {

    @Autowired
    Zfixed_taskService zfixed_taskService;

    @Autowired
    Zassign_scheduleService zassign_scheduleService;

    @Autowired
    Zstudent_scheduleService zstudent_scheduleService;

    @Autowired
    Ztask_content_logService ztask_content_logService;

    @Autowired
    Zname_facilityService zname_facilityService;

    @Autowired
    Ztraining_taskService ztraining_taskService;


    @RequestMapping("/findallfixedtasks")
    @ResponseBody
    public List<Zfixed_task> findalltemporarytask(){


        return zfixed_taskService.findallfixedtask();

    }


    @RequestMapping("/updatetasktime")
    @ResponseBody
    public int updatetasktime(HttpSession session,String taskid){
        Zstudent_cookie zstudent_cookie=(Zstudent_cookie)session.getAttribute("zstudent_cookie");

        String zstudentscheduleID=zstudent_cookie.getZstudent_scheduleid();

        String zscheduleID=zstudent_cookie.getZscheduleID();

        String studentid=zstudent_cookie.getZstudentID();

        //更新上课学生表state状态为在上课
        int j= zstudent_scheduleService.updatestate("在上课",zscheduleID,studentid);

        if(j==1){
            return 1;
        }


            //当前系统时间
       /* Timestamp timestamp=new Timestamp(System.currentTimeMillis());

        System.out.println(timestamp+zstudentscheduleID+taskid);

        return zassign_scheduleService.updatechecktime(timestamp,zstudentscheduleID,taskid);*/


       return 0;
    }



    @RequestMapping("/findallnandf")
    @ResponseBody
    public List<Zstudent_facility> findallnandf(){
        return zname_facilityService.findnameandfaclity();
    }


    @RequestMapping("/findalltask")
    @ResponseBody
    public List<Ztraining_task> findalltask(){

        return ztraining_taskService.findalltask();
    }


    @RequestMapping("/findtasklikename")
    @ResponseBody
    public List<Ztraining_task> findtasklikename(String zname){
        return ztraining_taskService.findtasklike(zname);
    }




}
