package com.itboyst.facedemo.cache;

import com.itboyst.facedemo.domain.CameraPojo;
import com.itboyst.facedemo.push.CameraPush;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class CacheUtil {
    /*
     * 保存已经开始推的流
     */
    public static Map<String, CameraPojo> STREATMAP = new ConcurrentHashMap<String, CameraPojo>();

    /*
     * 保存push
     */
    public static Map<String, CameraPush> PUSHMAP = new ConcurrentHashMap<>();
    /*
     * 保存服务启动时间
     */
    public static long STARTTIME;
}
