package com.itboyst.facedemo.controller;

import com.itboyst.facedemo.dto.Zteacher_command;
import com.itboyst.facedemo.dto.ztraining_room;
import com.itboyst.facedemo.service.Zteacher_commandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ZcommandController {

    @Autowired
    Zteacher_commandService zteacher_commandService;

    @RequestMapping("/findcommand")
    @ResponseBody
    public List<Zteacher_command> findcommand(HttpSession session){

        ztraining_room ztraining_room=(ztraining_room)session.getAttribute("ztraining_room");
        String zid=ztraining_room.getZid();

        List<Zteacher_command> data=zteacher_commandService.selectcommand(zid);

        return data;
    }
}
