package com.itboyst.facedemo.thread;

import com.itboyst.facedemo.cache.CacheUtil;
import com.itboyst.facedemo.controller.CameraController;
import com.itboyst.facedemo.domain.CameraPojo;
import com.itboyst.facedemo.push.CameraPush;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Title CameraThread.java
 * @description TODO
 * @time 2019年12月16日 上午9:32:43
 * @author wuguodong
 **/
public class CameraThread {
    private final static Logger logger = LoggerFactory.getLogger(CameraThread.class);

    public static class MyRunnable implements Runnable {

        // 创建线程池
        public static ExecutorService es = Executors.newCachedThreadPool();

        private CameraPojo cameraPojo;
        private Thread nowThread;

        public MyRunnable(CameraPojo cameraPojo) {
            this.cameraPojo = cameraPojo;
        }

        // 中断线程
        public void setInterrupted(String key) {
            CacheUtil.PUSHMAP.get(key).setExitcode(1);
        }

        @Override
        public void run() {
            // 直播流
            try {
                // 获取当前线程存入缓存
                nowThread = Thread.currentThread();
                CacheUtil.STREATMAP.put(cameraPojo.getToken(), cameraPojo);
                // 执行转流推流任务
                CameraPush push = new CameraPush(cameraPojo);
                CacheUtil.PUSHMAP.put(cameraPojo.getToken(), push);
                push.push();
                // 清除缓存
                CacheUtil.STREATMAP.remove(cameraPojo.getToken());
                CameraController.JOBMAP.remove(cameraPojo.getToken());
                CacheUtil.PUSHMAP.remove(cameraPojo.getToken());
            } catch (Exception e) {
                CacheUtil.STREATMAP.remove(cameraPojo.getToken());
                CameraController.JOBMAP.remove(cameraPojo.getToken());
                CacheUtil.PUSHMAP.remove(cameraPojo.getToken());
            }
        }
    }
}