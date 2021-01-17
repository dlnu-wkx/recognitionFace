package com.itboyst.facedemo.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.RandomUtil;
import com.arcsoft.face.toolkit.ImageFactory;
import com.arcsoft.face.toolkit.ImageInfo;
import com.itboyst.facedemo.base.Iputil;
import com.itboyst.facedemo.dto.*;
import com.itboyst.facedemo.domain.UserFaceInfo;
import com.itboyst.facedemo.service.*;
import com.itboyst.facedemo.base.Result;
import com.itboyst.facedemo.base.Results;
import com.itboyst.facedemo.enums.ErrorCodeEnum;
import com.arcsoft.face.FaceInfo;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;


import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.*;
import java.net.InetAddress;
import java.sql.Timestamp;
import java.util.*;
import java.util.List;

import static com.itboyst.facedemo.base.UUIDutil.ReplaceSQLChar;


@Controller
public class FaceController {

    public final static Logger logger = LoggerFactory.getLogger(FaceController.class);


    @Autowired
    FaceEngineService faceEngineService;

    @Autowired
    UserFaceInfoService userFaceInfoService;

    @Autowired
    ZstudentService zstuservice;

    @Autowired
    Zstudent_loginService zstudent_loginService;

    @Autowired
    FaceEngineService faceengine;

    @Autowired
    Ztraining_facilityService ztrinfser;

    @Autowired
    Ztraining_roomService ztraining_roomService;

    @Autowired
    Zstudent_cooikeService zstudent_cooikeService;

    @Autowired
    Zstudent_journalService zstudentJournalService;

    @Autowired
    Zteacher_journalService zteacher_journalService;

    @Autowired
    ZteacherService zteacherService;

    @Autowired
    ZscheuleService zscheuleService;

    @Autowired
    Zteacher_cookieSerice zteacher_cookieSerice;

    @Autowired
    ZmanagerService zmanagerService;

    @Autowired
    Zstudent_loginService zstudentLoginService;
    /**
     * 跳转测试
     * @return
     */
    @RequestMapping(value = "/demo",method = RequestMethod.GET)
    public String demo() {
        System.out.println("你好");
        return "demo3";
    }



    /**老师进入的主页面跳转到右侧功能页面的控制器
     * 魏凯旋 2020-11-04
     * @return
     */
    @RequestMapping(value = "/field_management")
    public String field_management(){return "field_management";}


    @RequestMapping(value = "/findnamelike")
    @ResponseBody
    public List<String> findallface(String name){
        return userFaceInfoService.findcountnamelike(name);
    }




    /*
    人脸添加
     */
    @RequestMapping(value = "/faceAdd", method = RequestMethod.POST)
    @ResponseBody
    public Result<Object> faceAdd(String major,String classes,String userkind,String password,String zphone,String sex,@RequestParam("file") String file, @RequestParam("groupId") Integer groupId, @RequestParam("name") String name, @RequestParam("zidentity") String zidentity) {

       //字符替换
        major =ReplaceSQLChar(major);
        classes =ReplaceSQLChar(classes);
        userkind =ReplaceSQLChar(userkind);
        password =ReplaceSQLChar(password);
        zphone =ReplaceSQLChar(zphone);
        sex =ReplaceSQLChar(sex);
        name =ReplaceSQLChar(name);
        zidentity =ReplaceSQLChar(zidentity);


        try {
            if (file == null) {
                return Results.newFailedResult("file is null");
            }
            if (groupId == null) {
                return Results.newFailedResult("groupId is null");
            }
            if (name == null) {
                return Results.newFailedResult("name is null");
            }
            if ("".equals(file)) {
                return Results.newFailedResult("picture is null");
            }
            byte[] decode = Base64.decode(base64Process(file));
            //String path = pictureOutStream(decode);//保存图片到指定的位置
            //String path = base64StringToImage(decode);

            ImageInfo imageInfo = ImageFactory.getRGBData(decode);

            //人脸特征获取
            byte[] bytes = faceEngineService.extractFaceFeature(imageInfo);
            if (bytes == null) {
                return Results.newFailedResult(ErrorCodeEnum.NO_FACE_DETECTED);
            }
            //System.err.println(path);
            String path="";
            if(userkind.equals("学生")){
                path = "D:\\SchoolTrainFiles\\FacePic\\student\\" + System.currentTimeMillis() + ".jpg";
            }
            if(userkind.equals("教师")){
                path = "D:\\SchoolTrainFiles\\FacePic\\teacher\\" + System.currentTimeMillis() + ".jpg";
            }

            if ("".equals(path)) {
                return Results.newFailedResult("picture sava failure");
            }
            //System.out.println(path);

            GenerateImage(file,path);


            //初始化更新与添加数据的行数
            int namenum,i,j,k,q,w,v,z=0;


            UserFaceInfo userFaceInfo = new UserFaceInfo();
            userFaceInfo.setName(name);
            userFaceInfo.setGroupId(groupId);
            userFaceInfo.setFaceFeature(bytes);

            //用学号或工号与face表相连
            userFaceInfo.setFaceId(zidentity);
            userFaceInfo.setPath(path);

            int e=userFaceInfoService.findcountfaceid(zidentity);

            //System.out.println(e);
            if (e>0)
                userFaceInfoService.updateuserfopath(path,zidentity,name);
            else
                userFaceInfoService.insertSelective(userFaceInfo);

            String zid = UUID.randomUUID().toString().replaceAll("-","");
            int id=0;

            if(e>0)
                  id=  userFaceInfoService.findidbyfaceid(zidentity);
            else
                  id=  faceEngineService.selectidbyname(path);

            if(userkind.equals("学生")){
               // System.out.println(0);

                Zstudent zstu=new Zstudent();

                zstu.setZidentity(zidentity);
                zstu.setZname(name);
                zstu.setZphoto(path);
                zstu.setZpass(password);
                zstu.setZsex(sex);
                zstu.setZphone(zphone);
                zstu.setZgradeID(classes);
                zstu.setZfaceinfoID(id);
                zstu.setZstatus("待审核");
                namenum=zstuservice.findcountbyname(zidentity);

                //System.out.println(zstu);
                if (namenum !=0){
                    w=zstuservice.updatestudent(zstu);
                    //更新成功
                    if (w>0){
                        return Results.newSuccessResult("");
                    }else{
                        //更新学生失败
                        return Results.newFailedResult(ErrorCodeEnum.FAIL_UPDATE_STUDENT);
                    }
                }else {
                    zstu.setZid(zid);
                    i=zstuservice.registerstud(zstu);
                }

               // System.out.println(i);
                if (i==0){
                    return null;
                }
            }else if(userkind.equals("教师")){
                //System.out.println(0);
                Zteacher zteacher=new Zteacher();
                Zteacher zteacher2=new Zteacher();
                zteacher2 = zteacherService.selectteacherbyname(name);
                int a=0;
                if (zteacher2!=null) a=1;

                zteacher.setZfaceinfoID(id);
                zteacher.setZidentity(zidentity);
                zteacher.setZname(name);
                zteacher.setZpass(password);
                zteacher.setZphone(zphone);
                zteacher.setZphoto(path);
                zteacher.setZsex(sex);
                zteacher.setZmajorID(major);
                //System.out.println(zteacher);


                if (a !=0){
                    v=zteacherService.updateteacher(zteacher);
                    //更新成功
                    if (v>0){
                        return Results.newSuccessResult("");
                    }else{
                        //更新学生失败
                        return Results.newFailedResult(ErrorCodeEnum.FAIL_UPDATE_TEACHER);
                    }
                }else {
                    zteacher.setZid(zid);
                    j= zteacherService.registerteacher(zteacher);
                }

                if (j==0){
                    return null;
                }

            }else if(userkind.equals("管理员")){
                //System.out.println(0);
                Zmanager zmanager=new Zmanager();
                Zmanager zmanager2=new Zmanager();
                zmanager2=zmanagerService.findallbymanagername(name);

                int b=0;
                if (zmanager2!=null)b=1;

                zmanager.setZidentity(zidentity);
                zmanager.setZname(name);
                zmanager.setZpass(password);
                zmanager.setZphone(zphone);

                if (b !=0){
                    v=zmanagerService.updatemanager(zmanager);
                    //更新成功
                    if (v>0){
                        return Results.newSuccessResult("");
                    }else{
                        //更新学生失败
                        return Results.newFailedResult(ErrorCodeEnum.FAIL_UPDATE_MANAGER);
                    }
                }else {
                    zmanager.setZid(zid);
                    k=zmanagerService.insertmanager(zmanager);
                }

                if(k==0){
                    return null;
                }

            }

            logger.info("faceAdd:" + name);
            return Results.newSuccessResult("");
        } catch (Exception e) {
            logger.error("", e);
        }
        return Results.newFailedResult(ErrorCodeEnum.UNKNOWN);
    }

    /**
     * 这个保存图片有问题
     *
     * @param decode
     */
    private String pictureOutStream(byte[] decode) {

        File file = new File("F:\\recognitionFace\\src\\main\\resources\\static\\images\\" + System.currentTimeMillis() + ".jpg");//保存照片的地址
        FileOutputStream fileOutputStream = null;
        try {
            if (!file.exists()) {//如果没找到文件夹则创建一个新文件夹
                file.mkdirs();
                fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(decode);
            }
            return file.getPath();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.getStackTrace();
                }

            }

        }
        return "";
    }

    /**
     * 把图片存储到指定位置
     *
     * @param bytes1
     */
    private String base64StringToImage(byte[] bytes1) {

        try {
            String path = "";
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes1);
            BufferedImage bi1 = ImageIO.read(bais);
            File w2 = new File("F:\\recognitionFace\\src\\main\\resources\\static\\images\\" + System.currentTimeMillis() + ".jpg");//可以是jpg,png格式
            ImageIO.write(bi1, "jpg", w2);//不管输出什么格式图片，此处不需改动
            path = w2.getPath();
            return path;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     *对字节数组字符串进行Base64解码并生成图片
     *魏凯旋 2020-10-26
     * @param imgStr 图片的数据
     * @param
     * @return
     */
    private  boolean GenerateImage(String imgStr,String imgFilePath) {
        if (imgStr == null) // 图像数据为空
            return false;
        //去掉头部的data:image/png;base64,
        /*int start = imgStr.indexOf(',') + 1;
            imgStr=imgStr.substring(start);*/
        //BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] bytes = Base64.decode(base64Process(imgStr));
            //byte[] bytes = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {// 调整异常数据
                    bytes[i] += 256;
                }
            }
            // 生成jpg图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(bytes);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 人脸识别返回识别的结果
     * @param file
     * @param groupId
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/faceSearch", method = RequestMethod.POST)
    @ResponseBody
    public Result<FaceSearchResDto> faceSearch(HttpServletRequest request,String ztype,String ip, String file, Integer groupId, HttpServletResponse response, HttpSession session,Model model) throws Exception {

        System.out.println(ip+","+ztype+","+groupId);

        if (groupId == null) {
            return Results.newFailedResult("groupId is null");
        }
        byte[] decode = Base64.decode(base64Process(file));

        BufferedImage bufImage = ImageIO.read(new ByteArrayInputStream(decode));
        ImageInfo imageInfo = ImageFactory.bufferedImage2ImageInfo(bufImage);
        //人脸特征获取
        byte[] bytes = faceEngineService.extractFaceFeature(imageInfo);

        if (bytes == null) {
            return Results.newFailedResult(ErrorCodeEnum.NO_FACE_DETECTED);//没检测到人脸数据
        }
        //人脸比对，获取比对结果
        List<FaceUserInfo> userFaceInfoList = faceEngineService.compareFaceFeature(bytes, groupId);
        System.out.println("符合条件的人脸"+userFaceInfoList.size());
        if (CollectionUtil.isNotEmpty(userFaceInfoList)) {
            FaceUserInfo faceUserInfo = userFaceInfoList.get(0);
            //获取目标文件路径以备上传照片使用
            String fpath =userFaceInfoList.get(0).getPath();
            FaceSearchResDto faceSearchResDto = new FaceSearchResDto();
            BeanUtil.copyProperties(faceUserInfo, faceSearchResDto);
            List<ProcessInfo> processInfoList = faceEngineService.process(imageInfo);
            if (CollectionUtil.isNotEmpty(processInfoList)) {
                //人脸检测
                List<FaceInfo> faceInfoList = faceEngineService.detectFaces(imageInfo);
                int left = faceInfoList.get(0).getRect().getLeft();
                int top = faceInfoList.get(0).getRect().getTop();
                int width = faceInfoList.get(0).getRect().getRight() - left;
                int height = faceInfoList.get(0).getRect().getBottom() - top;

                Graphics2D graphics2D = bufImage.createGraphics();
                graphics2D.setColor(Color.RED);//红色
                BasicStroke stroke = new BasicStroke(5f);
                graphics2D.setStroke(stroke);
                graphics2D.drawRect(left, top, width, height);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ImageIO.write(bufImage, "jpg", outputStream);
                byte[] bytes1 = outputStream.toByteArray();
                faceSearchResDto.setImage("data:image/jpeg;base64," + Base64Utils.encodeToString(bytes1));
                faceSearchResDto.setAge(processInfoList.get(0).getAge());
                faceSearchResDto.setGender(processInfoList.get(0).getGender().equals(1) ? "女" : "男");

            }
            //System.out.println(faceUserInfo.getPath());
            //student表信息更改
            Zstudent zstudent=new Zstudent();

            //System.out.println(faceUserInfo.getPath());

            if (faceUserInfo.getPath()==null)
                return Results.newFailedResult(ErrorCodeEnum.NO_FACE_PATH);

            session.setAttribute("faceUserInfo",faceUserInfo);

            int faceid=faceengine.selectidbyname(faceUserInfo.getPath());
            //判定int类型为空,设备表出问题返回1

            //System.out.println(faceid);
            // System.out.println(faceid);
            zstudent=zstuservice.findadoptstudent(faceid);
            System.out.println(zstudent);

            if(zstudent==null) return Results.newFailedResult(ErrorCodeEnum.NO_STUDENT_FACEID);
            //学生登陆信息
            Zstudent_login zsl=new Zstudent_login();

            String uuid2 = UUID.randomUUID().toString().replaceAll("-","");
            zsl.setZid(uuid2);

            //学生信息存session
            session.setAttribute("zstudent",zstudent);

            zsl.setZstudentID(zstudent.getZid());
            Timestamp timestamp=new Timestamp(System.currentTimeMillis());
            zsl.setZrecongnizetime(timestamp);

            //首次电脑界面登陆设置类型为机床
            zsl.setZtype("机床");


            InetAddress addr = InetAddress.getLocalHost();

            String ip4=Iputil.getClientIpAddress(request);

            zsl.setZcheck("登陆");
            System.out.println("ip4"+ip4);

            //System.out.println("ip2"+ip2);
            zsl.setZcheck("实操");
            zsl.setZrecognizeIP(ip4);


            Ztraining_facility ztrfac = ztrinfser.findbyip(ip4);
            if (ztrfac == null) return Results.newFailedResult(ErrorCodeEnum.NO_FACILITY_STUDENTPCIP);

            session.setAttribute("ztraining_facility",ztrfac);

            if(ztrfac.getZtrainingroomID().isEmpty()) return Results.newFailedResult(ErrorCodeEnum.NO_FACILITY_TRAINROOMID);
            //实训室
            ztraining_room ztr =ztraining_roomService.findbyip(ztrfac.getZtrainingroomID());
            session.setAttribute("ztraining_room",ztr);

            //System.out.println(ztr);

            //课程，日期，上课学生表
            Zstudent_cookie zsc=zstudent_cooikeService.findscookiemes(ztr.getZid(),timestamp,zstudent.getZid());
            System.out.println(zsc);

            zsl.setZscheduleID(zsc.getZscheduleID());
            zsl.setZstatus("正常");

            //插入学生登陆信息
            int  i=zstudent_loginService.insertnowmessage(zsl);

            if (zsc==null)return Results.newFailedResult(ErrorCodeEnum.NO_STUDENTSCHDULE_MESSAGE);

            session.setAttribute("zstudent_cookie",zsc);
           // System.out.println(session.getAttribute("zstudent_cookie"));

            Cookie zselecttest = new Cookie("zselecttest",zsc.getZselecttest());//设置路径在cookie中的值

            zselecttest.setMaxAge(86400);
            //存cookie
            response.addCookie(zselecttest);


            //插入日志信息
            Zstudent_journal zstudentJournal=new Zstudent_journal();
            zstudentJournal.setZstudentID(zstudent.getZid());
            zstudentJournal.setZtype(ztype);
            zstudentJournal.setZoperatedate(timestamp);
            String uuid3 = UUID.randomUUID().toString().replaceAll("-","");
            zstudentJournal.setZid(uuid3);

            int j=zstudentJournalService.insertstujournal(zstudentJournal);



            if(zstudent !=null  && i!=0){
                //将验证信息保存到Cookie
                Cookie name=new Cookie("name",faceSearchResDto.getName());
                Cookie faceId=new Cookie("faceId",faceSearchResDto.getFaceId());
                //替换“\”为“/”否则存不到Cookie中
                String path1 =fpath.replace("\\","/");
                String path =path1.substring(36);
                //输出看是否有空格
                Cookie aimPath1 = new Cookie("path",path);//设置路径在cookie中的值
                name.setMaxAge(86400);
                faceId.setMaxAge(86400);
                aimPath1.setMaxAge(86400);
                response.addCookie(name);
                response.addCookie(faceId);
                response.addCookie(aimPath1);//把路径存到cookie中
                return Results.newSuccessResult(faceSearchResDto);
            }


        }
        return Results.newFailedResult(ErrorCodeEnum.FACE_DOES_NOT_MATCH);
    }

    /**
     * 查岗调用人脸识别
     * 魏凯旋 2020-11-23
     * @param file
     * @param groupId
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/faceFind", method = RequestMethod.POST)
    @ResponseBody
    public Result<FaceSearchResDto> faceFind( String file, Integer groupId, HttpSession session,HttpServletRequest request) throws Exception {
        if (groupId == null) {
            return Results.newFailedResult("groupId is null");
        }
        byte[] decode = Base64.decode(base64Process(file));

        BufferedImage bufImage = ImageIO.read(new ByteArrayInputStream(decode));
        ImageInfo imageInfo = ImageFactory.bufferedImage2ImageInfo(bufImage);
        //人脸特征获取
        byte[] bytes = faceEngineService.extractFaceFeature(imageInfo);
        if (bytes == null) {
            return Results.newFailedResult(ErrorCodeEnum.NO_FACE_DETECTED);//没检测到人脸数据
        }
        //人脸比对，获取比对结果
        List<FaceUserInfo> userFaceInfoList = faceEngineService.compareFaceFeature(bytes, groupId);
        if (CollectionUtil.isNotEmpty(userFaceInfoList)) {
            FaceUserInfo faceUserInfo = userFaceInfoList.get(0);
            //获取目标文件路径以备上传照片使用
            String fpath =userFaceInfoList.get(0).getPath();
            FaceSearchResDto faceSearchResDto = new FaceSearchResDto();
            BeanUtil.copyProperties(faceUserInfo, faceSearchResDto);
            List<ProcessInfo> processInfoList = faceEngineService.process(imageInfo);
            if (CollectionUtil.isNotEmpty(processInfoList)) {
                //人脸检测
                List<FaceInfo> faceInfoList = faceEngineService.detectFaces(imageInfo);
                int left = faceInfoList.get(0).getRect().getLeft();
                int top = faceInfoList.get(0).getRect().getTop();
                int width = faceInfoList.get(0).getRect().getRight() - left;
                int height = faceInfoList.get(0).getRect().getBottom() - top;

                Graphics2D graphics2D = bufImage.createGraphics();
                graphics2D.setColor(Color.RED);//红色
                BasicStroke stroke = new BasicStroke(5f);
                graphics2D.setStroke(stroke);
                graphics2D.drawRect(left, top, width, height);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ImageIO.write(bufImage, "jpg", outputStream);
                byte[] bytes1 = outputStream.toByteArray();
                faceSearchResDto.setImage("data:image/jpeg;base64," + Base64Utils.encodeToString(bytes1));
                faceSearchResDto.setAge(processInfoList.get(0).getAge());
                faceSearchResDto.setGender(processInfoList.get(0).getGender().equals(1) ? "女" : "男");

            }
            //student表信息更改
            Zstudent zstudent=new Zstudent();
            int faceid=faceengine.selectidbyname(faceUserInfo.getPath());
            zstudent=zstuservice.findadoptstudent(faceid);
            Zstudent presentZstudent=(Zstudent)session.getAttribute("zstudent");
            if(presentZstudent.getZidentity().equals(zstudent.getZidentity())){
                Zstudent_login zsl = new Zstudent_login();
                String uuid2 = UUID.randomUUID().toString().replaceAll("-","");
                zsl.setZid(uuid2);
                zsl.setZstudentID(presentZstudent.getZid());
                Timestamp timestamp=new Timestamp(System.currentTimeMillis());
                zsl.setZrecongnizetime(timestamp);
                String ip4=Iputil.getClientIpAddress(request);
                zsl.setZtype("机床");
                List<String> list =zstudentLoginService.findScheduleBytimeandzstudentID(presentZstudent.getZid(),timestamp);

                if(!list.isEmpty()){
                    zsl.setZscheduleID(list.get(0));
                }
                System.out.println(ip4);
                //System.out.println(ip2);
                zsl.setZcheck("查岗");
                zsl.setZrecognizeIP(ip4);
                String studentfpath =faceEngineService.findfopathByfaceid(zstudent.getZfaceinfoID());
                zsl.setOriginalPictureUrl(studentfpath);
                //插入学生登陆信息
                int  i=zstudent_loginService.updateloginmessage(zsl);

                return Results.newSuccessResult(faceSearchResDto);
            }
            System.out.println("当前登录人员和查岗人员不匹配");
            return Results.newFailedResult(ErrorCodeEnum.FACE_DOES_NOT_MATCH);

        }
        return Results.newFailedResult(ErrorCodeEnum.FACE_DOES_NOT_MATCH);
    }


   //教师登陆
    @RequestMapping(value = "/faceTeacherSearch", method = RequestMethod.POST)
    @ResponseBody
    public Result<FaceSearchResDto> faceTeacherSearch(String ip, String file, Integer groupId, HttpServletResponse response, HttpSession session) throws Exception {
        if (groupId == null) {
            return Results.newFailedResult("groupId is null");
        }
        byte[] decode = Base64.decode(base64Process(file));

        BufferedImage bufImage = ImageIO.read(new ByteArrayInputStream(decode));
        ImageInfo imageInfo = ImageFactory.bufferedImage2ImageInfo(bufImage);
        //人脸特征获取
        byte[] bytes = faceEngineService.extractFaceFeature(imageInfo);
        if (bytes == null) {
            return Results.newFailedResult(ErrorCodeEnum.NO_FACE_DETECTED);//没检测到人脸数据
        }
        //人脸比对，获取比对结果
        List<FaceUserInfo> userFaceInfoList = faceEngineService.compareFaceFeature(bytes, groupId);
        if (CollectionUtil.isNotEmpty(userFaceInfoList)) {
            FaceUserInfo faceUserInfo = userFaceInfoList.get(0);
            //获取目标文件路径以备上传照片使用
            String fpath =userFaceInfoList.get(0).getPath();
            FaceSearchResDto faceSearchResDto = new FaceSearchResDto();
            BeanUtil.copyProperties(faceUserInfo, faceSearchResDto);
            List<ProcessInfo> processInfoList = faceEngineService.process(imageInfo);
            if (CollectionUtil.isNotEmpty(processInfoList)) {
                //人脸检测
                List<FaceInfo> faceInfoList = faceEngineService.detectFaces(imageInfo);
                int left = faceInfoList.get(0).getRect().getLeft();
                int top = faceInfoList.get(0).getRect().getTop();
                int width = faceInfoList.get(0).getRect().getRight() - left;
                int height = faceInfoList.get(0).getRect().getBottom() - top;

                Graphics2D graphics2D = bufImage.createGraphics();
                graphics2D.setColor(Color.RED);//红色
                BasicStroke stroke = new BasicStroke(5f);
                graphics2D.setStroke(stroke);
                graphics2D.drawRect(left, top, width, height);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ImageIO.write(bufImage, "jpg", outputStream);
                byte[] bytes1 = outputStream.toByteArray();
                faceSearchResDto.setImage("data:image/jpeg;base64," + Base64Utils.encodeToString(bytes1));
                faceSearchResDto.setAge(processInfoList.get(0).getAge());
                faceSearchResDto.setGender(processInfoList.get(0).getGender().equals(1) ? "女" : "男");

            }


                //人脸表id
                int faceid=faceengine.selectidbyname(faceUserInfo.getPath());
                //教师信息、课程信息和实训室信息
                Zteacher_cookie zteacher_cookie=zteacher_cookieSerice.findbyfaceid(faceid);
                if(null==zteacher_cookie){
                    return Results.newFailedResult(ErrorCodeEnum.NO_IS_TEACHER);
                }
                session.setAttribute("zteacher_cookie",zteacher_cookie);
                System.out.println(session.getAttribute("zteacher_cookie"));

                //插入教师日志
                Zteacher_journal zteacher_journal=new Zteacher_journal();
                String uuid = UUID.randomUUID().toString().replaceAll("-","");
                zteacher_journal.setZid(uuid);
                zteacher_journal.setZtype("登陆系统");
                Timestamp timestamp=new Timestamp(System.currentTimeMillis());
                zteacher_journal.setZoperatedate(timestamp);
                int i= zteacher_journalService.inserteacherjournal(zteacher_journal,faceSearchResDto.getName());



                //将验证信息保存到Cookie
                Cookie name=new Cookie("name",faceSearchResDto.getName());
                Cookie faceId=new Cookie("faceId",faceSearchResDto.getFaceId());
                //替换“\”为“/”否则存不到Cookie中
                String path1 =fpath.replace("\\","/");
                String path =path1.substring(36);

                //输出看是否有空格
                Cookie aimPath1 = new Cookie("path",path);//设置路径在cookie中的值
                name.setMaxAge(86400);
                faceId.setMaxAge(86400);
                aimPath1.setMaxAge(86400);
                response.addCookie(name);
                response.addCookie(faceId);
                response.addCookie(aimPath1);//把路径存到cookie中
                return Results.newSuccessResult(faceSearchResDto);

        }
        return Results.newFailedResult(ErrorCodeEnum.FACE_DOES_NOT_MATCH);
    }


    @RequestMapping(value = "/teacherlogin", method = RequestMethod.GET)
    public String teacherlogin(@CookieValue("name") String name, @CookieValue("faceId") String faceId,@CookieValue("path") String path, Model model) throws Exception {
        model.addAttribute("faceId",faceId);
        model.addAttribute("name",name);
        String indPath  =path.replace("F:/recognitionFace/src/main/resources/static/","");
        model.addAttribute("path",indPath);
        System.out.println("inPath"+indPath);
        return "teacherEnter";
    }



    /**
     * 人脸检验成功后跳转后的控制器
     * 魏凯旋  2020-10-23
     * @param name
     * @param faceId
     * @param model
     * @param path 存储的图片路径
     * @return
     * @throws Exception
     */

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String register(@CookieValue("name") String name, @CookieValue("faceId") String faceId,@CookieValue("path") String path, Model model) throws Exception {
        model.addAttribute("faceId",faceId);
        model.addAttribute("name",name);
        String indPath  =path.replace("F:/recognitionFace/src/main/resources/static/","");
        model.addAttribute("path",indPath);

        return "studentEnter";
    }

    /**
     *如果老师的人脸检测成功则可以进入操作页面
     * 魏凯旋 2020-10-31
     * @param name
     * @param faceId
     * @param path
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/teachRegister", method = RequestMethod.GET)
    public String teachRegister(@CookieValue("name") String name, @CookieValue("faceId") String faceId,@CookieValue("path") String path, Model model) throws Exception {
        model.addAttribute("faceId",faceId);
        model.addAttribute("name",name);
        String indPath  =path.replace("F:/recognitionFace/src/main/resources/static/","");
        model.addAttribute("path",indPath);
        return "fieldManagement";
    }

    @RequestMapping(value = "/detectFaces", method = RequestMethod.POST)
    @ResponseBody
    public List<FaceInfo> detectFaces(String image) throws IOException {
        byte[] decode = Base64.decode(image);
        InputStream inputStream = new ByteArrayInputStream(decode);
        ImageInfo imageInfo = ImageFactory.getRGBData(inputStream);

        if (inputStream != null) {
            inputStream.close();
        }
        List<FaceInfo> faceInfoList = faceEngineService.detectFaces(imageInfo);

        return faceInfoList;
    }


    private String base64Process(String base64Str) {
        if (!StringUtils.isEmpty(base64Str)) {
            String photoBase64 = base64Str.substring(0, 30).toLowerCase();
            int indexOf = photoBase64.indexOf("base64,");
            if (indexOf > 0) {
                base64Str = base64Str.substring(indexOf + 7);
            }

            return base64Str;
        } else {
            return "";
        }
    }
}
