package com.itboyst.facedemo.controller;

import com.itboyst.facedemo.service.QbankService;
import org.apache.http.client.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public final static Logger logger = LoggerFactory.getLogger(QbankController.class);

    @Autowired
    QbankService Qservice;

    @RequestMapping("/findallquestion")
    @ResponseBody
    public Map<String, Object> findall(HttpServletResponse response) {
        return Qservice.findallQuestion(response);
    }
    /**
     * 跳转到显示题目库的页面
     * weikaixuan 2020-10-13
     * @return
     */
    @RequestMapping(value = "/student_test",method = RequestMethod.GET)
    public String test(){

        return "studentTest";
    }

    /**
     * 跳转测试
     * @return
     */

    @RequestMapping(value = "/power_controller")
    public String power_controller(){return "power_controller";}

    @RequestMapping(value = "/time_status")
    public String time_status(){return "time_status";}


    @RequestMapping(value = "/information_service")
    public String information_service(){return "information_service";}


    @RequestMapping(value = "/face_registration")
    public String face_registration(){return "face_registration";}



    @RequestMapping(value = "/information_delivery")
    public String information_delivery(){return "information_delivery";}


    @RequestMapping(value = "/fixed_task")
    public String fixed_task(){return "fixed_task";}



    @RequestMapping(value = "/class_ppt")
    public String class_ppt(){return "class_ppt";}



}
