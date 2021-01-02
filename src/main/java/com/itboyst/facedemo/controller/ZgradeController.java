package com.itboyst.facedemo.controller;

import com.itboyst.facedemo.dto.Zgrade;
import com.itboyst.facedemo.service.ZgradeService;
import com.itboyst.facedemo.service.ZstudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ZgradeController {
    @Autowired
    ZgradeService zgradeService;

    @Autowired
    ZstudentService zstudentService;

    @RequestMapping("/findallgrade")
    @ResponseBody
    public List<Zgrade> findallgrade(){

        return zgradeService.findallgrade();
    }


    @RequestMapping("/findgradeandnamelike")
    @ResponseBody
    public List<String> findgradeandnamelike(String zname){
        List<String> listone=zgradeService.findgradebynamelike(zname);

        List<String> listtwo=zstudentService.findastudentbynamelike(zname);

        listone.addAll(listtwo);

        //System.out.println(listone);

        return listone;

    }

}
