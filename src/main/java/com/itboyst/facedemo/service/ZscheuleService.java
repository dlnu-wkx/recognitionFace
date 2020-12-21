package com.itboyst.facedemo.service;

import com.itboyst.facedemo.dto.Zschedule;

import java.sql.Timestamp;

public interface ZscheuleService {

    public Zschedule selectbynt(String zname, Timestamp time);

    public int updatetestbyscheduleid(String zselecttest,int zpassingscore,String zid);
}
