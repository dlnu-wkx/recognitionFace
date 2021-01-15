package com.itboyst.facedemo;

import com.itboyst.facedemo.base.SpringUtil;
import com.itboyst.facedemo.cache.CacheUtil;
import com.itboyst.facedemo.controller.CameraController;
import com.itboyst.facedemo.push.CameraPush;
import com.itboyst.facedemo.thread.CameraThread;
import com.itboyst.facedemo.timer.CameraTimer;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PreDestroy;
import java.util.Date;
import java.util.Set;

@SpringBootApplication
@Import(SpringUtil.class)
@MapperScan({"com.itboyst.facedemo.dao.mapper", "com.itboyst.facedemo.mapper"})
@EnableTransactionManagement

public class Application extends SpringBootServletInitializer {

    private final static Logger logger = LoggerFactory.getLogger(Application.class);
    public static void main(String[] args) {

        // 服务启动执行FFmpegFrameGrabber和FFmpegFrameRecorder的tryLoad()，以免导致第一次推流时耗时。
        try {
            FFmpegFrameGrabber.tryLoad();
            FFmpegFrameRecorder.tryLoad();
        } catch (org.bytedeco.javacv.FrameRecorder.Exception e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 将服务启动时间存入缓存
        CacheUtil.STARTTIME = new Date().getTime();
        final ApplicationContext applicationContext = SpringApplication.run(Application.class, args);
        // 将上下文传入RealPlay类中,以使其使用config中的变量
        CameraPush.setApplicationContext(applicationContext);
    }
    @PreDestroy
    public void destory(){
        logger.info("服务结束，开始释放空间...");
        // 结束正在进行的任务
        Set<String> keys = CameraController.JOBMAP.keySet();
        for (String key : keys) {
            CameraController.JOBMAP.get(key).setInterrupted(key);
        }
        // 关闭线程池
        CameraThread.MyRunnable.es.shutdown();
        // 销毁定时器
        CameraTimer.timer.cancel();
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
        return builder.sources(Application.class);
    }



}

   