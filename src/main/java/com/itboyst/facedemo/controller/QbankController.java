package com.itboyst.facedemo.controller;

import com.itboyst.facedemo.dto.*;
import com.itboyst.facedemo.service.QbankService;
import com.itboyst.facedemo.service.Zstudent_scheduleService;
import com.itboyst.facedemo.service.Ztesting_inputService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.itboyst.facedemo.base.UUIDutil.getUUID;

@Controller
public class QbankController {
    public final static Logger logger = LoggerFactory.getLogger(QbankController.class);

    @Autowired
    QbankService Qservice;

    @Autowired
    Ztesting_inputService ztesting_inputService;

    @Autowired
    Zstudent_scheduleService zstudent_scheduleService;



    /*
    勾选开启安全测试
   */
    @RequestMapping("/updatetestbychose")
    @ResponseBody
    public int updatetestbychose(HttpSession session,@RequestParam(value = "zpassingscore")int zpassingscore,@RequestParam(value = "zid[]")String [] zid){
        Zteacher_cookie zteacher_cookie=(Zteacher_cookie)session.getAttribute("zteacher_cookie");

        int j,k=0;
        for(int i=0;i<zid.length;i++){
            j=zstudent_scheduleService.updatetestbychose("是",zpassingscore,zteacher_cookie.getZscheduleID(),zid[i]);
            if(j==1)k++;
        }

        return k;
    }

    /*
        勾选不开启安全测试
     */
    @RequestMapping("/updatenotestbychose")
    @ResponseBody
    public int updatenotestbychose(HttpSession session,@RequestParam(value = "zid[]")String [] zid){
        Zteacher_cookie zteacher_cookie=(Zteacher_cookie)session.getAttribute("zteacher_cookie");

        int j,k=0;
        for(int i=0;i<zid.length;i++){
            j=zstudent_scheduleService.updatetestbychose("否",0,zteacher_cookie.getZscheduleID(),zid[i]);
            if(j==1)k+=1;
        }

        return k;
    }



    /*
    根据上课表id更新是否需要考试及考试分数
     */
    @RequestMapping("/updatetestbyscheduleid")
    @ResponseBody
    public int updatetestbyscheduleid(HttpSession session,int zpassingscore){
        Zteacher_cookie zteacher_cookie=(Zteacher_cookie)session.getAttribute("zteacher_cookie");
        return zstudent_scheduleService.updatetestbyscheduleid(zteacher_cookie.getZscheduleID(),"是",zpassingscore);
    }


    /*
   根据上课表id更新变成不需要测试，合格分数更为0
    */
    @RequestMapping("/updatenotestbyscheduleid")
    @ResponseBody
    public int updatenotestbyscheduleid(HttpSession session){
        Zteacher_cookie zteacher_cookie=(Zteacher_cookie)session.getAttribute("zteacher_cookie");
        //System.out.println(zteacher_cookie.getZscheduleID());
        return zstudent_scheduleService.updatetestbyscheduleid(zteacher_cookie.getZscheduleID(),"否",0);
    }



    /*
    查找合格分数
     */
    @RequestMapping("/findpassingcode")
    @ResponseBody
    public int findpassingcode(HttpSession session){
        Zstudent_cookie zstudent_cookie=(Zstudent_cookie)session.getAttribute("zstudent_cookie");
        //System.out.println(zstudent_cookie);
        return zstudent_cookie.getZpassingscore();
    }



    /*
    查找所有问题
     */
    @RequestMapping("/findallquestion")
    @ResponseBody
    public Map<String, Object> findall(HttpServletResponse response,HttpSession session) {
        Zstudent_cookie zstudent_cookie=(Zstudent_cookie)session.getAttribute("zstudent_cookie");

        String zstudentscheduleID=zstudent_cookie.getZstudent_scheduleid();

        int i = ztesting_inputService.deletebystscheid(zstudentscheduleID);

        int j=0;

        List<Ztesting_input> data = new ArrayList<>();

        Map<String, Object> safetest=Qservice.findallQuestion(response);
        List<Zsafe_testingDto> cbank=(List<Zsafe_testingDto>) safetest.get("cbank");
        List<Zsafe_testingDto> jbank=(List<Zsafe_testingDto>) safetest.get("jbank");
        String zsafetestid=null;
        String uuid[]=getUUID(5);

        for (int w=0;w<5;w++){
            // System.out.println(uuid);
            Ztesting_input ztesting_input=new Ztesting_input();
            ztesting_input.setZstudentscheduleID(zstudentscheduleID);
            ztesting_input.setZstate("未做");

            ztesting_input.setZid(uuid[w]);
                if (w<3){
                    zsafetestid=cbank.get(w).getZid();
                }else {
                    zsafetestid =jbank.get(w-3).getZid();
                }
                ztesting_input.setZsafetestingID(zsafetestid);
                ztesting_input.setZorder(w+1);
                data.add(ztesting_input);

        }

        j=ztesting_inputService.addtestinput(data);

        return safetest;
    }

    /*
    更新安全测试表，每刷一次更新一次
     */
    @RequestMapping("/updatetestinput")
    @ResponseBody
    public int updatetestinput(@RequestParam(value = "questionid[]")String [] questionid,@RequestParam(value = "ananswer[]")String [] ananswer,@RequestParam(value = "answercode[]")String [] answercode,HttpSession session){

        Zstudent_cookie zstudent_cookie=(Zstudent_cookie)session.getAttribute("zstudent_cookie");
        String zstudentscheduleID=zstudent_cookie.getZstudent_scheduleid();
        int j=0,k=0;

        for (int i=0;i<5;i++){
            Ztesting_input ztesting_input=new Ztesting_input();
            ztesting_input.setZstudentscheduleID(zstudentscheduleID);
            ztesting_input.setZorder(i);
            ztesting_input.setZstate("已做");
            ztesting_input.setZinput(ananswer[i]);
            ztesting_input.setZscore(Integer.parseInt(answercode[i]));
            ztesting_input.setZsafetestingID(questionid[i]);
            k=ztesting_inputService.updatelist(ztesting_input);

            if(k==1){
                j++;
            }

        }

        return j;
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

    @RequestMapping(value = "/temporary_task")
    public String temporary_task(){return "temporary_task";}



    @RequestMapping(value = "/class_ppt")
    public String class_ppt(){return "class_ppt";}

    @RequestMapping(value = "/teacher")
    public String teacher(){return "TeacherLogin";}


    /**
     * 查十个题目，备用
     * @param response
     * @param session
     * @return
     */
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
