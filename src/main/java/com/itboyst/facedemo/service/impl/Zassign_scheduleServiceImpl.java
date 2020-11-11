package com.itboyst.facedemo.service.impl;

import com.itboyst.facedemo.mapper.Zassign_scheduleMapper;
import com.itboyst.facedemo.service.Zassign_scheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class Zassign_scheduleServiceImpl implements Zassign_scheduleService {

    @Autowired
    Zassign_scheduleMapper zassign_scheduleMapper;

    @Override
    public int updatechecktime(Timestamp zchecktime, String zstudentscheduleID, String ztrainingtaskID){
      return   zassign_scheduleMapper.updatechecktime(zchecktime,zstudentscheduleID,ztrainingtaskID);
    }
}
