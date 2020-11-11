package com.itboyst.facedemo.controller;

import com.itboyst.facedemo.dto.Ztraining_task_content;
import com.itboyst.facedemo.service.Ztraining_task_contentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


@Controller
public class Ztraining_task_contentController {

    @Autowired
    Ztraining_task_contentService ztraining_task_contentService;

    @RequestMapping("/selectcontentbypage")
    @ResponseBody
    public Ztraining_task_content selectcontentbypage(int page, String taskid){

        return ztraining_task_contentService.selectcontbypage(page,taskid);

    }

    @RequestMapping("/findendpages")
    @ResponseBody
    public int findendpages(String taskid){

        return ztraining_task_contentService.findendpages(taskid);
    }


}
