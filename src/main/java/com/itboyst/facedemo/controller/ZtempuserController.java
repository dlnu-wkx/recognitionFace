package com.itboyst.facedemo.controller;

import com.itboyst.facedemo.dto.Zstudent;
import com.itboyst.facedemo.dto.Zstudent_login;
import com.itboyst.facedemo.dto.Zteacher;
import com.itboyst.facedemo.dto.Ztempuser;
import com.itboyst.facedemo.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class ZtempuserController {
    public final static Logger logger = LoggerFactory.getLogger(ZtempuserController.class);

    @Autowired
    ZtempuserService ztempuserService;

    @Autowired
    UserFaceInfoService userFaceInfoService;

    @Autowired
    ZteacherService zteacherService;

    @Autowired
    ZstudentService zstudentService;

    @Autowired
    ZmajorService zmajorService;

    @Autowired
    ZgradeService zgradeService;

    @RequestMapping(value = "/findAllztempuser", method = RequestMethod.POST)
    @ResponseBody
    public List<Ztempuser> findAllztempuser(HttpSession session) throws IOException {
        List<Ztempuser>  ztempuserList = ztempuserService.findAlltempuserbytime();
        return ztempuserList;
    }


    @RequestMapping(value = "/addtempuser", method = RequestMethod.POST)
    @ResponseBody
    public int  addtempuser(String zid,String authorityID,String zname) throws IOException {

        Ztempuser ztempuser = ztempuserService.findByzid(zid);

        String faceid=userFaceInfoService.selectfaceidbyfpath(ztempuser.getOriginalPictureUrl());
        int faceinfoid = userFaceInfoService.findidbyfaceid(faceid);
        //当有权限信息是存储教师的相关信息
        if(""!=authorityID){
            Zteacher zteacher = new Zteacher();
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            zteacher.setZid(uuid);
            //设置临时的专业
            String majorzid = zmajorService.findzidbyzname("临时专业");
            zteacher.setZmajorID(majorzid);
            zteacher.setZidentity(faceid);
            zteacher.setZauthorityID(authorityID);
            zteacher.setZpass("666");
            zteacher.setZname(zname);
            zteacher.setZphone("13888888888");
            zteacher.setZsex("男");
            zteacher.setZphoto(ztempuser.getOriginalPictureUrl());
            zteacher.setZfaceinfoID(faceinfoid);
            Zteacher  old = zteacherService.findteacherByzidentity(faceid);
            //当表中已经存在该老师的信息时则无法添加
            if(null == old){
                int b =zteacherService.addtempteacher(zteacher);
                //更新临时人员信息
                int a =ztempuserService.update(zid,zname);
                return b;
            }

        }else{//没有权限的就为学生,分两种情况讨论一种是已经注册但是没课的
            Zstudent oldstu= zstudentService.findstudentByZidentity(faceid);
            if(null == oldstu){//如果不存在则新建一条学生记录
                Zstudent zstudent = new Zstudent();
                String stuuuid = UUID.randomUUID().toString().replaceAll("-","");
                zstudent.setZid(stuuuid);
                String gradezid = zgradeService.findzidbyzname("临时班级");
                zstudent.setZgradeID(gradezid);
                zstudent.setZidentity(faceid);
                zstudent.setZpass("888");
                zstudent.setZname(zname);
                zstudent.setZphone("12345678911");
                zstudent.setZsex("男");
                zstudent.setZfaceinfoID(faceinfoid);
                zstudent.setZphoto(ztempuser.getOriginalPictureUrl());
                zstudent.setZstatus("审核通过");
               int c = zstudentService.registerstud(zstudent);
               //更新临时人员信息
                int a =ztempuserService.update(zid,zname);
               return c;
            }

        }
        return 0;
    }


}
