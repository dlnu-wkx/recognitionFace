package com.itboyst.facedemo.controller;

import com.itboyst.facedemo.dto.Zstudent;
import com.itboyst.facedemo.dto.Zteacher_temporary_task;
import com.itboyst.facedemo.service.Zteacher_temporary_taskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class TasktempController {

    @Autowired
    Zteacher_temporary_taskService zteacher_temporary_taskService;

    @RequestMapping("/findalltemporarytask")
    @ResponseBody
    public List<Zteacher_temporary_task> findalltemporarytask(HttpSession session){

        Zstudent zstudent=(Zstudent)session.getAttribute("zstudent");
        String zid=zstudent.getZid();

        return zteacher_temporary_taskService.findtaskname(zid);

    }

}
