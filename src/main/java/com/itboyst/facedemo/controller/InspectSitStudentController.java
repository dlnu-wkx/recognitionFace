package com.itboyst.facedemo.controller;

import com.itboyst.facedemo.dto.*;
import com.itboyst.facedemo.service.ZstrangeService;
import com.itboyst.facedemo.service.ZsysconfigService;
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
import java.util.*;

@Controller
public class InspectSitStudentController {
    public final static Logger logger = LoggerFactory.getLogger(InspectSitStudentController.class);
    @Autowired
    InspectSitStudentService inspectSitStudentService;

    @Autowired
    ZsysconfigService zsysconfigService;

    @Autowired
    ZstrangeService zstrangeService;

    @RequestMapping(value = "/InspectSitStudent", method = RequestMethod.POST)
    @ResponseBody
    public List<InspectSitStudent> InspectSitStudent(HttpSession session,String mytime,String zcheck) throws IOException, ParseException {

        Zteacher_cookie zteacher_cookie =(Zteacher_cookie) session.getAttribute("zteacher_cookie");
        String ztrainingroomID ="";
        if(zteacher_cookie !=null){
            ztrainingroomID = zteacher_cookie.getZtrainingroomid();
        }
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        if(mytime!=null){
            Long time =Long.parseLong(mytime);
            timestamp.setTime(time);
        }

        if(zcheck.equals("人脸识别")) {
            Zsysconfig zsysconfig = zsysconfigService.findIPByZname("杰视服务器IP地址");
            String jieshiip = zsysconfig.getZvalue();
            Renlian renlian = new Renlian();
            renlian.setJieshiip(jieshiip);
            renlian.setZtrainingroomID(ztrainingroomID);
            renlian.setUrl("ws://" + jieshiip + ":8080/webapi/websocket");
            System.err.println("ws://" + jieshiip + ":8080/webapi/websocket");
            renlian.renlianwinCreate();
            try {
                renlian.startBtnClick();
            } catch (RuntimeException e) {
                System.out.println("远程没有连接上");
            } finally {
                renlian.stopBtnClick();

            }

            List<InspectSitStudent>  zstudentList = new ArrayList<>();
            zstudentList =inspectSitStudentService.findStudentByDateAndTrainingId(ztrainingroomID,timestamp,zcheck);
            //教师的信息
            List<InspectSitTeacher> zteacherList =inspectSitStudentService.findTeacherByDateAndTrainingIDdistinct(jieshiip,timestamp,zcheck);
            if(null!=zteacherList){

                for(InspectSitTeacher a:zteacherList){
                    InspectSitStudent inspectSitStudent =new InspectSitStudent();
                    inspectSitStudent.setZgradeName("教师");
                    inspectSitStudent.setZName(a.getZName());
                    inspectSitStudent.setZstudentID(a.getZteacherID());
                    inspectSitStudent.setZrecognizetime(a.getZrecognizetime());
                    inspectSitStudent.setOriginalPictureUrl(a.getOriginalPictureUrl());
                    //把老师的信息变形添加到学生数组中
                    zstudentList.add(inspectSitStudent);
                }
            }

            if(zstudentList.size()>1){
                Collections.sort(zstudentList, new Comparator<InspectSitStudent>() {
                    @Override
                    public int compare(InspectSitStudent o1, InspectSitStudent o2) {
                        long aim =o1.getZrecognizetime().getTime()-o2.getZrecognizetime().getTime();
                        if(aim<0){
                            return 1;
                        }else if(aim>0){
                            return -1;
                        }
                        return 0;
                    }
                });
            }

            return zstudentList;
        }



        List<InspectSitStudent>  zstudentList = new ArrayList<>();
        zstudentList =inspectSitStudentService.findStudentByDateAndTrainingId(ztrainingroomID,timestamp,zcheck);



        return zstudentList;
    }


    @RequestMapping(value = "/InspectSitStudentandTeacher", method = RequestMethod.POST)
    @ResponseBody
    public List<InspectSitStudent> InspectSitStudentandTeacher(HttpSession session,String mytime,String zcheck) throws IOException, ParseException {

        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        if(mytime!=null){
            Long time =Long.parseLong(mytime);
            timestamp.setTime(time);
        }
        List<InspectSitStudent>  zstudentList = new ArrayList<>();
        Zteacher_cookie zteacher_cookie =(Zteacher_cookie) session.getAttribute("zteacher_cookie");
        String ztrainingroomID=zteacher_cookie.getZtrainingroomid();
        zstudentList =inspectSitStudentService.findStudentByDateAndTrainingIdASC(ztrainingroomID,timestamp,zcheck);
        //查找摄像头识别成功的教师
        Zsysconfig zsysconfig =zsysconfigService.findIPByZname("杰视服务器IP地址");
        String jieshiip =zsysconfig.getZvalue();
        List<InspectSitTeacher> zteacherList =inspectSitStudentService.findTeacherByDateAndTrainingIdASC(jieshiip,timestamp,zcheck);
        if(null!=zteacherList){

            for(InspectSitTeacher a:zteacherList){
                InspectSitStudent inspectSitStudent =new InspectSitStudent();
                inspectSitStudent.setZgradeName("教师");
                inspectSitStudent.setZName(a.getZName());
                inspectSitStudent.setZstudentID(a.getZteacherID());
                inspectSitStudent.setZrecognizetime(a.getZrecognizetime());
                inspectSitStudent.setOriginalPictureUrl(a.getOriginalPictureUrl());
                //把老师的信息变形添加到学生数组中
                zstudentList.add(inspectSitStudent);
            }
        }
        //把陌生人的信息也添加进来
        List<Zstrange> zstrangeslist = zstrangeService.findAll(jieshiip,timestamp,zcheck);
        if(null!=zstrangeslist){
            for(Zstrange a:zstrangeslist){
                InspectSitStudent inspectSitStudent =new InspectSitStudent();
                inspectSitStudent.setZgradeName("陌生人");
                inspectSitStudent.setZName(a.getZname());
                inspectSitStudent.setZrecognizetime(a.getZrecognizetime());
                inspectSitStudent.setOriginalPictureUrl(a.getOriginalPictureUrl());
                zstudentList.add(inspectSitStudent);
            }
        }
                //最后把学生的信息和变形后的教师信息一起排序
        if(zstudentList.size()>1){
            Collections.sort(zstudentList, new Comparator<InspectSitStudent>() {
                @Override
                public int compare(InspectSitStudent o1, InspectSitStudent o2) {
                    long aim =o1.getZrecognizetime().getTime()-o2.getZrecognizetime().getTime();
                    if(aim<0){
                        return 1;
                    }else if(aim>0){
                        return -1;
                    }
                    return 0;
                }
            });
        }

        for(InspectSitStudent a:zstudentList){
            System.err.println("识别出来的人"+a);
        }

        return zstudentList;
    }



}
