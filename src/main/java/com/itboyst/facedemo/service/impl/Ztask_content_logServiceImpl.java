package com.itboyst.facedemo.service.impl;

import com.itboyst.facedemo.mapper.Ztask_content_logMapper;
import com.itboyst.facedemo.service.Ztask_content_logService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Ztask_content_logServiceImpl implements Ztask_content_logService {

    @Autowired
    Ztask_content_logMapper ztask_content_logMapper;

    @Override
    public int updatestarttime(String zassignscheduleID,String ztrainingtaskcontentID,String zstarttime){
        return ztask_content_logMapper.updatestarttime(zassignscheduleID,ztrainingtaskcontentID,zstarttime);
    }
}
