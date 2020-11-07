package com.itboyst.facedemo.controller;

import com.itboyst.facedemo.service.QbankService;
import com.itboyst.facedemo.service.ZstudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class ZstudentController {

    public final static Logger logger = LoggerFactory.getLogger(TeachController.class);
    @Autowired
    QbankService Qservice;

    /**
     * 学生注册控制器
     * 魏凯旋 2020-11-4
     * @return
     */
    @RequestMapping(value = "/face_registration")
    public String face_registration(){return "face_registration";}

    /**
     * 学生端人脸检验成功后跳转后的控制器
     * 魏凯旋  2020-10-23
     * @param name
     * @param faceId
     * @param model
     * @param path 存储的图片路径
     * @return
     * @throws Exception
     */

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String register(@CookieValue("name") String name, @CookieValue("faceId") String faceId, @CookieValue("path") String path, Model model) throws Exception {
        model.addAttribute("faceId",faceId);
        model.addAttribute("name",name);
        String indPath  =path.replace("F:/recognitionFace/src/main/resources/static/","");
        model.addAttribute("path",indPath);
        return "studentEnter";
    }

    /**
     * 从数据库中取出测试题
     * @param response
     * @return
     */
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
/*

    @Autowired
    ZstudentService zstudentserice;
*/

   /* @RequestMapping("/registerstudent")
    @ResponseBody
    public int registerstudent(){

    }*/
}
