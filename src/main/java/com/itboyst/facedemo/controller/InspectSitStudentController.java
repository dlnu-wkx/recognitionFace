package com.itboyst.facedemo.controller;

import com.itboyst.facedemo.dto.InspectSitStudent;
import com.itboyst.facedemo.dto.Zstudent;
import com.itboyst.facedemo.dto.Zstudent_login;
import com.itboyst.facedemo.dto.Zteacher_cookie;
import com.itboyst.facedemo.service.impl.InspectSitStudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.IOException;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
public class InspectSitStudentController {
    public final static Logger logger = LoggerFactory.getLogger(InspectSitStudentController.class);
    @Autowired
    InspectSitStudentService inspectSitStudentService;

    @RequestMapping(value = "/InspectSitStudent", method = RequestMethod.POST)
    @ResponseBody
    public List<InspectSitStudent> InspectSitStudent(HttpSession session) throws IOException, ParseException {

        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        List<InspectSitStudent>  zstudentList = new ArrayList<>();
        Zteacher_cookie zteacher_cookie =(Zteacher_cookie) session.getAttribute("zteacher_cookie");
        String ztrainingroomID=zteacher_cookie.getZtrainingroomid();
        zstudentList =inspectSitStudentService.findStudentByDateAndTrainingId(ztrainingroomID,timestamp);
        System.err.println(zstudentList.get(0).getZstudentName());
        System.err.println(zstudentList.get(0).getZstudentID());
        return zstudentList;
    }
}
