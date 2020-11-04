package com.itboyst.facedemo.controller;

import com.itboyst.facedemo.service.QbankService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class TeachController {
    public final static Logger logger = LoggerFactory.getLogger(TeachController.class);

    /**
     * 跳转到显示题目库的页面
     * weikaixuan 2020-10-13
     * @return
     *//*
    @RequestMapping(value = "/student_test",method = RequestMethod.GET)
    public String test(){

        return "studentTest";
    }*/

    /**
     *如果老师的人脸检测成功则可以进入操作页面
     * 魏凯旋 2020-10-31
     * @param name
     * @param faceId
     * @param path
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/teachLogin", method = RequestMethod.GET)
    public String teachRegister(@CookieValue("name") String name, @CookieValue("faceId") String faceId,@CookieValue("path") String path, Model model) throws Exception {
        model.addAttribute("faceId",faceId);
        model.addAttribute("name",name);
        String indPath  =path.replace("F:/recognitionFace/src/main/resources/static/","");
        model.addAttribute("path",indPath);
        return "fieldManagement";
    }

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

    /**老师进入的主页面跳转到右侧功能页面的控制器
     * 魏凯旋 2020-11-04
     * @return
     */
    @RequestMapping(value = "/field_management")
    public String field_management(){return "field_management";}

}
