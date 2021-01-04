package com.itboyst.facedemo.controller;
import com.alibaba.fastjson.JSONObject;
import com.itboyst.facedemo.base.SpringUtil;
import com.itboyst.facedemo.dto.*;
import com.itboyst.facedemo.service.*;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.enums.ReadyState;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.ApplicationContext;
import org.zkoss.zk.ui.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Window;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.util.UUID;



public class Renlian  {
    public final static Logger logger = LoggerFactory.getLogger(Renlian.class);
    private static final long serialVersionUID = 1L;
    //普通的类实力化springboot管理的类
    ApplicationContext appCtx = SpringUtil.getApplicationContext();
    Zstudent_loginService zstudentLoginService = appCtx.getBean(Zstudent_loginService.class);
    ZstudentService zstudentService  = appCtx.getBean(ZstudentService.class);
    Zteacher_loginService zteacher_loginService = appCtx.getBean(Zteacher_loginService.class);
    ZteacherService zteacherService = appCtx.getBean(ZteacherService.class);
    Ztraining_cameraService ztraining_cameraService = appCtx.getBean(Ztraining_cameraService.class);

    // 自定义局部变量
    //private static final String jieshiip = "192.168.1.156"; // 杰视设备的IP地址
    //private static final String url = "ws://" + jieshiip + ":8080/webapi/websocket";
    //只能自定义接口类

   /* private static Zstudent_loginService zstudentLoginService;
    private static ZstudentService zstudentService;*/

   /* private static Zteacher_loginService zteacher_loginService;
    private static ZteacherService zteacherService;*/

    private String jieshiip;
    private String url ;
    private String ztrainingroomID;

    public String getZtrainingroomID() {
        return ztrainingroomID;
    }

    public void setZtrainingroomID(String ztrainingroomID) {
        this.ztrainingroomID = ztrainingroomID;
    }

    public void setJieshiip(String jieshiip) {
        this.jieshiip = jieshiip;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getJieshiip() {
        return jieshiip;
    }

    public String getUrl() {
        return url;
    }

    // 界面变量
    @Wire
    private Window renlianwin;
    @Wire
    private Button startBtn;
    @Wire
    private Button stopBtn;

    WebSocketClient myClient = null;

   /* @Autowired
   public void setZstudentLoginService (Zstudent_loginService zstudentLoginService){
        Renlian.zstudentLoginService=zstudentLoginService;
    }

   @Autowired
    public void setZstudentService (ZstudentService zstudentService){
        Renlian.zstudentService=zstudentService;
    }*/


   /* @Autowired
    public void setZteacherService (ZteacherService  zteacherService){
        Renlian.zteacherService=zteacherService;
    }

    public void setZteacher_loginService(Zteacher_loginService zteacher_loginService){
        Renlian.zteacher_loginService=zteacher_loginService;
    }*/

    @Listen("onCreate = #renlianwin")
    public void renlianwinCreate() {
        try {

            myClient = new WebSocketClient(new URI(url)) {

                @Override
                public void onClose(int arg0, String arg1, boolean arg2) {
                    System.out.println("连接关闭");

                }

                @Override
                public void onError(Exception arg0) {
                    System.out.println("发生错误");

                }

                @Override
                public synchronized void onMessage(String arg0) {
                    System.out.println("收到消息:" + arg0);
                    JSONObject dongtai = JSONObject.parseObject(arg0);
                    JSONObject dongtaipayload = dongtai.getJSONObject("payload");
                    String xuehao = dongtaipayload.getString("personIdCard");
                    String cameraName =dongtaipayload.getString("cameraName");

                    System.out.println("识别:" + xuehao + " , " + dongtaipayload.getString("personName"));

                    if ((xuehao != null) && (xuehao.length() > 0)) {

                        //int i =zstudentLoginService.deleteStudentLoginByzidentity(xuehao);

                        Zstudent_login zsl=new Zstudent_login();
                        String uuid2 = UUID.randomUUID().toString().replaceAll("-","");
                        zsl.setZid(uuid2);
                        zsl.setZrecognizeIP(jieshiip);
                        zsl.setZcheck("人脸识别");
                        Zstudent abc =zstudentService.findstudentByZidentity(xuehao);
                        //**这个地方的判空处理有问题
                        Zteacher zteacher =zteacherService.findteacherByzidentity(xuehao);

                        if(null!=abc){
                            zsl.setZstudentID(abc.getZid());
                            zsl.setZtype("入口签到");
                            Timestamp timestamp=new Timestamp(System.currentTimeMillis());
                            zsl.setZrecongnizetime(timestamp);
                            int a =zstudentLoginService.updateloginmessage(zsl);
                        }
                        //把识别的老师信息加入到登录日志中
                        if(null!=zteacher){
                            Zteacher_login zteacher_login = new Zteacher_login();
                            String uuid = UUID.randomUUID().toString().replaceAll("-","");
                            zteacher_login.setZid(uuid);
                            zteacher_login.setZteacherID(zteacher.getZid());
                            Timestamp timestamp=new Timestamp(System.currentTimeMillis());
                            zteacher_login.setZrecognizetime(timestamp);
                            zteacher_login.setZtype("入口签到");
                            zteacher_login.setZcheck("人脸识别");
                            zteacher_login.setZrecognizeIP(jieshiip);

                           int b = zteacher_loginService.delAndinsertteacher(zteacher_login);
                        }
                        if(cameraName != null){
                            Ztraining_camera ztraining_camera = new Ztraining_camera();
                            String uuid3 = UUID.randomUUID().toString().replaceAll("-","");
                            ztraining_camera.setZid(uuid3);
                            ztraining_camera.setZtrainingroomID(ztrainingroomID);
                            ztraining_camera.setZtitle("签到");
                            ztraining_camera.setZcameraIP(jieshiip);
                            ztraining_camera.setZcameraName(cameraName);
                            /*int zidentity = Integer.parseInt(cameraName);*/
                            ztraining_camera.setZidentity(1);
                            ztraining_camera.setZstatus("可用");

                           int c= ztraining_cameraService.insertCamera(ztraining_camera);
                        }


                       /* String sqlstr = "";
                        // 查DB
                        sqlstr = "DELETE FROM zstudent_login WHERE zstudentID=(SELECT zid FROM zstudent WHERE zidentity LIKE '"
                                + xuehao + "')";
                        //ORMOperate.ExecSQL(sqlstr, null, null);
                        sqlstr = "INSERT INTO zstudent_login(zid,zstudentID,zrecognizetime,ztype,zrecognizeIP,zstatus) VALUES('"
                                //+ UUID.randomUUID().toString().replaceAll("-", "")
                                + "',(SELECT zid FROM zstudent WHERE zidentity='" + xuehao + "'),NOW(),'表camera','"
                                + jieshiip + "','正常');";
                       // ORMOperate.ExecSQL(sqlstr, null, null);*/


                    }
                }

                @Override
                public void onOpen(ServerHandshake arg0) {
                    System.out.println("握手成功");

                }
            };
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Listen("onClick = #startBtn")
    public void startBtnClick() {
        if (myClient != null) {
            myClient.connect();
            // 判断是否连接成功，未成功后面发送消息时会报错
            if (!myClient.getReadyState().equals(ReadyState.OPEN)) {
                System.out.println("连接中···请稍后");
              /*  try {
                   // Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }
            myClient.send("MyClient");
            System.out.println("发送成功");
        }
    }

    @Listen("onClick = #stopBtn")
    public void stopBtnClick() {
        System.out.println("进入到stopBtnClick()");
        if (myClient != null) {
            System.out.println("关闭连接");
            myClient.close();
        }
    }

}
