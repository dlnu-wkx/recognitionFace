package com.itboyst.facedemo.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Zstudent_scheduleMapper {

    public int updatestate(String zstate,String zscheduleID,String zstudentID);

    public int updatetestbyscheduleid(String zscheduleID,String zselecttest,int zpassingscore);

    public int updatetestbychose(String zselecttest,int zpassingscore,String zscheduleID,String zid);
}
