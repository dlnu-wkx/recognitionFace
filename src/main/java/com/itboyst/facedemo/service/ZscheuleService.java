package com.itboyst.facedemo.service;

import com.itboyst.facedemo.dto.Zschedule;

import java.sql.Timestamp;
import java.util.List;

public interface ZscheuleService {

    public Zschedule selectbynt(String zname, Timestamp time);

    public int updatetestbyscheduleid(String zselecttest,int zpassingscore,String zid);

    List<Zschedule> findbytime(Timestamp zstartdate);

    int addzschedule(Zschedule zschedule);

    String findidbycourename(String coursename);
}
