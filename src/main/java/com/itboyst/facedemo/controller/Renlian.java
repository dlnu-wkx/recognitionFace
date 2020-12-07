package com.itboyst.facedemo.controller;
import com.alibaba.fastjson.JSONObject;
import com.itboyst.facedemo.dto.Zstudent;
import com.itboyst.facedemo.dto.Zstudent_login;
import com.itboyst.facedemo.service.ZstudentService;
import com.itboyst.facedemo.service.Zstudent_loginService;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.enums.ReadyState;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.beans.factory.annotation.Autowired;
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


@Controller
public class Renlian extends SelectorComposer<Component> {
    public final static Logger logger = LoggerFactory.getLogger(Renlian.class);
    private static final long serialVersionUID = 1L;

    // 自定义局部变量
    private static final String jieshiip = "192.168.1.156"; // 杰视设备的IP地址
    private static final String url = "ws://" + jieshiip + ":8080/webapi/websocket";
    //只能自定义接口类

    private static Zstudent_loginService zstudentLoginService;
    private static ZstudentService zstudentService;


    // 界面变量
    @Wire
    private Window renlianwin;
    @Wire
    private Button startBtn;
    @Wire
    private Button stopBtn;

    WebSocketClient myClient = null;

    @Autowired
   public void setZstudentLoginService (Zstudent_loginService zstudentLoginService){
        Renlian.zstudentLoginService=zstudentLoginService;
    }

   @Autowired
    public void setZstudentService (ZstudentService zstudentService){
        Renlian.zstudentService=zstudentService;
    }

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
                    System.out.println("识别:" + xuehao + " , " + dongtaipayload.getString("personName"));

                    if ((xuehao != null) && (xuehao.length() > 0)) {

                        //int i =zstudentLoginService.deleteStudentLoginByzidentity(xuehao);

                        Zstudent_login zsl=new Zstudent_login();
                        String uuid2 = UUID.randomUUID().toString().replaceAll("-","");
                        zsl.setZid(uuid2);
                        zsl.setZrecognizeIP(jieshiip);
                        zsl.setZcheck("人脸识别");
                        Zstudent abc =zstudentService.findstudentByZidentity(xuehao);
                        System.out.println(abc);
                        if(abc.getZid()!=null){
                            zsl.setZstudentID(abc.getZid());
                            zsl.setZtype("入口签到");
                            Timestamp timestamp=new Timestamp(System.currentTimeMillis());
                            zsl.setZrecongnizetime(timestamp);
                            int a =zstudentLoginService.updateloginmessage(zsl);
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
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            myClient.send("MyClient");
            System.out.println("发送成功");
        }
    }

    @Listen("onClick = #stopBtn")
    public void stopBtnClick() {
        if (myClient != null) {
            myClient.close();
        }
    }

}
