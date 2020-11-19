package com.itboyst.facedemo.controller;

import com.itboyst.facedemo.dto.*;
import com.itboyst.facedemo.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class TimeStatusController {

    public final static Logger logger = LoggerFactory.getLogger(TimeStatusController.class);

    @Autowired
    Ztraining_facilityService ztraining_facilityService;

    @Autowired
    Zstudent_eventService zstudent_eventService;

    @Autowired
    ZstudentService zstudentService;

    @Autowired
    Ztask_content_logService ztask_content_logService;

    @Autowired
    Ztraining_task_contentService ztraining_task_contentService;
    /**
     * 根据老师的教室查找该教室所有的机床
     * 魏凯旋 2020-11-16
     * @param session
     * @param ztraining_room
     * @return
     */
    @RequestMapping("/findfacilitybytrainingroom")
    @ResponseBody
    public List<Ztraining_facility> findfacilitybytrainingroom(HttpSession session, String ztraining_room){
        Zteacher_cookie zteacher_cookie =(Zteacher_cookie) session.getAttribute("zteacher_cookie");
        String ztrainingroomID=zteacher_cookie.getZtrainingroomid();
        return ztraining_facilityService.findfactibyztrainingroomID(ztrainingroomID);

    }

    /**
     * 根据id查找用该机床的同学是否有请假举手的状态
     * 魏凯旋 2020-11-16
     * @param zid
     * @return
     */
    @RequestMapping("/findRaiseHand")
    @ResponseBody
    public Zstudent_event  findRaiseHand( String zid){
        Zstudent_event zstudent_event =zstudent_eventService.findRaiseHandByFacility(zid);

        return zstudent_event;

    }

    /**
     * 根据id查找机床是否是开机状态
     * 魏凯旋 2020-11-17
     * @param zid
     * @return
     */
    @RequestMapping("/findOpenPower")
    @ResponseBody
    public String   findOpenPower( String zid){
        String  zstudentPCIP  =ztraining_facilityService.findOpenPower(zid);

        return zstudentPCIP ;
    }

    /**
     * 根据机床的id找出使用学生的姓名
     * 魏凯旋2020-11-17
     * @param zid
     * @return
     */
    @RequestMapping("/findStudentName")
    @ResponseBody
    public String   findStudentName( String zid){
        String name =zstudentService.findStudentNameByfacilityID(zid);

        return name ;
    }

    /**
     * 根据设备的id找到相应学生的当前做的内容
     * 魏凯旋 2020-11-17
     * @param zid
     * @return
     */
    @RequestMapping("/presentProgess")
    @ResponseBody
    public Ztraining_task_content  presentProgess(String zid) {
        Ztraining_task_content ztraining_task_content = ztraining_task_contentService.findpresentProgessByfacilityID(zid);

        return ztraining_task_content;
    }

}
