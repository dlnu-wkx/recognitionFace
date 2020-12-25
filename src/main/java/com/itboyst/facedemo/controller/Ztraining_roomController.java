package com.itboyst.facedemo.controller;

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


    @RequestMapping("/updateallfacility")
    @ResponseBody
    public int updateallfacility(String ztrainroomid,String zpowerstatus){
       // System.out.println(ztrainroomid+zpowerstatus);

        List<Ztraining_facility> data =ztraining_facilityService.findfactibyztrainingroomID(ztrainroomid);

       // System.out.println(data);
        int j=0;int k=0;
        String powerid="";
        //想要关机
        if (zpowerstatus.equals("未开机")){
            for (int i=0;i<data.size();i++){
                powerid="2"+data.get(i).getZpowerPort();
               // System.out.println(powerid);
                j = Powerutil.powercontroller(data.get(i).getZpowerIP(),powerid);
                if (j>0)k++;
            }
            //想要开机
        }else{
            for (int u=0;u<data.size();u++){
                powerid="1"+data.get(u).getZpowerPort();
                //System.out.println(powerid);
                j = Powerutil.powercontroller(data.get(u).getZpowerIP(),powerid);
                if (j>0)k++;

            }
        }


        return ztraining_facilityService.updateallfacility(ztrainroomid,zpowerstatus);


    }


    /**
     * 根据Id更新实训设备的通电情况
     * @param zid
     * @param zpowerstatus
     * @return
     */
    @RequestMapping("/updateallfacilitybyzid")
    @ResponseBody
    public int updateallfacilitybyzid(@RequestParam(value = "zid[]")String [] zid, @RequestParam(value = "zpowerstatus")String  zpowerstatus){
        int j,k=0;
        int q,p=0;
        String powerid="";

        for (int i=0;i<zid.length;i++){

           j= ztraining_facilityService.updateallfacilitybyzid(zid[i], zpowerstatus);
           Ztraining_facility ztraining_facility=ztraining_facilityService.findcontrollerbyid(zid[i]);

           // System.out.println(ztraining_facility);
           if (zpowerstatus.equals("未开机")){
               powerid="2"+ztraining_facility.getZpowerPort();
           }else {
               powerid="1"+ztraining_facility.getZpowerPort();
           }

           //System.out.println(powerid);
           q = Powerutil.powercontroller(ztraining_facility.getZpowerIP(),powerid);

           //System.out.println(q);
           if (q>0)p++;

           if(j==1)k++;
        }
      return k;
    }



}
