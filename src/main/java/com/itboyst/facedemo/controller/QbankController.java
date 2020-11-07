package com.itboyst.facedemo.controller;

import com.itboyst.facedemo.dto.Zsafe_testingDto;
import com.itboyst.facedemo.dto.Zstudent;
import com.itboyst.facedemo.dto.Zstudent_cookie;
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
import javax.servlet.http.HttpSession;
import java.util.List;
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



    @RequestMapping("/findrand10")
    @ResponseBody
    public List<Zsafe_testingDto> findrand10(HttpServletResponse response, HttpSession session) {
        //从session中取出两个值
        Zstudent zstudent =(Zstudent)session.getAttribute("zstudent");
        String zstudentID=zstudent.getZid();

        Zstudent_cookie zstudent_cookie=(Zstudent_cookie) session.getAttribute("zstudent_cookie");
        String zsafetestingType=zstudent_cookie.getZsafetestingType();

        System.out.println( Qservice.findrand10(zstudentID,zsafetestingType).size());
        for (Zsafe_testingDto string : Qservice.findrand10(zstudentID,zsafetestingType)) {
            System.out.println(string);
        }

        return Qservice.findrand10(zstudentID,zsafetestingType);
    }

}
