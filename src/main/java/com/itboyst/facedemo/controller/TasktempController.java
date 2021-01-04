package com.itboyst.facedemo.controller;

import com.itboyst.facedemo.dto.Zstudent;
import com.itboyst.facedemo.dto.Zteacher_cookie;
import com.itboyst.facedemo.dto.Zteacher_temporary_task;
import com.itboyst.facedemo.service.Zassign_scheduleService;
import com.itboyst.facedemo.service.Zteacher_temporary_taskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Controller
public class TasktempController {

    @Autowired
    Zteacher_temporary_taskService zteacher_temporary_taskService;

    @Autowired
    Zassign_scheduleService zassign_scheduleService;





    @RequestMapping("/findalltemporarytask")
    @ResponseBody
    public List<Zteacher_temporary_task> findalltemporarytask(HttpSession session){

        Zstudent zstudent=(Zstudent)session.getAttribute("zstudent");
        //**session失活是为null,异常处理
        String zid ="";
        try{
            zid=zstudent.getZid();
        }catch (Exception e){
            System.out.println("TasktempController中/findalltemporarytask zstudent的session失活");
        }


        return zteacher_temporary_taskService.findtaskname(zid);

    }

    @RequestMapping("/findisinassigan")
    @ResponseBody
    public int findisinassigan(String studentid,String taskid,HttpSession session){
        //System.out.println(studentid+taskid);
        Zteacher_cookie zteacher_cookie =(Zteacher_cookie)session.getAttribute("zteacher_cookie");
        return zassign_scheduleService.findtaskisin(taskid,studentid,zteacher_cookie.getZscheduleID());

    }

    @RequestMapping("/findisintemp")
    @ResponseBody
    public int findisintemp(String studentid,String taskid){
       // System.out.println(studentid+taskid);
        return zteacher_temporary_taskService.findisintemp(taskid,studentid);

    }

    @RequestMapping("/inserttemptask")
    @ResponseBody
    public int inserttemptask(String studentid,String taskid){

        String zid = UUID.randomUUID().toString().replaceAll("-","");

        Timestamp timestamp=new Timestamp(System.currentTimeMillis());

        Zteacher_temporary_task zteacher_temporary_task=new Zteacher_temporary_task();
        zteacher_temporary_task.setZid(zid);
        zteacher_temporary_task.setZcontentID(taskid);
        zteacher_temporary_task.setZpublishtime(timestamp);
        zteacher_temporary_task.setZstudentID(studentid);
        //System.out.println(zteacher_temporary_task);

        return zteacher_temporary_taskService.inserttemptask(zteacher_temporary_task);
    }



}
