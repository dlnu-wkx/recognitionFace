package com.itboyst.facedemo.controller;

import com.itboyst.facedemo.dto.Zgrade;
import com.itboyst.facedemo.service.ZgradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ZgradeController {
    @Autowired
    ZgradeService zgradeService;

    @RequestMapping("/findallgrade")
    @ResponseBody
    public List<Zgrade> findallgrade(){

        return zgradeService.findallgrade();
    }


}
