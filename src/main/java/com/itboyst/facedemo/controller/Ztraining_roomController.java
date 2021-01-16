package com.itboyst.facedemo.controller;

import com.itboyst.facedemo.base.Iputil;
import com.itboyst.facedemo.base.Powerutil;
import com.itboyst.facedemo.dto.TimeStatusStudent;
import com.itboyst.facedemo.dto.Zteacher_cookie;
import com.itboyst.facedemo.dto.Ztraining_facility;
import com.itboyst.facedemo.dto.ztraining_room;
import com.itboyst.facedemo.service.TimeStatusStudentService;
import com.itboyst.facedemo.service.Ztraining_facilityService;
import com.itboyst.facedemo.service.Ztraining_roomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.util.List;

@Controller
public class Ztraining_roomController {
    public final static Logger logger = LoggerFactory.getLogger(Ztraining_roomController.class);

    @Autowired
    Ztraining_roomService ztraining_roomService;

    @Autowired
    Ztraining_facilityService ztraining_facilityService;

    @Autowired
    TimeStatusStudentService timeStatusStudentService;


    /*
   勾选开启安全测试
  */
    @RequestMapping("/updateftestbych")
    @ResponseBody
    public int updateftestbych(HttpSession session,@RequestParam(value = "zpassingscore")int zpassingscore,@RequestParam(value = "zid[]")String [] zid,@RequestParam(value = "zsafetestingNum")int zsafetestingNum,@RequestParam(value = "zsafetestingType")String zsafetestingType){
        int j=0;
        for (int i=0;i<zid.length;i++){
                Ztraining_facility ztraining_facility=new Ztraining_facility();
                ztraining_facility.setZid(zid[i]);
                ztraining_facility.setZpassingscore(zpassingscore);
                ztraining_facility.setZsafetestingNum(zsafetestingNum);
                ztraining_facility.setZsafetestingType(zsafetestingType);
                j=ztraining_facilityService.updatefatestbyid(ztraining_facility);
                System.out.println(ztraining_facility);
            }
        return j;
    }





    @RequestMapping("/updatefatestbyroomid")
    @ResponseBody
    public int updatefatestbyroomid(Ztraining_facility ztraining_facility){
        //System.out.println(ztraining_facility);
        return ztraining_facilityService.updatefatestbyroomid(ztraining_facility);
    }


    @RequestMapping("/findstunamebyfacid")
    @ResponseBody
    public String findstunamebyfacid(String zid){
        //System.out.println(zid);
        return ztraining_facilityService.findstunamebyfacid(zid);
    }



    @RequestMapping("/findalltrainroom")
    @ResponseBody
    public List<ztraining_room> findalltrainroom(){
        return ztraining_roomService.findallztrainroom();
    }

    @RequestMapping("/findfacilitybyrid")
    @ResponseBody
    public List<Ztraining_facility> findfacilitybyrid(String id){

        return ztraining_facilityService.findfacilitybyrid(id);
    }


    @RequestMapping("/findteststatebyfid")
    @ResponseBody
    public String findteststatebyfid(String id){
        Ztraining_facility ztraining_facility=ztraining_facilityService.findcontrollerbyid(id);
        //所有的状态
        String allstate =Powerutil.powerstate(ztraining_facility.getZpowerIP());

        //System.out.println(allstate);
        //第六个的状态
        String singlesix=allstate.substring(5, 6);
      // System.out.println(singlesix);
        return singlesix;

    }


    @RequestMapping("/findteststatebyIP")
    @ResponseBody
    public String findteststatebyIP(HttpServletRequest request,HttpSession session){

        Ztraining_facility ztraining_facility=ztraining_facilityService.findbyip(Iputil.getClientIpAddress(request));
        String allstate =Powerutil.powerstate(ztraining_facility.getZpowerIP());
        //第六个的状态
        String singlesix=allstate.substring(5, 6);
        session.setAttribute("sixstate",singlesix);

        return singlesix;

    }


    @RequestMapping("/updatesixstateaftertest")
    @ResponseBody
    public int updatesixstateaftertest(HttpSession session){
        System.out.println("通过测试后继电器:");
        Ztraining_facility ztraining_facility=(Ztraining_facility)session.getAttribute("ztraining_facility");

        String IP=ztraining_facility.getZpowerIP();

        return Powerutil.powercontroller(IP,"26");

    }



    @RequestMapping("/usixout")
    @ResponseBody
    public void usixout(HttpSession session){

        String sixstate=(String)session.getAttribute("sixstate");
        System.out.println("登陆时6的状态:"+sixstate);
        System.out.println("退出后继电器:");
        if (sixstate.equals("1")){

            Ztraining_facility ztraining_facility=(Ztraining_facility)session.getAttribute("ztraining_facility");

            String IP=ztraining_facility.getZpowerIP();

            Powerutil.powercontroller(IP,"16");
        }
    }


    @RequestMapping("/findloginnamebyfaid")
    @ResponseBody
    public String findloginnamebyfaid(String id){
        return ztraining_facilityService.findstunamebyfacid(id);
    }




    @RequestMapping("/updateallfacility")
    @ResponseBody
    public int updateallfacility(String ztrainroomid,String zpowerstatus){
       // System.out.println(ztrainroomid+zpowerstatus);

        List<Ztraining_facility> data =ztraining_facilityService.findfactibyztrainingroomID(ztrainroomid);

       // System.out.println(data);
        int j=0;int k=0;
       // String powerid="";
        //想要关机
        if (zpowerstatus.equals("未开机")){
            int a=ztraining_facilityService.updatesixportbyroomid(ztrainroomid,0);
            for (int i=0;i<data.size();i++){
              //  powerid="2"+data.get(i).getZpowerPort();
               // System.out.println(powerid);
                j = Powerutil.powercontroller(data.get(i).getZpowerIP(),"26");
                if (j>0)k++;
            }
            //想要开机
        }else{
            int b=ztraining_facilityService.updatesixportbyroomid(ztrainroomid,1);
            for (int u=0;u<data.size();u++){
               // powerid="1"+data.get(u).getZpowerPort();
                //System.out.println(powerid);
                j = Powerutil.powercontroller(data.get(u).getZpowerIP(),"16");
                if (j>0)k++;

            }
        }

        return ztraining_facilityService.updateallfacility(ztrainroomid,zpowerstatus);

    }



    @RequestMapping("/updateallfacilitybyzid")
    @ResponseBody
    public int updateallfacilitybyzid(@RequestParam(value = "zid[]")String [] zid, @RequestParam(value = "zpowerstatus")String  zpowerstatus, @RequestParam(value = "kind")String  kind){
        System.out.println(zid+zpowerstatus+kind);

        int j,k=0;
        int q,p=0;
        String powerid="";

        for (int i=0;i<zid.length;i++){

            Ztraining_facility ztraining_facility2=new Ztraining_facility();
            ztraining_facility2.setZid(zid[i]);

           j= ztraining_facilityService.updateallfacilitybyzid(zid[i], zpowerstatus);
           Ztraining_facility ztraining_facility=ztraining_facilityService.findcontrollerbyid(zid[i]);

           // System.out.println(ztraining_facility);
           if (kind.equals("关闭")){
               powerid="26";
               ztraining_facility2.setZpowerStatus6(0);
           }else if(kind.equals("开启")){
               powerid="16";
               ztraining_facility2.setZpowerStatus6(1);
           }
           //更新数据库继电器6端口的值
            p=ztraining_facilityService.updatesixportbyid(ztraining_facility2);

           //System.out.println(powerid);
           q = Powerutil.powercontroller(ztraining_facility.getZpowerIP(),powerid);

           //System.out.println(q);
           if (q>0)p++;

           if(j==1)k++;
        }
      return k;
    }



}
