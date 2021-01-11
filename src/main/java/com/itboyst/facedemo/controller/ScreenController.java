package com.itboyst.facedemo.controller;

import com.itboyst.facedemo.base.ScreenUtil;
import com.itboyst.facedemo.dto.TestScreen;
import com.itboyst.facedemo.service.TestscreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static com.itboyst.facedemo.controller.Renlian.logger;


@Controller
public class ScreenController {

    @Autowired
    TestscreenService testscreenService;

    //同屏
    @GetMapping("/testscreen")
    @ResponseBody
    public String sse() {
        // 获取屏幕截图，每个1秒获取一次，返回前端界面进行图片展示
        String capture = ScreenUtil.capture();
        SseEmitter emitter = new SseEmitter(0L) ;
        try {
            emitter.send(SseEmitter.event()
                    .reconnectTime(0)
                    .data(capture));
        } catch (IOException e) {
            logger.error("服务端推送服务发生异常", e);
        }
        return capture;
    }


    @RequestMapping("/insertpicturescreen")
    @ResponseBody
    public int insertscreen(String type,String position,int orders,String file,int timein) {
        String zid = UUID.randomUUID().toString().replaceAll("-","");
        String path = ClassUtils.getDefaultClassLoader().getResource("static").getPath();
        String filename=System.currentTimeMillis()+".png";
        String f2=path+File.separator+filename;
        file="D:/SchoolTrainFiles/Curriculum/taskcontent/"+file;
        try{
            ScreenUtil.copeImage(file,f2);
            } catch (Exception e) {
         logger.error("读取图片异常", e);
            }

        TestScreen testScreen=new TestScreen();
        testScreen.setId(zid);
        testScreen.setFilename(filename);
        testScreen.setOrders(orders);
        testScreen.setPosition(position);
        testScreen.setType("图片");
        testScreen.setTimein(timein);
        System.out.println(testScreen);

        return testscreenService.insertscreen(testScreen);
    }



    @RequestMapping("/findleftimg")
    @ResponseBody
    public List<TestScreen> findleftimg() {
        return testscreenService.findleftscreen();
    }


    @RequestMapping("/findlefcount")
    @ResponseBody
    public int findlefcount() {
        return testscreenService.findleftscreencount();
    }

}
