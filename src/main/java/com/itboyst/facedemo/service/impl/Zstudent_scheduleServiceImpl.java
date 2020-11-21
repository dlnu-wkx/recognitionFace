package com.itboyst.facedemo.service.impl;

import com.itboyst.facedemo.mapper.Zstudent_scheduleMapper;
import com.itboyst.facedemo.service.Zstudent_scheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Zstudent_scheduleServiceImpl implements Zstudent_scheduleService {

    @Autowired
    Zstudent_scheduleMapper zstudent_scheduleMapper;

    @Override
    public int updatestate(String zstate,String zscheduleID,String zstudentID){
        return zstudent_scheduleMapper.updatestate(zstate,zscheduleID,zstudentID);

    }

    @Override
    public int updatetestbyscheduleid(String zscheduleID,String zselecttest,int zpassingscore){
        return zstudent_scheduleMapper.updatetestbyscheduleid(zscheduleID, zselecttest,zpassingscore);
    }

    @Override
    public int updatetestbychose(String zselecttest,int zpassingscore,String zscheduleID,String zid){
        return  zstudent_scheduleMapper.updatetestbychose(zselecttest, zpassingscore, zscheduleID, zid);
    }
}
