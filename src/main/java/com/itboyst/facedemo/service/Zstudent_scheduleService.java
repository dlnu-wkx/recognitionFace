package com.itboyst.facedemo.service;

public interface Zstudent_scheduleService {
    public int updatestate(String zstate,String zscheduleID,String zstudentID);

    public int updatetestbyscheduleid(String zscheduleID,String zselecttest,int zpassingscore);

    public int updatetestbychose(String zselecttest,int zpassingscore,String zscheduleID,String zid);
}
