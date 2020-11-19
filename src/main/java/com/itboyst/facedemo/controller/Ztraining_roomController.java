package com.itboyst.facedemo.controller;

import com.itboyst.facedemo.dto.Ztraining_facility;
import com.itboyst.facedemo.dto.ztraining_room;
import com.itboyst.facedemo.service.Ztraining_facilityService;
import com.itboyst.facedemo.service.Ztraining_roomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class Ztraining_roomController {

    @Autowired
    Ztraining_roomService ztraining_roomService;

    @Autowired
    Ztraining_facilityService ztraining_facilityService;

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
    public int updateallfacilitybyzid(String zid,String zpowerstatus){
       // System.out.println(zid+zpowerstatus);
        return ztraining_facilityService.updateallfacilitybyzid(zid, zpowerstatus);
    }


}
