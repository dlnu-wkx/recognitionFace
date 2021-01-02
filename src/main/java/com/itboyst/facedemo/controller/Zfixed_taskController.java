package com.itboyst.facedemo.controller;

import com.itboyst.facedemo.dto.*;
import com.itboyst.facedemo.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import static com.itboyst.facedemo.base.UUIDutil.ReplaceSQLChar;

@Controller
public class Zfixed_taskController {
    public final static Logger logger = LoggerFactory.getLogger(Zfixed_taskController.class);

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

    @Autowired
    Ztraining_task_assessService ztraining_task_assessService;

    @Autowired
    Ztask_inputService ztask_inputService;


    @RequestMapping("/findallfixedtasks")
    @ResponseBody
    public List<Zfixed_task> findalltemporarytask(HttpSession session){

        Zstudent_cookie zstudent_cookie =(Zstudent_cookie)session.getAttribute("zstudent_cookie");

      //  System.out.println(zfixed_taskService.findallfixedtask());

        return zfixed_taskService.findallfixedtask(zstudent_cookie.getZstudent_scheduleid());

    }


    @RequestMapping("/updatetasktime")
    @ResponseBody
    public int updatetasktime(String taskid,HttpSession session,String zassign_scheduleid,String task_contentid){
        Zstudent_cookie zstudent_cookie=(Zstudent_cookie)session.getAttribute("zstudent_cookie");

        String zstudentscheduleID=zstudent_cookie.getZstudent_scheduleid();

        String zscheduleID=zstudent_cookie.getZscheduleID();

        String studentid=zstudent_cookie.getZstudentID();

        int w=0,v=0;
        //更新上课学生表state状态为在上课
        int j= zstudent_scheduleService.updatestate("在上课",zscheduleID,studentid);

        Timestamp timestamp=new Timestamp(System.currentTimeMillis());

        //System.out.println(timestamp);

        int i= zassign_scheduleService.updatechecktime(timestamp,zstudentscheduleID,taskid);

        int k= ztask_content_logService.findnumcontentlog(zassign_scheduleid,task_contentid);

        if(k==0){
            Ztask_content_log ztask_content_log =new Ztask_content_log();
            String zid = UUID.randomUUID().toString().replaceAll("-","");
            ztask_content_log.setZid(zid);
            ztask_content_log.setZassignscheduleID(zassign_scheduleid);
            ztask_content_log.setZid(zid);
            ztask_content_log.setZstarttime(timestamp);
            ztask_content_log.setZtrainingtaskcontentID(task_contentid);
            w=ztask_content_logService.insertcontentlog(ztask_content_log);
        }else {
            v=ztask_content_logService.updatecontentlogstart(zassign_scheduleid,task_contentid,timestamp);
        }

        if(i==1&&(w==1||v==1)){
            return 1;
        }


       return 0;
    }



    @RequestMapping("/findallnandf")
    @ResponseBody
    public List<Zstudent_facility> findallnandf(HttpSession session){
        Zteacher_cookie zteacher_cookie=(Zteacher_cookie)session.getAttribute("zteacher_cookie");
        String ztrainingroomid =zteacher_cookie.getZtrainingroomid();
        String zscheduleid=zteacher_cookie.getZscheduleID();

       // System.out.println(zname_facilityService.findnameandfaclity(ztrainingroomid,zscheduleid));
        return zname_facilityService.findnameandfaclity(ztrainingroomid,zscheduleid);
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

    @RequestMapping("/insertfixedtask")
    @ResponseBody
    public int  insertfixedtask(String studentid,String taskid,HttpSession session){
        String zid = UUID.randomUUID().toString().replaceAll("-","");

        Timestamp timestamp=new Timestamp(System.currentTimeMillis());

        Zteacher_cookie zteacher_cookie=(Zteacher_cookie)session.getAttribute("zteacher_cookie");

        String zscheduleid=zteacher_cookie.getZscheduleID();

        return zassign_scheduleService.insertfixedtask(zid,zscheduleid,studentid,taskid,timestamp);
    }


    @RequestMapping("/updatetaskendtime")
    @ResponseBody
    public int updatetaskendtime(String zassign_scheduleid,String task_contentid){

        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        return ztask_content_logService.updatecontentlogend(zassign_scheduleid,task_contentid,timestamp);
    }

    @RequestMapping("/updatealltaskend")
    @ResponseBody
    public int updatealltaskend(String ztrainingtaskID,HttpSession session,String task_contentid,String zassign_scheduleid){
        Zassign_schedule zassign_schedule =new Zassign_schedule();
        Zstudent_cookie zstudent_cookie=(Zstudent_cookie)session.getAttribute("zstudent_cookie");
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());

        zassign_schedule.setZcompletetime(timestamp);
        zassign_schedule.setZstudentscheduleID(zstudent_cookie.getZstudent_scheduleid());
        zassign_schedule.setZtrainingtaskID(ztrainingtaskID);

        int i=ztask_content_logService.updatecontentlogend(zassign_scheduleid,task_contentid,timestamp);

        return zassign_scheduleService.updatecompletetime(zassign_schedule);

    }


    @RequestMapping("/findtaskassessbytrainingid")
    @ResponseBody
    public List<Ztraining_task_assess> findtaskassessbytrainingid(String ztraining_taskID){

        return ztraining_task_assessService.findallbyztrainingtaskID(ztraining_taskID);
    }

    @RequestMapping("/inserttaskinput")
    @ResponseBody
    public int inserttaskinput(@RequestParam(value = "zassign_scheduleid")String zassign_scheduleid,@RequestParam(value = "zselfcheck[]")String [] zselfcheck,@RequestParam(value = "ztrainingtaskassessID[]")String [] ztrainingtaskassessID){
        int j,k=0;
        int w=0;
        for (int i=0;i<zselfcheck.length;i++){
            //字符替换
            zselfcheck[i] =ReplaceSQLChar(zselfcheck[i]);

            Ztask_input ztask_input=new Ztask_input();
            String zid = UUID.randomUUID().toString().replaceAll("-","");
            ztask_input.setZid(zid);
            ztask_input.setZselfcheck(zselfcheck[i]);
            ztask_input.setZtrainingtaskassessID(ztrainingtaskassessID[i]);
            ztask_input.setZassignscheduleID(zassign_scheduleid);

            w=ztask_inputService.findistaskinput(ztask_input);
            //System.out.println(w);
            if(w==0){
                j=ztask_inputService.isnerttaskinput(ztask_input);
            }else {
                 j=ztask_inputService.updatetaskselfcheck(ztask_input);
            }
            if(j>0)k++;
        }

        return k;
    }






}
