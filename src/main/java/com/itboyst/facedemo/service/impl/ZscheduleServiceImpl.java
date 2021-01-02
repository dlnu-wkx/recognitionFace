package com.itboyst.facedemo.service.impl;

import com.itboyst.facedemo.dto.Zschedule;
import com.itboyst.facedemo.mapper.ZscheduleMapper;
import com.itboyst.facedemo.service.ZscheuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class ZscheduleServiceImpl implements ZscheuleService {
    @Autowired
    ZscheduleMapper zscheduleMapper;

    @Override
    public Zschedule selectbynt(String zname, Timestamp time){
        return zscheduleMapper.selectbynt(zname,time);
    }

    @Override
    public int updatetestbyscheduleid(String zselecttest,int zpassingscore,String zid){return zscheduleMapper.updatetestbyscheduleid(zselecttest,zpassingscore,zid);}
}
