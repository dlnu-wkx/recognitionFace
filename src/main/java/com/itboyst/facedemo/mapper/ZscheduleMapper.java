package com.itboyst.facedemo.mapper;

import com.itboyst.facedemo.dto.Zschedule;

import java.sql.Timestamp;

public interface ZscheduleMapper {

    public Zschedule selectbynt(String zname, Timestamp time);

    public int updatetestbyscheduleid(String zselecttest,int zpassingscore,String zid);
}
