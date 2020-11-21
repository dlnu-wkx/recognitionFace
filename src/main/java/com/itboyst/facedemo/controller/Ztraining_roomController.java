package com.itboyst.facedemo.controller;

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
        return ztraining_facilityService.updateallfacility(ztrainroomid,zpowerstatus);
    }


    @RequestMapping("/updateallfacilitybyzid")
    @ResponseBody
    public int updateallfacilitybyzid(@RequestParam(value = "zid[]")String [] zid, @RequestParam(value = "zpowerstatus")String  zpowerstatus){
        int j,k=0;
        for (int i=0;i<zid.length;i++){
           j= ztraining_facilityService.updateallfacilitybyzid(zid[i], zpowerstatus);
           if(j==1)k++;
        }
      return k;
    }



}
